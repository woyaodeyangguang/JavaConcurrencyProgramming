package com.test.chapter4basics;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * 模拟客户端ConnectionRunner获取、使用、最后释放连接的过程.
 */
public class ConnectionPoolTest {
  private static ConnectionPool pool = new ConnectionPool(10);
  // 保证所有ConnectionRunner能够同时开始
  private static CountDownLatch start = new CountDownLatch(1);
  // main线程将会等待ConnectionRunner结束后才能继续执行
  private static CountDownLatch end;

  public static void main(String[] args) throws InterruptedException {
    // 线程数量，可以修改线程数量进行观察
    int threadCount = 10;
    end = new CountDownLatch(threadCount);
    int count = 20;
    AtomicInteger got = new AtomicInteger();
    AtomicInteger notGot = new AtomicInteger();
    for (int i = 0; i < threadCount; i++) {
      Thread thread = new Thread(new ConnectionRunner(count, got, notGot),
          "ConnectionRunnerThread");
      thread.start();
    }
    start.countDown();
    end.await();
    System.out.println("total invoke :" + (threadCount * count));
    System.out.println("got connection :" + got);
    System.out.println("not got connection: " + notGot);

  }


  private static class ConnectionRunner implements Runnable {

    private int count;
    private AtomicInteger got;
    private AtomicInteger notGot;

    public ConnectionRunner(int count, AtomicInteger got, AtomicInteger notGot){
      this.count = count;
      this.got = got;
      this.notGot = notGot;
    }

    @Override
    public void run() {
      try {
        start.await();
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
      while (count > 0) {
        try {
          // 从线程池中获取连接，如果1000ms内无法获取连接，将返回null
          // 分别统计连接获取的数量got和未获取的数量notGot
          Connection connection = pool.fectConnection(1000);
          if(connection != null) {
            try {
              connection.createStatement();
              connection.commit();
            } catch (SQLException e) {
              e.printStackTrace();
            } finally {
              pool.releaseConnection(connection);
              got.incrementAndGet();
            }
          } else {
            notGot.incrementAndGet();
          }
        } catch (InterruptedException e) {
          e.printStackTrace();
        } finally {
          count--;
        }
      }
    }


  }
}
