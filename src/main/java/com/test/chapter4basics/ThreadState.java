package com.test.chapter4basics;

import com.test.utils.SleepUtils;

/**
 * 线程的状态
 */
public class ThreadState {

  // 该线程不断地进行睡眠
  static class TimeWaiting implements Runnable {
    @Override
    public void run() {
      while (true) {
        SleepUtils.second(2);
      }
    }
  }

  //该线程再Waiting.class实例上等待
  static class Waiting implements Runnable {
    @Override
    public void run() {
      while (true) {
        synchronized (Waiting.class) {
          try {
            Waiting.class.wait();
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      }
    }
  }
  // 该线程再Bolcked.class实例上加锁，不会释放锁
  static class Blocked implements Runnable {
    @Override
    public void run() {
      synchronized (Blocked.class) {
        while(true) {
          SleepUtils.second(5);
        }
      }
    }
  }

  public static void main(String[] args) {
    new Thread(new TimeWaiting(), "TimeWaitingThread").start();
    new Thread(new Waiting(), "WaitingThread").start();
    new Thread(new Blocked(), "BlockedThread-1").start();
    new Thread(new Blocked(), "BlockedThread-2").start();
    System.out.println("start thread sucess!");
  }
}
