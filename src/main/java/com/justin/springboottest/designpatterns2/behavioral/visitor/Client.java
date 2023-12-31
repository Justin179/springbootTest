package com.justin.springboottest.designpatterns2.behavioral.visitor;

public class Client {
    public static void main(String[] args) {
        Element elementA = new ElementA();
        Element elementB = new ElementB();

        Visitor visitor = new ConcreteVisitor();

        elementA.accept(visitor); // 呼叫 accept 並觸發相應的 visit 方法
        elementB.accept(visitor);
    }
}
