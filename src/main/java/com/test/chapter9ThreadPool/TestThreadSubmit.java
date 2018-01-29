package com.test.chapter9ThreadPool;


import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * 向线程池提交任务
 * submit方法用于提交需要返回值的任务
 */
public class TestThreadSubmit {

  public static void main(String[] args) {
    ExecutorService pool = Executors.newFixedThreadPool(5);
    Future<String> future = pool.submit(new Callable<String>() {
      @Override
      public String call() throws Exception {
        Thread.currentThread().sleep(5000);
        return "Hello World";
      }
    });
    try {
      String  result = future.get();
      System.out.println("获取结果: " + result);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } catch (ExecutionException e) {
      e.printStackTrace();
    } finally {
      pool.shutdown();
    }

  }
}
