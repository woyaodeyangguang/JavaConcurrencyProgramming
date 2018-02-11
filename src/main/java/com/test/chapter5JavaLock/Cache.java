package com.test.chapter5JavaLock;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 使用读写锁构建线程安全的缓存
 * 读操作时，需要获取读锁，并发访问该方法时并不会阻塞
 * structural 时，需要获取写锁，其他的读锁、写锁的获取都会被阻塞
 */
public class Cache {
  private static Map<String, Object> map = new HashMap<>();
  private static ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
  private static Lock r = rwl.readLock();
  private static Lock w = rwl.writeLock();

  // 获取key对应value
  public static final Object get(String key) {
    r.lock();
    try {
      return map.get(key);
    } finally {
      r.unlock();
    }
  }

  // 设置旧的key,并返回旧值
  public static final Object put(String key, Object value) {
    w.lock();
    try {
      return map.put(key, value);
    } finally {
      w.unlock();
    }
  }

  public static final void clear() {
    w.lock();
    try {
      map.clear();
    } finally {
      w.unlock();
    }
  }
}
