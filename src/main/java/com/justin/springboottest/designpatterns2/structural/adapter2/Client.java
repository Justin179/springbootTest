package com.justin.springboottest.designpatterns2.structural.adapter2;

public class Client {
/*
透過 USDeviceEUAdapter 這個轉接器，我們能夠讓原本使用美國插頭的設備在歐洲使用，這就是 Adapter Pattern 的實際應用之一。
 */
    public static void main(String[] args) {
        // 在歐洲使用美國插頭的設備
        USPlug usDevice = new USDevice();
        EUPlug adapter = new USDeviceEUAdapter(usDevice);

        // 在歐洲插頭上插入美國設備（使用了轉接器）
        adapter.plugInEU();
    }
}

