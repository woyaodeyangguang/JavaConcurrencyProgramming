package com.test.chapter5JavaLock;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 不要将获取锁的过程写在try块中，因为如果在获取锁（自定义锁的实现）时发生了异常，
 * 异常抛出的同时，也会导致锁无故释放
 *
 * Lock和synchronized区别
 * 1、synchronize是一种隐式锁，先获取后释放
 * 2、Lock需要显式获取锁和释放锁，虽然少了隐式的便捷性，但是永遇乐锁获取与释放的可操作性、可中断性、
 * 可中断的获取锁以及超时获取锁等多种synchronized关键字所不具备的同步特性.
 */
public class LockUseCase {
  Lock lock = new ReentrantLock();

  public static void main(String[] args) {
    LockUseCase instance = new LockUseCase();
    instance.use();
  }

  public void use() {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        useCase();
      }
    }, "CustomRunnable");
    thread.start();
    useCase();
  }

  /**
   * Lock的lock和unlock()
   */
  public void useCase() {
    lock.lock();
    try {
      System.out.println(Thread.currentThread().getName() +  " void something!");
      Thread.currentThread().sleep(4000);
    } catch (Exception e) {
    } finally {
      // 一定要unlock
      lock.unlock();
    }
  }
}
