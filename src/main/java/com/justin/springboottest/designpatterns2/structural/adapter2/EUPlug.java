package com.justin.springboottest.designpatterns2.structural.adapter2;

// 歐洲插頭接口
public interface EUPlug {
    void plugInEU();
}

// 歐洲插頭實現
class EUDevice implements EUPlug {
    @Override
    public void plugInEU() {
        System.out.println("Plugged in EU device.");
    }
}
