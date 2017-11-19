package com.test.chapter4basics;

import com.test.utils.SleepUtils;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;
import javafx.scene.input.DataFormat;

/**
 * Created by Admin on 2017/11/19.
 */
public class Deprecated {

  public static void main(String[] args) throws InterruptedException {
    DateFormat format = new SimpleDateFormat("HH:mm:ss");
    Thread printThread = new Thread(new Runner(), "PrintThread");
    printThread.setDaemon(true);
    printThread.start();
    TimeUnit.SECONDS.sleep(3);
    // 将printThread进行暂停,输出内容停止
    printThread.suspend();
    System.out.println("main suspend printThread at " + format.format(new Date()));
    TimeUnit.SECONDS.sleep(3);
    // 将PrintThread进行恢复,输出内容继续
    printThread.resume();
    System.out.println("main resume printThread at " + format.format(new Date()));
    // 将PrintThread进行终止,输出内容中止
    printThread.stop();
    System.out.println("main stop printThread at " + format.format(new Date()));
    TimeUnit.SECONDS.sleep(3);
  }


  static class Runner implements Runnable {

    @Override
    public void run() {
      DateFormat format = new SimpleDateFormat("HH:mm:ss");
      while(true) {
        System.out.println(Thread.currentThread().getName() + " Run at " +
            format.format(new Date()));
        SleepUtils.second(1);
      }
    }
  }
}
