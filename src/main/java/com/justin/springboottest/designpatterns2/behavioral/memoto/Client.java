package com.justin.springboottest.designpatterns2.behavioral.memoto;

import java.util.ArrayList;
import java.util.List;
/*
    範例演示了如何使用 Memeto Pattern 在不破壞原始對象封裝的情況下保存和恢復狀態。
    它允許在不破壞封裝的情況下捕捉一個對象的內部狀態，並在稍後恢復它。在 Java 中實現 Memeto Pattern 涉及到將對象的狀態保存到 Memento（備忘錄）中，並且能夠恢復到先前的狀態。

 */
public class Client {
    public static void main(String[] args) {
        // 創建原始對象
        Originator originator = new Originator();
        originator.setState("State 1");

        // 創建管理者
        Caretaker caretaker = new Caretaker(); // 內有 List<Memento> mementoList

        // 保存當前狀態到備忘錄
        caretaker.addMemento(originator.saveStateToMemento());


        // 改變原始對象的狀態
        originator.setState("State 2");
        // 再次保存狀態到備忘錄
        caretaker.addMemento(originator.saveStateToMemento());

        // 恢復到先前的狀態
        originator.getStateFromMemento(caretaker.getMemento(0));
        System.out.println("Current State: " + originator.getState()); // 輸出：Current State: State 1
    }
}

// Caretaker 類別負責管理 Memento（備忘錄）
class Caretaker {
    private final List<Memento> mementoList = new ArrayList<>();

    public void addMemento(Memento memento) {
        mementoList.add(memento);
    }

    public Memento getMemento(int index) {
        return mementoList.get(index);
    }
}

// 原始對象類別
class Originator {
    private String state; // 原始對象的狀態

    public void setState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    // 創建 Memento 以保存狀態
    public Memento saveStateToMemento() {
        return new Memento(state); // save the current state
    }

    // 從 Memento 中恢復狀態
    public void getStateFromMemento(Memento memento) {
        state = memento.getSavedState();
    }
}

// Memento（備忘錄）類別保存原始對象的狀態
class Memento {
    private final String state; // 要保存的狀態

    public Memento(String stateToSave) {
        state = stateToSave;
    }

    public String getSavedState() {
        return state;
    }
}




