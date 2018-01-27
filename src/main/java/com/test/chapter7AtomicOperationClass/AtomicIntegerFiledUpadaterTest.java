package com.test.chapter7AtomicOperationClass;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

/**
 * 原子更新字段类
 */
public class AtomicIntegerFiledUpadaterTest {
  private static AtomicIntegerFieldUpdater<User> atomicIntegerFieldUpdater =
      AtomicIntegerFieldUpdater.newUpdater(User.class, "old");

  public static void main(String[] args) {
    User conan = new User("conan", 10);
    System.out.println(atomicIntegerFieldUpdater.getAndIncrement(conan));
    System.out.println(atomicIntegerFieldUpdater.get(conan));
  }

  public static class User {
    private String name;
    public volatile int old;

    public User(String name, int old) {
      this.name = name;
      this.old = old;
    }

    public String getName() {
      return name;
    }

    public int getOld() {
      return old;
    }
  }
}
