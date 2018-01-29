package com.test.chapter10Executor;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Runnable和Callable相互之间转换.
 * Runnable 不会返回执行结果
 * Callable可以返回结果
 */
public class Convert {


  public static Callable<Object> callable(Runnable runnable) {
    return Executors.callable(runnable);
  }

}
