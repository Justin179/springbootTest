package com.justin.springboottest.designpatterns2.creational.factorymethod2;

public interface Factory {
    Product createProduct();
}

class ConcreteFactory implements Factory {
    @Override
    public Product createProduct() {
        return new ConcreteProduct();
    }
}
