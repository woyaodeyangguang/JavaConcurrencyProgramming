package com.test.chapter10Executor;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.FutureTask;

/**
 * FutureTask的使用
 * 1.可以把FutureTask交给Executor执行
 * 2.也可以通过ExecutorService.submit(...)返回一个FutureTask,可以执行FutureTask.get()或者cancel()
 */
public class TestFutureTask {

  private final Map<String, FutureTask<String>> taskCache = new ConcurrentHashMap<>();

  private String executionTask(final String taskName) {
    while(true) {
      FutureTask<String> future = taskCache.get(taskName);
      if(future == null) {
        Callable<String> task = new Callable<String>() {
          @Override
          public String call() throws Exception {
            return taskName;
          }
        };
        // 创建任务
        FutureTask<String> futureTask = new FutureTask<String>(task);
        future = taskCache.putIfAbsent(taskName, futureTask);
        if(future == null) {
          future = futureTask;
          futureTask.run();
        }
      }
      try {
          return future.get();
      } catch (Exception e) {
          taskCache.remove(taskName, future);
      }
    }
  }
}
