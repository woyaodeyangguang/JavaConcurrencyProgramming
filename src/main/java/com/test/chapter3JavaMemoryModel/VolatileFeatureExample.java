package com.test.chapter3JavaMemoryModel;

/**
 * 理解volatile特性的一个好方法是把对volatile变量的读/写，看成是同一个锁对单个读/写操作做了同步.
 * 锁的happens-before规则保证释放锁和获取锁的两个线程之间的可见性，意味着对一个volatile变量的读，
 *     总是能看到(任意线程)对这个volatile变量最后的写入
 *
 * volatile具备以下特征：
 * 可见性：对一个volatile变量的读，总是能看到(任意线程)对这个volatile变量最后的写入
 * 原子性：对任意单个volatile的读/写具备原子性，但类似于volatile++这种复合操作不具备原子性
 */
public class VolatileFeatureExample {

  volatile long vl = 0L;// 使用volatile声明的64位的long型变量

  public void set(long l) {
    vl = l;// 单个volatile变量的写
  }

  public void getAndIncrement() {
    vl++;// 复合(多个)volatile变量的读/写
  }

  public long get() {
    return vl;// 单个volatile变量的读
  }
}
