package com.test.chapter3JavaMemoryModel;

/**
 * 多线程环境下重排序会改变多线程程序的执行结果.
 */
public class RecorderExample {
  int a = 0;
  boolean flag = false;

  public void writer() {
    a = 1;
    flag = true;
  }

  public void reader() {
    if(flag) {
      int i = a * a;
    }
  }

}
