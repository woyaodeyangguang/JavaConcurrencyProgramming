package com.test.chapter8ConcurrentUtils;

import java.util.concurrent.CyclicBarrier;

/**
 * 同步屏障
 * 让一组线程到达一个屏障(也可以叫做同步点)时被阻塞,直到最后一个线程到达屏障时,屏障才会开门
 * 所有被屏障拦截的线程才会继续执行.
 */
public class CyclicBarrierTest {
  private static CyclicBarrier barrier = new CyclicBarrier(2);

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
}
