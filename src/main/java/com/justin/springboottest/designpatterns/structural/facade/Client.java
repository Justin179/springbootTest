package com.justin.springboottest.designpatterns.structural.facade;

public class Client {
  public static void main(String[] args) {
    NetworkAccessFacade access = new NetworkAccessFacade();
    access.communicate();
  }
}
