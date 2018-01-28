package com.test.chapter9ThreadPool;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 线程池.
 */
public class TestThreadPool {

  public static void main(String[] args) {
    //ExecutorService pool = Executors.newSingleThreadExecutor();
    ExecutorService pool = Executors.newFixedThreadPool(3);
    Thread thread1 = new MyThread();
    Thread thread2 = new MyThread();
    Thread thread3 = new MyThread();
    Thread thread4 = new MyThread();
    Thread thread5 = new MyThread();
    // 线程放入到线程池中
    pool.execute(thread1);
    pool.execute(thread2);
    pool.execute(thread3);
    pool.execute(thread4);
    pool.execute(thread5);
    System.out.println("线程全部加入!");
    pool.shutdown();
    System.out.println("线程停止");
  }

  public static class MyThread extends Thread {

    @Override
    public void run() {
      System.out.println(Thread.currentThread().getName() + "正在执行");
      try {
        Thread.currentThread().sleep(1000 * 3);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }
}
