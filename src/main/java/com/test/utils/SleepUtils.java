package com.test.utils;

import java.util.concurrent.TimeUnit;

/**
 * Created by Admin on 2017/11/2.
 */
public class SleepUtils {

  public static final void second(long seconds) {
    try {
      TimeUnit.SECONDS.sleep(seconds);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
