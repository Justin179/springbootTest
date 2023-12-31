package com.justin.springboottest.designpatterns2.structural.adapter2;
/*
    需要一個轉接器（Adapter）來讓美國插頭的設備能夠在歐洲使用。這個轉接器會實現歐洲插頭接口，同時持有一個美國設備的實例：
 */
// 美國插頭轉接器
public class USDeviceEUAdapter implements EUPlug {

    private USPlug usDevice;

    public USDeviceEUAdapter(USPlug usDevice) {
        this.usDevice = usDevice;
    }

    @Override
    public void plugInEU() {
        // 在歐洲使用轉接器，實際上使用美國插頭的功能
        usDevice.plugIn();
    }
}

