package com.justin.springboottest.designpatterns2.creational.singleton2;

public class Client {
    public static void main(String[] args) {
        Singleton instance = Singleton.getInstance();
        System.out.println(instance);
    }
}
