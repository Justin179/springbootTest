package com.justin.springboottest.designpatterns2.creational.singleton;

/*
    這種方法的問題是，在多執行緒環境下，可能會出現競爭條件（race condition），
    多個執行緒同時認為 instance 是 null，然後各自創建一個實例。
 */
public class Singleton {
    private static Singleton instance = null;

    private Singleton() {}

    public static Singleton getInstance() {
        if (instance == null) {
            instance = new Singleton();
        }
        return instance;
    }
}

