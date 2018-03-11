package com.test.chapter5JavaLock;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 有界队列
 */
public class BoundQueue<T> {
  private Object[] items;
  // 添加的下标、删除的下标和当前数组的数量
  private int addIndex;
  private int removeIndex;
  private int count;
  private Lock lock = new ReentrantLock();
  private Condition notFully = lock.newCondition();
  private Condition notEmpty = lock.newCondition();

  public BoundQueue(int size) {
    items = new Object[size];
  }

  // 添加一个元素，如果数组满，则添加线程进入等待状态，知道有"空位"
  public void add(T t) throws InterruptedException {
    lock.lock();
    try {
      while (count == items.length) {
        notFully.await();
      }
      items[addIndex] = t;
      if(++addIndex == items.length) {
        addIndex = 0;
      }
      count++;
      notEmpty.signal();
    } finally {
      lock.unlock();
    }
  }

  // 由头部删除一个元素，如果数组为空，则删除线程进入等待状态，直到有新添加的元素
  public T remove() throws InterruptedException {
    lock.lock();
    try {
      while (count == 0) {
        notEmpty.await();
      }
      Object x = items[removeIndex];
      if(++removeIndex == items.length) {
        removeIndex = 0;
      }
      --count;
      notFully.signal();
      return (T) x;
    } finally {
      lock.unlock();
    }
  }
}