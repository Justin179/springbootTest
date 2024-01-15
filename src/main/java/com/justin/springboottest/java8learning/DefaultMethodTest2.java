package com.justin.springboottest.java8learning;

interface IDonkey{
    default void run() {
        System.out.println("IDonkey run");
    }
}

interface IHorse {

    default void run(){
        System.out.println("Horse run");
    }

}

public class DefaultMethodTest2 implements IDonkey,IHorse {
    public static void main(String[] args) {
        DefaultMethodTest2 defaultMethod = new DefaultMethodTest2();
        defaultMethod.run();
    }

    @Override
    public void run() {
        IHorse.super.run();
    }

}
