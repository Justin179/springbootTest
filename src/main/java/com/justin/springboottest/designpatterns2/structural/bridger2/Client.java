package com.justin.springboottest.designpatterns2.structural.bridger2;

// 通過這種方法，你可以輕鬆地新增新的顏色或者形狀，而無需修改現有的代碼。
public class Client {
    public static void main(String[] args) {
        Shape square = new Square(new Red());
        Shape triangle = new Triangle(new Blue());
        System.out.println(square.draw());
        System.out.println(triangle.draw());
    }
}


interface Color {
    String fill();
}

class Blue implements Color {
    @Override
    public String fill() {
        return "Color is Blue";
    }
}

class Red implements Color {
    @Override
    public String fill() {
        return "Color is Red";
    }
}

abstract class Shape {
    protected Color color;
    public Shape(Color color) {
        this.color = color;
    }
    abstract public String draw();
}

class Square extends Shape {
    public Square(Color color) {
        super(color);
    }
    @Override
    public String draw() {
        return "Square drawn. " + color.fill();
    }
}

class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }
    @Override
    public String draw() {
        return "Triangle drawn. " + color.fill();
    }
}


