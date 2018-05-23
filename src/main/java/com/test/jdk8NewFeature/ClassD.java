package com.test.jdk8NewFeature;

/**
 * Created by Admin on 2018/2/6.
 */
public class ClassD extends ClassC {

  @Override
  protected void method() {
    System.out.println("ClassD method");
  }

  public static void main(String[] args) {
    ClassD classD = new ClassD();
    classD.method();
  }
}
