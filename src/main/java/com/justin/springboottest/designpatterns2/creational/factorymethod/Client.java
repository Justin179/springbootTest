package com.justin.springboottest.designpatterns2.creational.factorymethod;

/*
你可以使用這個模式根據需要創建不同類型的產品，例如：
這樣做可以讓你根據需要動態地創建產品，同時保持彈性和可擴展性。
 */
public class Client {
    public static void main(String[] args) {
        Creator creatorA = new ConcreteCreatorA();
        Product productA = creatorA.createProduct();
        productA.doSomething();

        Creator creatorB = new ConcreteCreatorB();
        Product productB = creatorB.createProduct();
        productB.doSomething();
    }
}
