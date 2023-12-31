package com.justin.springboottest.designpatterns2.creational.abstractfactory;

public interface ProductA {
    void doSomething();
}

class ConcreteProductA1 implements ProductA {
    @Override
    public void doSomething() {
        System.out.println("Product A1 is doing something.");
    }
}

// 具体产品A2
class ConcreteProductA2 implements ProductA {
    @Override
    public void doSomething() {
        System.out.println("Product A2 is doing something.");
    }
}
