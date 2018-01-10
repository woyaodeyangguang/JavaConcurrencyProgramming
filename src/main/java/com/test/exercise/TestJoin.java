package com.test.exercise;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 2017/12/7.
 */
public class TestJoin {

  public static void main(String[] args) throws InterruptedException {
    Thread thread = new Thread(new Runnable() {
      @Override
      public void run() {
        System.out.println(Thread.currentThread().getName() + " 开始!");
        try {
          TimeUnit.SECONDS.sleep(5);
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + "结束!");
      }
    }, "Thread");
    thread.start();
    System.out.println(Thread.currentThread().getName() + " 开始!");
    // 等待线程死亡后,才能之后后续代码
    thread.join();
    System.out.println(Thread.currentThread().getName() + " 结束!");
  }

}
