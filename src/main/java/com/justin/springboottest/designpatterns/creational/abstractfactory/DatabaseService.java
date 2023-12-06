package com.justin.springboottest.designpatterns.creational.abstractfactory;

public class DatabaseService implements Service {

  @Override
  public String runService() {
    return "Database service running";
  }
  
}
