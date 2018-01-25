package com.test.chapter5JavaLock;

import com.test.chapter4basics.ThreadPool;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.Condition;

/**
 * 独占锁
 * 同一时刻只能有一个线程获取锁，其他获取锁的线程只能处于同步队列中等待
 * 只有获取锁的线程释放锁，后继的线程才能获得锁.
 */
public class Mutex {

  // 静态内部类，自定义同步器
  private static class Sync extends AbstractQueuedSynchronizer {

    // 是否处于占用状态
    @Override
    protected boolean isHeldExclusively() {
      return getState() == 1;
    }

    // 当状态为0时获取锁
    @Override
    protected boolean tryAcquire(int acquires) {
      if (compareAndSetState(0, 1)) {
        setExclusiveOwnerThread(Thread.currentThread());
        return true;
      }
      return false;
    }

    // 释放锁，将状态设置为0
    @Override
    protected boolean tryRelease(int releases) {
      if (getState() == 0) {
        throw new IllegalMonitorStateException();
      }
      setExclusiveOwnerThread(null);
      setState(0);
      return true;
    }

    Condition newCondition() {
      return new ConditionObject();
    }
  }

    // 仅需要将操作代理到Sync上即可
    private final Sync sync = new Sync();

    public void lock() {
      sync.acquire(1);
    }

    public boolean tryLock() {
      return sync.tryAcquire(1);
    }

    public void unlock() {
      sync.release(1);
    }

    public Condition newCondition() {
      return sync.newCondition();
    }

    public boolean isLocked() {
      return sync.isHeldExclusively();
    }

    public boolean hasQueuedThreds() {
      return sync.hasQueuedThreads();
    }

    public void lockInterruptibly() throws InterruptedException {
      sync.acquireInterruptibly(1);
    }

    public boolean tryLock(long timeout, TimeUnit unit) throws InterruptedException {
      return sync.tryAcquireNanos(1, unit.toNanos(timeout));
    }
}
