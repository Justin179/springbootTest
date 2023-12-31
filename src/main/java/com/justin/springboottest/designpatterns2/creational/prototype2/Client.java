package com.justin.springboottest.designpatterns2.creational.prototype2;

public class Client {
    public static void main(String[] args) {
        Shape shape = new Shape();
        shape.setType("Circle");

        Shape clonedShape = (Shape) shape.clone();
        System.out.println("Shape : " + clonedShape.getType());
    }
}
