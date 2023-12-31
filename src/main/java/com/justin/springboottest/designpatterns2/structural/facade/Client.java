package com.justin.springboottest.designpatterns2.structural.facade;

/*
Facade Pattern 是一種常見的設計模式，Facade提供了一個簡單的接口，封裝了一個複雜的子系統，使其更容易使用
 Facade 中的 methodA 和 methodB 方法會簡單地調用子系統中對應的方法，從而隱藏了子系統的複雜性，使得使用起來更加簡單和直觀。
 */
public class Client {
    public static void main(String[] args) {
        Facade facade = new Facade();

        // 使用 Facade 提供的簡單接口來操作子系統
        facade.methodA();
        facade.methodB();
    }
}
