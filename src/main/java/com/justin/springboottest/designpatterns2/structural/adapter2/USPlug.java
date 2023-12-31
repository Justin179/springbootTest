package com.justin.springboottest.designpatterns2.structural.adapter2;

// 美國插頭接口
public interface USPlug {
    void plugIn();
}

// 美國插頭實現
class USDevice implements USPlug {
    @Override
    public void plugIn() {
        System.out.println("Plugged in US device.");
    }
}
