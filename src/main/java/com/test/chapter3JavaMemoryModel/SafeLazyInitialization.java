package com.test.chapter3JavaMemoryModel;

import com.test.chapter4basics.Synchronized;

/**
 * 单例 利用synchronized修饰方法.
 */
public class SafeLazyInitialization {
  private static Instance instance;

  // 导致每次调用方法时,synchronized都会造成性能开销
  public static synchronized Instance getInstance() {
    if(instance == null) {
      instance = new Instance();
    }
    return instance;
  }

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
          SafeLazyInitialization.getInstance();
        }
      });
      thread.start();
    }
  }

}
