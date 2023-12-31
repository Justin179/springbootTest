package com.justin.springboottest.designpatterns2.structural.flyweight;

import java.util.Random;

public class Client {
    private static final String colors[] = { "Red", "Green", "Blue" };
    private static final Random random = new Random();

    public static void main(String[] args) {
        for(int i = 0; i < 20; ++i) {
            Circle circle = (Circle)ShapeFactory.getCircle(getRandomColor());
            circle.draw();
        }
    }

    private static String getRandomColor() {
        return colors[random.nextInt(colors.length)];
    }
}
