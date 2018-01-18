package com.test.chapter3JavaMemoryModel;

/**
 * JVM在类的初始化阶段（即在Class被加载后，且被线程使用之前），会执行类的初始化
 * 在执行类的初始化期间，JVM会获取一个锁。这个锁可以同步多个线程对同一个类的初始化
 */
public class InstanceFactory {
  private static class InstanceHolder {
    public static Instance instace = new Instance();
  }

  public static Instance getInstace() {
    // 这里才会导致InstanceHolder类被初始化
    return InstanceHolder.instace;
  }
}
