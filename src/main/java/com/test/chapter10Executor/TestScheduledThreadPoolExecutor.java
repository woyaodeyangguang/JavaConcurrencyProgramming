package com.test.chapter10Executor;

import com.test.chapter10Executor.TestFixedThreadPool.CustomRunnable;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ScheduledThreadPoolExecutor主要用来在给定的延迟之后运行任务,或者定期执行任务
 */
public class TestScheduledThreadPoolExecutor {
  private static final int NUM = 10;

  public static void main(String[] args) {
    ScheduledExecutorService pool = Executors.newScheduledThreadPool(1);
    for (int i = 0; i < NUM; i++) {
      CustomRunnable runnable = new CustomRunnable();
      pool.schedule(runnable, 3, TimeUnit.SECONDS);
    }
    System.out.println("提交任务完成!");
  }
}
