package com.justin.springboottest.designpatterns.behavioral.strategy;

public class UppercaseStrategy implements PrintStrategy {

  @Override
  public String formatString(String input) {
    return input.toUpperCase();
  }
  
}
