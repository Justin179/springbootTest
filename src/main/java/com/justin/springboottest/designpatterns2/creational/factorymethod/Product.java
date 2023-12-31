package com.justin.springboottest.designpatterns2.creational.factorymethod;

/*
Product 代表一個產品的基本行為，ConcreteProductA 和 ConcreteProductB 是實際的產品類別。
 */
// 產品介面
interface Product {
    void doSomething();
}

// 具體產品 A
class ConcreteProductA implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product A is doing something.");
    }
}

// 具體產品 B
class ConcreteProductB implements Product {
    @Override
    public void doSomething() {
        System.out.println("Product B is doing something.");
    }
}
