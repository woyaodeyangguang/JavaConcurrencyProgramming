package com.test.chapter3JavaMemoryModel;

import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by Admin on 2018/1/16.
 */
public class ReentrantLockExample {
  int a = 0;
  ReentrantLock lock = new ReentrantLock();

  public void writer() {
    lock.lock();
    try{
      a++;
    } finally {
      lock.unlock();
    }
  }

  public void reader() {
    lock.lock();
    try {
        int i = a;
    } finally {
      lock.unlock();
    }
  }
}
