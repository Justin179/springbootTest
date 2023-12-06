package com.justin.springboottest.designpatterns.creational.abstractfactory;

public class NetworkResponse implements Response {

  @Override
  public String getResponse() {
    return "Network response";
  }
}
