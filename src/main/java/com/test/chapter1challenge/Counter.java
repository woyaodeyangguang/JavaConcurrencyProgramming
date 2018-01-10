package com.test.chapter1challenge;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.swing.plaf.basic.BasicInternalFrameTitlePane.SystemMenuBar;

/**
 * 使用循环CAS实现原子操作.
 */
public class Counter {
  private AtomicInteger atomicI = new AtomicInteger(0);
  private int i  = 0;

  public static void main(String[] args) {
    final Counter cas = new Counter();
    List<Thread> list = new ArrayList<>(600);
    long start = System.currentTimeMillis();
    for (int j = 0; j <100 ; j++) {
      Thread t = new Thread(new Runnable() {
        @Override
        public void run() {
          for (int i = 0; i < 10000; i++) {
            cas.count();
            cas.safeCount();
          }
        }
      });
      list.add(t);
    }
    for(Thread t : list) {
      t.start();
    }
    for(Thread t : list) {
      try {
        t.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
    System.out.println(cas.i);
    System.out.println(cas.atomicI.get());
    System.out.println(System.currentTimeMillis() - start);

  }

  /**
   * 非线程安全计数器
   */
  private void count() {
    i++;
  }

  /**
   * 使用CAS实现线程安全计数器
   */
  private void safeCount() {
    for (;;) {
      int i = atomicI.get();
      boolean suc = atomicI.compareAndSet(i, ++i);
      if(suc) {
        break;
      }
    }
  }

}
