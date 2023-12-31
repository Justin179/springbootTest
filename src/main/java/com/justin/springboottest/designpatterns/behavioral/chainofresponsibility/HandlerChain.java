package com.justin.springboottest.designpatterns.behavioral.chainofresponsibility;

public interface HandlerChain {
  String addHandler(String inputHeader);
}
