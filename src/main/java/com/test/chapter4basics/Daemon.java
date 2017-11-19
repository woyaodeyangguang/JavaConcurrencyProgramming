package com.test.chapter4basics;

import com.test.utils.SleepUtils;

/**
 * Daemon线程是一种支持性线程，主要被用作线程中后台调度以及支持性工作
 * Daemon属性需要在启动线程之前设置，不能在线程启动之后设置
 * 当虚拟机中不存在非Daemon线程时，虚拟机将会退出，因此在构建Daemon线程时，不能依靠finally来
 * 确保执行关闭或者清理资源的逻辑
 *
 * 线程主要分为：用户线程和守护线程
 * 守护线程"比较次要",可以理解为一种运行在后台的服务线程,比如时钟处理线程、idle线程、
 * 垃圾收集线程等都是daemon线程
 */
public class Daemon {

  public static void main(String[] args) {
    Thread thread = new Thread(new DaemonRunner(), "DaemonRunner");
    // 当只剩下守护线程时,JVM就会退出.如果还有其他的任一用户线程还在,JVM就不会退出
    thread.setDaemon(true);
    thread.start();
  }

  static class DaemonRunner implements Runnable {

    @Override
    public void run() {
      try {
        SleepUtils.second(10);
      } finally {
      }
    }
  }
}
