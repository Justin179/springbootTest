package com.justin.springboottest.designpatterns2.behavioral.observer;

/*
    觀察者模式（Observer Pattern）是一種常見的設計模式，用於定義物件之間的一對多依賴關係，
    使得當一個物件的狀態改變時，其相依物件都能收到通知並自動更新。

    這個例子中，ConcreteSubject 是主題，ConcreteObserver 是觀察者。
    當 ConcreteSubject 的狀態改變時，所有註冊的觀察者都會收到通知並執行 update 方法。
    這樣就建立了一個基本的觀察者模式。
 */
public class Client {
    public static void main(String[] args) {
        ConcreteSubject subject = new ConcreteSubject();

        ConcreteObserver observer1 = new ConcreteObserver();
        ConcreteObserver observer2 = new ConcreteObserver();

        subject.registerObserver(observer1);
        subject.registerObserver(observer2);

        // 當狀態改變時，通知觀察者 notifyObservers()
        subject.setState(5);
        subject.setState(10);

        // 移除觀察者
        subject.removeObserver(observer1);

        // 再次狀態改變時，只有 observer2 會收到通知
        subject.setState(15);
    }
}

