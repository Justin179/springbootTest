package com.justin.springboottest.designpatterns2.behavioral.observer;

import java.util.UUID;

public interface Observer {
    void update(int state);
}

class ConcreteObserver implements Observer {

    public final UUID id = UUID.randomUUID();

    @Override
    public void update(int state) {
        System.out.println(id + " 狀態更新為: " + state);
        // 在此處處理狀態更新的邏輯
    }
}

