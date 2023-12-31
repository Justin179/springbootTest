package com.justin.springboottest.designpatterns.behavioral.observer;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

// maintain a list of interested parties and notify them whenever the events are available
public class EventManager {
  Map<String, ArrayList<EventListener>> listeners = new HashMap<>();

  // one operation correspond to a list of subscribers
  public EventManager(String ... operations) {
    for (String op : operations) {
      listeners.put(op, new ArrayList<>());
    }
  }

  // check the existing list, if not subscribed, add to the list
  public void subscribe(String event, EventListener listener) {
    ArrayList<EventListener> users = listeners.get(event);
    if (!users.contains(listener)) {
      users.add(listener);
    }
  }

  // check the existing list, if subscribed, remove from the list
  public void unsubscribe(String event, EventListener listener) {
    ArrayList<EventListener> users = listeners.get(event);
    if (users.contains(listener)) {
      users.remove(listener);
    }
  }

  // 一次通知一個群組(event)
  public void notify(String event, String file) {
    ArrayList<EventListener> users = listeners.get(event);
    for (EventListener user : users) {
      user.notify(event, file);
    }
  }
}
