package com.justin.springboottest.designpatterns.creational.factorymethod;





public class ShapeFactoryTest {
  ShapeFactory factory = new ShapeFactory();
  Shape roundFood = factory.getFood("Round");
  Shape cylinderFood = factory.getFood("Cylinder");
  
//  @Test
//  void factoryMethodTest() {
//    assertEquals("Round", roundFood.getShape());
//    assertEquals("Cylinder", cylinderFood.getShape());
//  }
}
