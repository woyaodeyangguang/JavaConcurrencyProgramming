package com.test.chapter5JavaLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Test Condition interface.
 */
public class TestCondition {

  private static Lock lock  = new ReentrantLock();
  private static Condition conditon = lock.newCondition();
  private static volatile boolean flag = false;

  public static void main(String[] args) {
    Thread thread1 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          lock.lock();
          String name = Thread.currentThread().getName();
          System.out.println(name + "睡眠三秒");
          Thread.currentThread().sleep(3000);
          // 开关还没有开开
          if(!flag) {
            System.out.println(name + ", 检测到开关没打开");
            conditon.await();
          }
          System.out.println(name + "运行结束");
        } catch (Exception e) {
          e.printStackTrace();
        } finally {
          lock.unlock();
        }
      }
    }, "Thread1");
    Thread thread2 = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          lock.lock();
          String name = Thread.currentThread().getName();
          flag = true;
          System.out.println(name + "运行结束");
          conditon.signal();
        } finally {
          lock.unlock();
        }
      }
    }, "Thread2");
    thread1.start();
    thread2.start();
  }
}
