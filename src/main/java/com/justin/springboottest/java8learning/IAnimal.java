package com.justin.springboottest.java8learning;

public interface IAnimal {
    default void breath(){
        System.out.println("breath!");
    };
}


class DefaultMethodTest implements IAnimal {
    public static void main(String[] args) {
        DefaultMethodTest defaultMethod = new DefaultMethodTest();
        defaultMethod.breath();
    }

}
