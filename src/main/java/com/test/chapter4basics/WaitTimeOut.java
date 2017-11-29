package com.test.chapter4basics;

/**
 * 等待超时模式.
 */
public class WaitTimeOut {

  private String result = null;

  public synchronized Object get(long mills) throws InterruptedException {
    // 超时时间
    long future = System.currentTimeMillis() + mills;
    // 等待持续时间
    long remaining = mills;
    while(result == null && remaining > 0 ) {
      wait(remaining);
      remaining = future - System.currentTimeMillis();
    }
    return result;
  }

}
