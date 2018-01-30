package com.test.exercise;


/**
 * interrupt.
 */
public class TestInterrupt {

  public static void main(String[] args) throws InterruptedException {
    Thread runnable = new Thread(new MyCustomRunnable(), "MyCustomRunnable");
    runnable.start();
    Thread.currentThread().sleep(3000);
    runnable.interrupt();
  }

  private static class MyCustomRunnable implements Runnable {

    @Override
    public void run() {
      String curThreadName = Thread.currentThread().getName();
      while(!Thread.currentThread().isInterrupted()) {
        System.out.println(curThreadName + "- Hello World");
      }
      System.out.println(curThreadName + "-线程结束");
    }
  }
}
