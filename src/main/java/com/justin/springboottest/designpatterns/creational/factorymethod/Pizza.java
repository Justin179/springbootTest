package com.justin.springboottest.designpatterns.creational.factorymethod;

public class Pizza implements Shape {
  @Override
  public String getShape() {
    return "Round";
  }
}
