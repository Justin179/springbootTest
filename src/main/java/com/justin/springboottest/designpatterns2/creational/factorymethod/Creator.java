package com.justin.springboottest.designpatterns2.creational.factorymethod;
/*
Creator 是一個工廠介面，ConcreteCreatorA 和 ConcreteCreatorB 是實際的工廠類別，用來創建相應的產品。
 */
// 工廠介面
interface Creator {
    Product createProduct();
}

// 具體工廠 A
class ConcreteCreatorA implements Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProductA();
    }
}

// 具體工廠 B
class ConcreteCreatorB implements Creator {
    @Override
    public Product createProduct() {
        return new ConcreteProductB();
    }
}
