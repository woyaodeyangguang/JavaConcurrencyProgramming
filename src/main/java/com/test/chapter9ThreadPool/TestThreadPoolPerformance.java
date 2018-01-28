package com.test.chapter9ThreadPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 测试线程池和普通线程池性能比较
 */
public class TestThreadPoolPerformance {
  private static final int NUM =1000;

  public static void main(String[] args) {
    method1();
    method2();
  }

  /**
   * 普通方法提交线程
   */
  public static void method1() {
    long start = System.currentTimeMillis();
    for (int i = 0; i < NUM; i++) {
      Thread thread = new Thread(new MyThread());
      thread.start();
    }
    System.out.print("Time cost:");
    System.out.println(System.currentTimeMillis() - start);
  }

  /**
   * 利用线程池来提交线程池
   */
  public static void method2() {
    ExecutorService pool = Executors.newFixedThreadPool(10);
    long start = System.currentTimeMillis();
    for (int i = 0; i < NUM; i++) {
      pool.execute(new MyThread());
    }
    System.out.print("Time cost:");
    System.out.println(System.currentTimeMillis() - start);
  }

  private static class MyThread implements Runnable {
    @Override
    public void run() {
    }
  }
}
