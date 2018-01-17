package com.test.chapter3JavaMemoryModel;

/**
 * 非线程安全的延迟加载初始化对象.
 */
public class UnsafeLazyInitialization {
  private static Instance instance;

  public static Instance getInstance() {
    if(instance == null) {
      instance = new Instance();
    }
    return instance;
  }
}
