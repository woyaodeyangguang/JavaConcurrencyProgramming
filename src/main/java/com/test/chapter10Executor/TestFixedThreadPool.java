package com.test.chapter10Executor;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * FixedThreadPool固定线程数的线程池
 *
 */
public class TestFixedThreadPool {

  private static final int NUM = 10;

  public static void main(String[] args) {
    // corePoolSize和maximumPoolSize都被设置为nThreads
    // 由于使用了无界队列因此maximumPoolSize和keepAlive为无效参数
    ExecutorService pool = Executors.newFixedThreadPool(3);
    for (int i = 0; i < NUM; i++) {
      CustomRunnable runnable = new CustomRunnable();
      pool.execute(runnable);
    }
    System.out.println("提交任务完成!");
  }

  public static class CustomRunnable implements Runnable {

    @Override
    public void run() {
      try {
        System.out.println(Thread.currentThread().getName());
        Thread.currentThread().sleep(3 * 1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

}
