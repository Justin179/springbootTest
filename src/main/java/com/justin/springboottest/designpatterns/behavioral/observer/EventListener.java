package com.justin.springboottest.designpatterns.behavioral.observer;

public interface EventListener {
  void notify(String eventType, String file);
}
