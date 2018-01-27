package com.test.chapter7AtomicOperationClass;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by Admin on 2018/1/27.
 */
public class AtomicIntegerTest {

  private static AtomicInteger atomicInteger = new AtomicInteger(1);
  public static void main(String[] args) {
    System.out.println(atomicInteger.getAndIncrement());
    System.out.println(atomicInteger.get());
  }
}
