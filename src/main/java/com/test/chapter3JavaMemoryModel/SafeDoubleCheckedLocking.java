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
          // instance 为volatile,防止重排序
          // new Instance() 这个操作可以理解为：
          // memory = allocate() 分配对象的内存空间
          // ctorInstance(memory) 初始化对象
          // instance = memory 设置instance指向刚分配的内存地址
          instance = new Instance();
        }
      }
    }
    return instance;
  }
}
