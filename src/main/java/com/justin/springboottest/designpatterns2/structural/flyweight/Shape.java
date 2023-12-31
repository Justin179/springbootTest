package com.justin.springboottest.designpatterns2.structural.flyweight;

public interface Shape {
    void draw();
}

// 實作具體的享元類別
class Circle implements Shape {
    private String color;

    public Circle(String color) {
        this.color = color;
    }

    public void draw() {
        System.out.println("Drawing circle with color: " + color);
    }
}