package com.justin.springboottest.designpatterns.creational.singleton;
public class SingletonLazy {
  private static SingletonLazy lazyInstance;

  private SingletonLazy() {

  }

  // 所謂Lazy 就是真的有人用了，才初始化
  public static SingletonLazy getInstance() {
    if(lazyInstance == null) {
      lazyInstance = new SingletonLazy();
    }

    return lazyInstance;
  }
}