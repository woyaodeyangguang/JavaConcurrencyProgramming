package com.test.exercise;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * HashMap 多线程条件下回出现问题.
 */
public class TestHashMap {

  private static Map<Integer, Integer> map = new HashMap<>();
  private static AtomicInteger ai = new AtomicInteger();

  public static void main(String[] args) {
    Thread t0 = new Thread(new CustomRunnable());
    Thread t1 = new Thread(new CustomRunnable());
    Thread t2 = new Thread(new CustomRunnable());
    Thread t3 = new Thread(new CustomRunnable());
    Thread t4 = new Thread(new CustomRunnable());
    t0.start();
    t1.start();
    t2.start();
    t3.start();
    t4.start();
    while (true){

    }
  }

  private static class CustomRunnable implements Runnable {
    @Override
    public void run() {
      while(ai.get() < 100000) {
        map.put(ai.get(), ai.get());
        ai.getAndIncrement();
      }
    }
  }
}
