package com.test.chapter8ConcurrentUtils;

import java.util.concurrent.CountDownLatch;

/**
 * CountDownLatch的构造函数接收一个int类型的参数作为计数器，如果等n个线程完成，就传入n
 */
public class CountDownLatchTest {
  public static CountDownLatch countDownLatch = new CountDownLatch(2);

  public static void main(String[] args) throws InterruptedException {
    new Thread(new Runnable() {
      public void run() {
        System.out.println(1);
        // countDown方法会用在任何地方，所以这里所说的N个点，可以是N个线程，也可以是1个线程中N步骤
        // 用在多线程时,只需要把这个CountDownLatch的引用传递给线程里面即可.
        countDownLatch.countDown();
        System.out.println(2);
        countDownLatch.countDown();
      }
    }).start();
    // await会阻塞当前线程,直到n变成0
    countDownLatch.await();
    System.out.println(3);
  }
}
