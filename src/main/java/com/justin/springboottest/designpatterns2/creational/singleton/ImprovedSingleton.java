package com.justin.springboottest.designpatterns2.creational.singleton;

public class ImprovedSingleton {
    private static ImprovedSingleton instance = null;

    private ImprovedSingleton() {}

    // 這種方式在實例為 null 時才使用同步。這個方式在第一次創建實例後就不再需要同步，提高了效能。
    public static ImprovedSingleton getInstance() {

        if (instance == null) {
            synchronized (ImprovedSingleton.class) {

                if (instance == null) {
                    instance = new ImprovedSingleton();
                }

            }
        }

        return instance;
    }
}
