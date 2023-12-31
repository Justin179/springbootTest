package com.justin.springboottest.designpatterns2.behavioral.memoto2;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Originator originator = new Originator(); // 本體
        CareTaker careTaker = new CareTaker(); // have a Memoto list

        originator.setState("State #1");
        originator.setState("State #2");
        careTaker.add(originator.saveStateToMemento()); // #2
        originator.setState("State #3");
        careTaker.add(originator.saveStateToMemento()); // #3
        originator.setState("State #4");
        System.out.println("Current State: " + originator.getState()); // print #4
        originator.getStateFromMemento(careTaker.get(0));
        System.out.println("First saved State: " + originator.getState()); // print #2
        originator.getStateFromMemento(careTaker.get(1));
        System.out.println("Second saved State: " + originator.getState()); // print #3
    }
}

class CareTaker {
    private final List<Memento> mementoList = new ArrayList<>();

    public void add(Memento state) {
        mementoList.add(state);
    }

    public Memento get(int index) {
        return mementoList.get(index);
    }
}

// 本體一次就一個狀態
class Originator {
    private String state;

    public void setState(String state) {
        this.state = state;
    }
    public String getState() {
        return state;
    }

    public Memento saveStateToMemento() {
        return new Memento(state);
    }

    public void getStateFromMemento(Memento memento) {
        state = memento.getState();
    }
}

class Memento {
    private final String state;

    public Memento(String stateToSave) {
        state = stateToSave;
    }

    public String getState() {
        return state;
    }
}





