package com.justin.springboottest.designpatterns.creational.singleton;

public class SingletonBillPugh {

  private SingletonBillPugh() {

  }

  // inner class?
  private static class InnerStaticClass {
    private static final SingletonBillPugh billPughInstance = new SingletonBillPugh();
  }

  public static SingletonBillPugh getInstance() {
    return InnerStaticClass.billPughInstance;
  }
  
}
