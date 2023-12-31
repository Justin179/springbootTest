package com.justin.springboottest.designpatterns2.structural.adapter;

public class Client {

    public static void main(String[] args) {
        LegacyRectangle legacyRectangle = new LegacyRectangle();
        Rectangle adapter = new RectangleAdapter(legacyRectangle);

        // 客戶端調用期望的接口方法
        adapter.determineSize();
    }
}

