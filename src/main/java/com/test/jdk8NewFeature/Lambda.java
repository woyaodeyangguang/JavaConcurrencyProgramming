package com.test.jdk8NewFeature;

import java.util.Arrays;
import java.util.List;

/**
 * jdk1.8中lambda表达式.
 */
public class Lambda {
  private static List<Integer> list = Arrays.asList(10, 2, 4, 5);

  public static void main(String[] args) {
    useCaseThreadLambda();
    useCaseForEachLambda();
  }

  public static void useCaseThreadLambda() {
    new Thread(() -> System.out.println("Hello World")).start();
  }

  /**
   * 打印数组中的所有元素
   */
  public static void useCaseForEachLambda() {
    list.forEach(i -> System.out.println(i));
  }

  public static int[] useCaseToArrayLambda() {
    return list.stream().mapToInt(Integer::intValue).toArray();
  }
}
