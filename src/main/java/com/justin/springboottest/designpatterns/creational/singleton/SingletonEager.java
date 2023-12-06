package com.justin.springboottest.designpatterns.creational.singleton;

public class SingletonEager {

  private static SingletonEager eagerInstance = new SingletonEager();

  private SingletonEager() {
  }

  // only this method is open to access
  public static SingletonEager getEagerInstance() {
    return eagerInstance;
  }

}