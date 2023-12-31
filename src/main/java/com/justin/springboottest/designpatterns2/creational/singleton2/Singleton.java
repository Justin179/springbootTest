package com.justin.springboottest.designpatterns2.creational.singleton2;

public class Singleton {
    private Singleton() {} // 私有的構造方法

    private class SingletonHolder {
        // assigned only once
        private static final Singleton INSTANCE = new Singleton();
    }

    public static Singleton getInstance() {
        return SingletonHolder.INSTANCE;
    }
}

