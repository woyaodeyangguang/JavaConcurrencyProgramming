package com.test.chapter3JavaMemoryModel;

/**
 * volatile变量的写-读可以实现线程之间的通信.
 * 从内存语义的角度来说，volatile的写-读与锁的释放-获取具有相同的内存效果.
 *
 * 假设线程A执行writer() 方法之后，线程B执行reader()方法
 * 根据Happens-Before规则，这个过程建立的happens-before关系可以分为3类
 * 1) 根据程序次序规则，1 happens-before 2; 3 happens-before 4;
 * 2) 根据volatile规则 2 happens-before 3;
 * 3) 根据happens-before的传递性规则，1 happens-before 4;
 */
public class VolatileExample {
  int a = 0;
  volatile boolean flag = false;

  public void writer() {
    a = 1;                // 1
    flag = true;          // 2
  }

  public void reader() {
    if(flag) {            // 3
      int i = a;          // 4
    }
  }
}
