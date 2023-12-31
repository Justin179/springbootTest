package com.justin.springboottest.designpatterns2.creational.abstractfactory;

public class Client {
    public static void main(String[] args) {
        // 要產品找工廠生產(即new)，client不要自己手動生產(即new)產品

        // 使用工厂1创建产品族1
        AbstractFactory factory1 = new ConcreteFactory1();
        ProductA productA1 = factory1.createProductA();
        ProductB productB1 = factory1.createProductB();

        productA1.doSomething();
        productB1.performAction();

        // 使用工厂2创建产品族2
        AbstractFactory factory2 = new ConcreteFactory2();
        ProductA productA2 = factory2.createProductA();
        ProductB productB2 = factory2.createProductB();

        productA2.doSomething();
        productB2.performAction();
    }
}
