package com.test.exercise;

import com.test.utils.MemoryCache;

/**
 * Created by Admin on 2017/11/6.
 */
public class TestWait {

  public static void main(String[] args) {
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (MemoryCache.getCache()) {
          System.out.println(Thread.currentThread().getName());
          if(MemoryCache.size() == 0) {
            try {
              // wait方法会让出处理机
               MemoryCache.getCache().wait();
              System.out.println("终于处理完了呀");
              // sleep方法不会释放当前线程所持有的锁
              // Thread.currentThread().sleep(10000);
            } catch (InterruptedException e) {
              e.printStackTrace();
            }
          }
        }
      }
    }, "thread1");

    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        synchronized (MemoryCache.getCache()) {
          System.out.println(Thread.currentThread().getName());
          MemoryCache.getCache().notify();
        }
      }

    }, "thread2");
    thread1.start();
    thread2.start();
  }

}
