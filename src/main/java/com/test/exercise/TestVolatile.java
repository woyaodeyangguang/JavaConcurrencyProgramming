package com.test.exercise;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 2017/11/27.
 */
public class TestVolatile {

  public static void main(String[] args) throws InterruptedException {
    Runner one = new Runner();
    Thread thread1 = new Thread(one, "Thread1");
    thread1.start();
    TimeUnit.SECONDS.sleep(1);
    one.cancel();

  }

  private static class Runner implements Runnable {
    private  boolean var = true;
    @Override
    public void run() {
      String threadName = Thread.currentThread().getName();
      if("Thread1".equals(threadName)) {
        while (true && var) {
          System.out.println(threadName + " print " + var);
        }
      }
    }

    public void cancel() {
        var = false;
        System.out.println(Thread.currentThread().getName() + " update var");
      }
    }

}
