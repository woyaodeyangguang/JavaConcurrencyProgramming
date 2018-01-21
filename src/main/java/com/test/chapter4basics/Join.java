package com.test.chapter4basics;

import java.util.concurrent.TimeUnit;

/**
 * 如果一个线程A执行了thread.join()语句，其含义是：
 * 当前线程A等待thread线程终止之后才从thread.join()返回.
 */
public class Join {

  public static void main(String[] args) throws InterruptedException {
    Thread previous = Thread.currentThread();
    for (int i = 0; i < 10; i++) {
      // 每一个线程拥有前一个线程的引用,需要等待前一个线程终止,才能从等待中返回
      Thread thread = new Thread(new Domino(previous), String.valueOf(i));
      thread.start();
      previous = thread;
      TimeUnit.SECONDS.sleep(5);
      System.out.println(Thread.currentThread().getName() + " terminate.");
    }
  }

  static class Domino implements Runnable {
    private Thread thread;

    public Domino(Thread thread) {
      this.thread = thread;
    }

    @Override
    public void run() {
      try {
        thread.join();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      System.out.println(Thread.currentThread().getName() + " terminate.");
    }
  }
}
