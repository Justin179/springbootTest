package com.justin.springboottest.designpatterns2.creational.abstractfactory;

public interface ProductB {
    void performAction();
}

class ConcreteProductB1 implements ProductB {
    @Override
    public void performAction() {
        System.out.println("Product B1 is performing an action.");
    }
}

// 具体产品B2
class ConcreteProductB2 implements ProductB {
    @Override
    public void performAction() {
        System.out.println("Product B2 is performing an action.");
    }
}
