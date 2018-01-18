package com.test.chapter3JavaMemoryModel;

/**
 * 基于volatile的解决方案.
 */
public class SafeDoubleCheckedLocking {
  private static volatile Instance instance;

  public static Instance getInstance() {
    if(instance == null) {
      synchronized (SafeDoubleCheckedLocking.class) {
        if(instance == null) {
          instance = new Instance(); // instance 为volatile,现在没有问题了
        }
      }
    }
    return instance;
  }
}
