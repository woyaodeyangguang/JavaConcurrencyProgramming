package com.test.chapter3JavaMemoryModel;


/**
 * 同步程序.
 */
public class SynchronizedExample {
  int a = 0;
  boolean flag = false;

  public synchronized void writer() { // 获取锁
    String threadName = Thread.currentThread().getName();
    System.out.println(threadName + "获取锁");
    a = 1;
    flag = true;
    System.out.println(threadName + "释放锁");
  }                                   // 释放锁

  public synchronized void reader() { // 获取锁
    String threadName = Thread.currentThread().getName();
    System.out.println(threadName + "获取锁");
    if(flag) {
      int i = a * a;
    }
    System.out.println(threadName + "释放锁");
  }                                   // 释放锁

  public static void main(String[] args) {
    SynchronizedExample instance = new SynchronizedExample();
    Thread writerThread = new Thread(new Runnable() {
      @Override
      public void run() {
        instance.writer();
      }
    }, "WriterThread");
    Thread readerThread = new Thread(new Runnable() {
      @Override
      public void run() {
        instance.reader();
      }
    }, "ReaderThread");
    writerThread.start();
    readerThread.start();
  }
}
