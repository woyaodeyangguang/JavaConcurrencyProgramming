package com.test.chapter6ConcurrentCollectionAndFrame;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

/**
 * 使用阻塞队列BlockingQueue来实现生产者消费者模型.
 */
public class ProducerAndConsumer {

  private static BlockingQueue<Product> blockingQueue = new ArrayBlockingQueue<Product>(1);

  private static class Producer implements Runnable {
    @Override
    public void run() {
      try {
        blockingQueue.put(new Product());
        System.out.println("生产者加入商品");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  private static class Consumer implements Runnable {
    @Override
    public void run() {
      try {
        Product product = blockingQueue.take();
        System.out.println("消费者获取商品");
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }
  }

  public static void main(String[] args) {
    new Thread(new Producer()).start();
    new Thread(new Producer()).start();
    new Thread(new Consumer()).start();
    new Thread(new Consumer()).start();
  }

  private static class Product {
  }
}
