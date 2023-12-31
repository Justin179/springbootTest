package com.justin.springboottest.designpatterns2.creational.factorymethod2;

public class Client {
    public static void main(String[] args) {
        // 不要直接new Product，而是透過factory去生產
        Factory factory = new ConcreteFactory();
        Product product = factory.createProduct();
        product.print();
    }
}
