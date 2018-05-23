package com.test.exercise;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList.
 * 顾名思义,write的时候总是需要Copy，也就是任何可变操作(add，set，remove)都会伴随着赋值这个操作
 */
public class TestCopyOnWriteArrayList {
  private static int count = 1000;
  // ArrayList 会出发fast-fail ConcurrentModificationException
  // private static List<Integer> list = new ArrayList<>();

  // LinkedList 会出发fast-fail ConcurrentModificationException
  // private static List<Integer> list = new LinkedList<>();

  // Vector也会抛出ConcurrentModificationException，Vector虽然是线程安全的，但是是一种相对的线程安全
  // 它只能保证增、删、改、查的单个操作一定是原子的，不会被打断。但是组合起来并不能保证线程安全性
  // private static List<Integer> list = new Vector<>();

  private static List<Integer> list =  new CopyOnWriteArrayList<>();

  public static void main(String[] args) {
    for (int i = 0; i < 10; i++) {
      list.add(i);
    }
    Thread readThread = new Thread(new ReadRunnable());
    Thread deleteThread = new Thread(new DeleteRunnable());
    deleteThread.start();
    readThread.start();

  }

  private static class ReadRunnable implements Runnable {
    @Override
    public void run() {
      for(Integer i : list) {
      }
    }
  }

  private static class DeleteRunnable implements Runnable {

    @Override
    public void run() {
      for (int i = 0; i < list.size(); i++) {
        list.remove(i);
      }
    }
  }
}


