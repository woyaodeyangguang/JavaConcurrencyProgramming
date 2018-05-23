package com.test.chapter8ConcurrentUtils;

import java.util.concurrent.CyclicBarrier;

/**
 * CyclicBarrier还提供更加高级的构造函数CyclicBarrier(int parties, Runnable barrierAction)
 */
public class CyclicBarrierTest2 {
  private static CyclicBarrier barrier = new CyclicBarrier(2, new A());

  public static void main(String[] args) {
    new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          barrier.await();
        } catch (Exception e) {
          e.printStackTrace();
        }
        System.out.println(1);
      }
    }).start();
    try {
      barrier.await();
    } catch (Exception e) {
    }
    System.out.println(2);
  }

  private static class A implements Runnable {

    @Override
    public void run() {
      System.out.println(3);
    }
  }
}
