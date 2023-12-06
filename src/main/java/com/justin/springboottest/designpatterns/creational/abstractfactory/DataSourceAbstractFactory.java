package com.justin.springboottest.designpatterns.creational.abstractfactory;

public interface DataSourceAbstractFactory {
  Service createService();
  Response createResponse();
}
