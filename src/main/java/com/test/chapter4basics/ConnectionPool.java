package com.test.chapter4basics;

import java.sql.Connection;
import java.util.LinkedList;

/**
 * 简单的数据库连接池
 */
public class ConnectionPool {
  private LinkedList<Connection> pool = new LinkedList<>();

  public ConnectionPool(int initialSize) {
    if(initialSize > 0) {
      for (int i = 0; i < initialSize; i++) {
        pool.add(ConnectionDriver.createConnection());
      }
    }
  }

  public void releaseConnection(Connection connection) {
    if(connection != null) {
      synchronized (pool) {
        // 连接释放后需要进行通知,这样其他消费者才能够感知到连接池已经归还了一个连接
        pool.addLast(connection);
        pool.notifyAll();
      }
    }
  }

  // 在mills内无法获取连接,将返回null
  public Connection fectConnection(long mills) throws InterruptedException {
    synchronized (pool) {
      if(mills <= 0) {
        while(pool.isEmpty()) {
          pool.wait();
        }
        return pool.removeFirst();
      } else {
        long future = System.currentTimeMillis() + mills;
        long remaing = mills;
        while(pool.isEmpty() && remaing > 0) {
          pool.wait(remaing);
          remaing = future - System.currentTimeMillis();
        }
      }
      Connection result = null;
      if(!pool.isEmpty()) {
        result = pool.removeFirst();
      }
      return result;
    }
  }





}
