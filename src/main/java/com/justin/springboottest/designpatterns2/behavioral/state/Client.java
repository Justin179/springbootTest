package com.justin.springboottest.designpatterns2.behavioral.state;

public class Client {
    public static void main(String[] args) {
        // 創建一個 Context
        Context context = new Context();

        // 創建幾個具體狀態
        State stateA = new ConcreteStateA();
        State stateB = new ConcreteStateB();

        // 設置初始狀態為 stateA
        context.setState(stateA);
        // 呼叫 Context 的 request 方法
        context.request(); // 輸出：This is ConcreteStateA.

        // 狀態切換至 stateB
        context.setState(stateB);
        // 再次呼叫 request 方法
        context.request(); // 輸出：This is ConcreteStateB.
    }
}

