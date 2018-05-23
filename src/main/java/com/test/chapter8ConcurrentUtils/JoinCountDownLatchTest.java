package com.test.chapter8ConcurrentUtils;

/**
 * 主线程等待子线程执行完毕
 */
public class JoinCountDownLatchTest {

  public static void main(String[] args) throws InterruptedException {
    Thread parse1 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("parse1 finish!");
      }
    });
    Thread parse2 = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println("parse2 finish!");
      }
    });
    parse1.start();
    parse2.start();
    parse1.join();
    parse2.join();
    System.out.println("all parser finish!");
  }
}
