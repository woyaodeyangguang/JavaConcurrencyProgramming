package com.test.chapter4basics;

import com.test.utils.SleepUtils;
import java.util.concurrent.TimeUnit;

/**
 * 中断可以理解为线程的一个标识位属性,它表示一个运行的线程是否被其他线程进行了中断操作.
 */
public class Interrupted {

  public static void main(String[] args) throws InterruptedException {
    // sleepThread不停的尝试睡眠
    Thread sleepThread = new Thread(new SleepRunnable(), "SleepThread");
    sleepThread.setDaemon(true);
    // busyThread不停的运行
    Thread busyThread = new Thread(new BusyRunnable(), "BusyThread");
    busyThread.setDaemon(true);

    sleepThread.start();
    busyThread.start();

    // 休眠5秒钟,让sleepThread和busyThread充分运行
    TimeUnit.SECONDS.sleep(5);

    sleepThread.interrupt();
    busyThread.interrupt();
    System.out.println("SleepThread interrupted is " + sleepThread.isInterrupted());
    System.out.println("BusyThread interrupted is " + busyThread.isInterrupted());

    // 防止sleepThread和busyThread立即退出
    SleepUtils.second(2);
  }



  static class BusyRunnable implements Runnable {
    @Override
    public void run() {
      while (true) {}
    }
  }


  static class SleepRunnable implements Runnable {
    @Override
    public void run() {
      while(true) {
        SleepUtils.second(10);
      }
    }
  }
}
