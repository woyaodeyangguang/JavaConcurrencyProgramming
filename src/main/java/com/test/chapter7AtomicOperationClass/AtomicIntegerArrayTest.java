package com.test.chapter7AtomicOperationClass;

import java.util.concurrent.atomic.AtomicIntegerArray;

/**
 * 原子更新数组
 */
public class AtomicIntegerArrayTest {
  private static int[] value = new int[] {1, 2};
  // AtomicInteger 会将数组进行复制一份
  private static AtomicIntegerArray array = new AtomicIntegerArray(value);

  public static void main(String[] args) {
    array.getAndSet(0, 3);
    System.out.println(array.get(0));
    System.out.println(value[0]);
  }
}
