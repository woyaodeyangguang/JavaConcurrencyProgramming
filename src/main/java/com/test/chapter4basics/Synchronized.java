package com.test.chapter4basics;

/**
 * Created by Admin on 2017/11/27.
 */
public class Synchronized {

  public static void main(String[] args) {
    // 对Synchronized Class对象加锁
    synchronized (Synchronized.class) {

    }
    // 静态同步方法,对Synchronized Class对象进行加锁
    m();
  }

  public static void m() {}
}
