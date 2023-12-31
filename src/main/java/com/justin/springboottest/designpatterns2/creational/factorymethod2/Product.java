package com.justin.springboottest.designpatterns2.creational.factorymethod2;

public interface Product {
    void print();
}

class ConcreteProduct implements Product {
    @Override
    public void print() {
        System.out.println("ConcreteProduct");
    }
}
