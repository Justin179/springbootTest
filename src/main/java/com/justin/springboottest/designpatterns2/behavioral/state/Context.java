package com.justin.springboottest.designpatterns2.behavioral.state;

// Context 類別持有當前狀態，並執行相對應的行為
public class Context {
    private State currentState; // 保存當前狀態的參考

    // 設置新的狀態
    public void setState(State state) {
        this.currentState = state;
    }

    // Context 呼叫當前狀態的方法
    public void request() {
        currentState.handle();
    }
}

