package com.test.chapter4basics;

import java.util.concurrent.TimeUnit;

/**
 * ThreadLocal,即线程变量.
 * 是以一个ThreadLocal对象为键、任意对象为值的粗出结构
 * 该结构被附带再线程上，也就是说一个线程可以根据一个ThreadLocal对象查询到绑定在这个线程的值
 *
 * Profile可以被复用在方法调用耗时统计上,在方法入口前执行begin，在方法执行后调用end方法，好处就是
 * 两个方法的调用不在一个方法或者类中,比如在AOP中，可以在方法的调用前的切入点执行begin方法,而在方法
 * 调用后切入点执行end()方法，这样依旧可以获得方法的执行耗时.
 */
public class Profiler {

  // 第一次get()方法调用时会进行初始化(如果set方法没有调用),每个线程都会调用一次
  private static final ThreadLocal<Long> TIME_THREADLOCAL = new ThreadLocal<Long>() {
    @Override
    protected Long initialValue() {
      return super.initialValue();
    }
  };

  public static final void begin() {
    TIME_THREADLOCAL.set(System.currentTimeMillis());
  }

  public static final long end() {
    return System.currentTimeMillis() - TIME_THREADLOCAL.get();
  }

  public static void main(String[] args) throws InterruptedException {
    Profiler.begin();
    TimeUnit.SECONDS.sleep(1);
    System.out.println("Cost " + Profiler.end() + "mills");
  }
}
