package com.test.chapter4basics;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 2017/11/27.
 */
public class ShutDown {

  public static void main(String[] args) throws InterruptedException {
    Runner one = new Runner();
    Thread countThread = new Thread(one, "CountThread");
    countThread.start();
    // 睡眠1秒,main线程对countThread进行中断,使CountThread能够感知中断而结束
    TimeUnit.SECONDS.sleep(1);
    countThread.interrupt();

    Runner two = new Runner();
    countThread = new Thread(two, "CountThread");
    countThread.start();
    // 睡眠1秒,main线程对Runner tow进行取消,使得CountThread能够感知on为false而结束
    TimeUnit.SECONDS.sleep(1);
    two.cancel();
  }


  private static class Runner implements Runnable {
    private long i;
    private volatile boolean on =true;
    @Override
    public void run() {
      while(on && !Thread.currentThread().isInterrupted()) {
        i++;
      }
      System.out.println("Count i = " + i);
    }

    public void cancel() {
      on = false;
    }
  }
}
