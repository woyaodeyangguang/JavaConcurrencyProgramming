package com.test.chapter5JavaLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Condition use case.
 */
public class ConditionUseCase {
  private Lock lock = new ReentrantLock();
  private Condition condition = lock.newCondition();

  public void conditionWait() throws InterruptedException {
    lock.lock();
    try {
      System.out.println("release lock, begin wait!");
      condition.await();
    } finally {
      lock.unlock();
    }
    System.out.println("condition wait end!");
  }

  public void conditionSignal() {
    lock.lock();
    try {
      System.out.println("begin signal!");
      condition.signal();
    } finally {
      lock.unlock();
    }
    System.out.println("condition signal end!");
  }

  public static void main(String[] args) throws Exception {
    ConditionUseCase useCase = new ConditionUseCase();
    new Thread(() -> {
      try {
        useCase.conditionWait();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }).start();
    Thread.currentThread().sleep(3000);
    new Thread(() -> useCase.conditionSignal()).start();
  }
}
