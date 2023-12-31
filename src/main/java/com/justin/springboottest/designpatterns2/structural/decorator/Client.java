package com.justin.springboottest.designpatterns2.structural.decorator;

/*
裝飾器模式（Decorator Pattern）是一種設計模式，它允許你在不更改現有程式碼的情況下，動態地將新功能添加到對象上。
在 Java 中實現裝飾器模式可以讓你按需擴展對象的功能。
假設你有一個基本介面或抽象類別，然後你想要在其基礎上添加一些額外的功能，但又不想直接修改它。這時可以使用裝飾器模式。

這裡的 PizzaDecorator 是裝飾器的抽象類別，Cheese 是一個實際的裝飾器類別，它擴展了 PizzaDecorator 並添加了額外的配料（在這個例子中是奶酪）。
現在，讓我們使用這些類別來創建一個 PlainPizza，然後用 Cheese 裝飾它：

裝飾器模式的核心概念
動態地添加額外的配料而不必修改原始的 PlainPizza 類別
 */
public class Client {
    public static void main(String[] args) {
        Pizza plainPizza = new PlainPizza();
        System.out.println(plainPizza.getDescription()); // 輸出：Plain Pizza
        System.out.println(plainPizza.getCost()); // 輸出：5.0

        Pizza pizzaWithCheese = new Cheese(plainPizza);
        System.out.println(pizzaWithCheese.getDescription()); // 輸出：Plain Pizza, with Cheese
        System.out.println(pizzaWithCheese.getCost()); // 輸出：6.5

    }
}
