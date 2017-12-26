package com.test.exercise;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Admin on 2017/12/26.
 */
public class TestReentrantLock {

  private static ReentrantLock lock = new ReentrantLock();
  public static void main(String[] args) {
    TestReentrantLock instance = new TestReentrantLock();
    Thread thread1 = new Thread(new CustomRunnable(), "Thread1");
    Thread thread2 = new Thread(new CustomRunnable(), "Thread2");
    thread1.start();
    thread2.start();
  }

  private static class CustomRunnable implements Runnable {
    @Override
    public void run() {
      method();
    }
  }


  public static void method() {
    lock.lock();
    try{
      System.out.println(Thread.currentThread().getName() + "获取锁");
    } finally {
      // 必须在finally中进行关闭,要不然代码块发生异常后，线程永远都不会释放锁
      lock.unlock();
    }
  }

}
