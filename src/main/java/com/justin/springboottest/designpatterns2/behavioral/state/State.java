package com.justin.springboottest.designpatterns2.behavioral.state;

// State 接口表示不同狀態的通用行為
public interface State {
    void handle(); // 可以是一個或多個狀態處理方法
}

// 具體狀態類別實現 State 介面
class ConcreteStateA implements State {
    @Override
    public void handle() {
        // 實作 ConcreteStateA 的行為
        System.out.println("This is ConcreteStateA.");
    }
}

class ConcreteStateB implements State {
    @Override
    public void handle() {
        // 實作 ConcreteStateB 的行為
        System.out.println("This is ConcreteStateB.");
    }
}

// 可以有其他具體狀態類別，視需求而定
