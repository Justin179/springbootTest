package com.justin.springboottest.designpatterns.creational.abstractfactory;

public class DatabaseResponse implements Response{

  @Override
  public String getResponse() {
    return "Database response";
  }
  
}
