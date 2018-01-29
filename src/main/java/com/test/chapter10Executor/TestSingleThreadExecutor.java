package com.test.chapter10Executor;

import com.test.chapter10Executor.TestFixedThreadPool.CustomRunnable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * SingleThreadExecutor是使用单个worker线程的Executor.
 * 常常用于线程的顺序执行.
 */
public class TestSingleThreadExecutor {
  private static final int NUM = 10;

  public static void main(String[] args) {
    // corePoolSize和maximumPoolSize为1
    // 由于使用了无界队列因此maximumPoolSize和keepAlive为无效参数
    ExecutorService pool = Executors.newSingleThreadExecutor();
    for (int i = 0; i < NUM; i++) {
      CustomRunnable runnable = new CustomRunnable();
      pool.execute(runnable);
    }
    System.out.println("提交任务完成!");
  }
}
