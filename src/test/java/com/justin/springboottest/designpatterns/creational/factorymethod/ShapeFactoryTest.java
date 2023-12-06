package com.justin.springboottest.designpatterns.creational.factorymethod;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ShapeFactoryTest {

    @Test
    void getFood() {
        ShapeFactory factory = new ShapeFactory();
        Shape roundFood = factory.getFood("Round");
        Shape cylinderFood = factory.getFood("Cylinder");

        assertEquals("Round", roundFood.getShape());
        assertEquals("Cylinder", cylinderFood.getShape());
    }
}