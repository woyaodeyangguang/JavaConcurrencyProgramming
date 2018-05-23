package com.test.jdk8NewFeature;

import java.util.Arrays;
import java.util.List;

/**
 * Created by Admin on 2018/2/6.
 */
public interface InterfaceC extends InterfaceA, InterfaceB{

  List<String> list = Arrays.asList();

  public abstract void method();
  void InterfaceC();
}
