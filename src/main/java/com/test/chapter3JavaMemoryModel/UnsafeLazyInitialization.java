package com.test.chapter3JavaMemoryModel;

/**
 * 非线程安全的延迟加载初始化对象.
 */
public class UnsafeLazyInitialization {
  private static Instance instance;

  public static Instance getInstance() {
    try {
      Thread.currentThread().sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
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
          UnsafeLazyInitialization.getInstance();
        }
      });
      thread.start();
    }
  }
}
