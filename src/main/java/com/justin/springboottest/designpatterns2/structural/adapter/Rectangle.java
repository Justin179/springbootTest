package com.justin.springboottest.designpatterns2.structural.adapter;

public interface Rectangle {
    void determineSize();
}

class RectangleAdapter implements Rectangle {

    private LegacyRectangle legacyRectangle;

    public RectangleAdapter(LegacyRectangle legacyRectangle) {
        this.legacyRectangle = legacyRectangle;
    }

    @Override
    public void determineSize() {
        // 轉換客戶端期望的方法調用成適配到LegacyRectangle的方法
        legacyRectangle.calculateSize();
    }
}
