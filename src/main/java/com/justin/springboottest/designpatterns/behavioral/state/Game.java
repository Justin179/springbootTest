package com.justin.springboottest.designpatterns.behavioral.state;

public class Game {

  public State state = new WelcomeScreenState(this);

  public void changeState(State state) {
    this.state = state;
  }
  
}
