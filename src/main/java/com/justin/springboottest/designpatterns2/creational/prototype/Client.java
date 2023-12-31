package com.justin.springboottest.designpatterns2.creational.prototype;

public class Client {
    public static void main(String[] args) {
        // 创建原型对象
        ConcretePrototype prototype = new ConcretePrototype("Prototype 1");

        // 克隆对象
        // 通过调用 clone() 方法，你可以创建原型对象的副本而不需要使用构造函数。
        ConcretePrototype clonedPrototype = (ConcretePrototype) prototype.clone();

        // 验证克隆是否成功
        System.out.println("Original Prototype Name: " + prototype.getName());
        System.out.println("Cloned Prototype Name: " + clonedPrototype.getName());

        // 修改克隆对象的属性
        clonedPrototype.setName("Cloned Prototype 1");

        // 验证修改是否影响原对象
        System.out.println("Original Prototype Name after Cloning: " + prototype.getName());
        System.out.println("Cloned Prototype Name after Modification: " + clonedPrototype.getName());
    }
}
