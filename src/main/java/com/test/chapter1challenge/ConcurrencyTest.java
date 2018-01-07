package com.test.chapter1challenge;

/**
 * 代码并行执行一定比串行快吗？
 * 不一定
 * 如果数量比较少时,当并发执行累加操作不超过百万次时，速度会比串行执行累加操作要满，
 *     因为线程有创建和上下文切换的开销
 * 当数量较大时，并发执行效率会更高一些
 */
public class ConcurrencyTest {

  // pivate static final long count = 1000;
  private static final long count = 100000000;

  public static void main(String[] args) throws InterruptedException {
    concurrency();
    serial();
  }

  private static void concurrency() throws InterruptedException {
    long start = System.currentTimeMillis();
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        int a = 0;
        for (long i = 0; i < count; i++) {
          a += 5;
        }
        while (true) {}
      }
    });
    thread.start();
    int b = 0;
    for (long i = 0; i < count; i++) {
      b--;
    }
    System.out.println(thread.isAlive());
    thread.join(10000);
    System.out.println(thread.isAlive());
    long time = System.currentTimeMillis() - start;
    System.out.println("Concurrency :" + time + "ms, b = " + b);
  }

  private static void serial() {
    long start = System.currentTimeMillis();
    int a = 0;
    for (long i = 0; i < count; i++) {
      a += 5;
    }
    int b = 0;
    for (long i = 0; i < count; i++) {
      b--;
    }
    long time = System.currentTimeMillis() - start;
    System.out.println("serial :" + time + "ms,b= " + b + ", a=" + a);
  }

}
