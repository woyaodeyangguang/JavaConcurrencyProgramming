package com.test.jdk8NewFeature;

/**
 *
 */
public class Main {
  private static int a = 1;
  private static int b = 2;

  public static void foo() {
    a = 3;
    try {
      Thread.currentThread().sleep(100);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
    b = 4;
  }

  public static int getA() {
    return a;
  }

  public static int getB() {
    return b;
  }

  public static void main(String[] args) {
    new Thread(() -> System.out.println(getA())).start();
    new Thread(() -> foo()).start();
    new Thread(() -> System.out.println(getB())).start();
  }
}
