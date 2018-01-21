package com.test.chapter4basics;

import com.test.utils.SleepUtils;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 2017/11/28.
 */
public class WaitNotify {

  private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");
  private static boolean flag =  true;
  private static Object lock = new Object();

  public static void main(String[] args) throws InterruptedException {
    Thread waitThread = new Thread(new Wait(), "WaitThread");
    waitThread.start();
    TimeUnit.SECONDS.sleep(1);
    Thread notifyThread = new Thread(new Notify(), "NotifyThread");
    notifyThread.start();
  }


  static class Wait implements Runnable {
    @Override
    public void run() {
      //加锁,拥有lock的monitor
      synchronized (lock) {
        // 当条件不满足时,继续wait,同时释放lock的锁
        while(flag) {
          System.out.println(Thread.currentThread() + "flag is true. wait @ " +
              simpleDateFormat.format(new Date()));
          try {
            lock.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
        // 条件满足后,完成工作
        System.out.println(Thread.currentThread() + " flag is false. running @ " +
            simpleDateFormat.format(new Date()));
      }
    }
  }

  static class Notify implements Runnable {
    @Override
    public void run() {
      // 加锁,拥有lock的Monitor
      synchronized (lock) {
        // 获取lock的锁,然后进行通知,通知时不会释放lock的锁
        System.out.println(Thread.currentThread() + "hold lock, notify @ " +
            simpleDateFormat.format(new Date()));
        // 通知时不会释放锁
        lock.notifyAll();
        flag = false;
        SleepUtils.second(5);
        System.out.println(Thread.currentThread() + "release monitor!");
      }

      // 再次加锁
      synchronized (lock) {
        System.out.println(Thread.currentThread() + "hold lock again. sleep @ " +
            simpleDateFormat.format(new Date()));
        SleepUtils.second(5);
      }
    }
  }



}
