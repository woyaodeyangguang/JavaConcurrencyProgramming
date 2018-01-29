package com.test.chapter10Executor;

import com.test.chapter10Executor.TestFixedThreadPool.CustomRunnable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * CachedThreadPool是一个根据需要创建新线程的线程池
 */
public class TestCachedThreadPool {
  private static final int NUM = 10;

  public static void main(String[] args) {
    // corePoolSize设置为0
    // maximumPoolSize被设置为Integer.MAX_VALUE,因此maximumPoolSize是无界的
    // keepAlive设置为60秒
    // 如果主线程提交任务的速度高于maximumPoolSize中线程处理任务的速度时
    // CachedThreadPool会不断地创建线程，有可能会耗尽CPU和内存资源
    ExecutorService pool = Executors.newCachedThreadPool();
    for (int i = 0; i < NUM; i++) {
      CustomRunnable runnable = new CustomRunnable();
      pool.execute(runnable);
    }
    System.out.println("提交任务完成!");
  }
}
