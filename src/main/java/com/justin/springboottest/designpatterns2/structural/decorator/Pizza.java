package com.justin.springboottest.designpatterns2.structural.decorator;

// 定義 Pizza 介面
public interface Pizza {
    String getDescription();
    double getCost();
}

// 實作 Pizza 介面的具體類別
class PlainPizza implements Pizza {
    @Override
    public String getDescription() {
        return "Plain Pizza";
    }

    @Override
    public double getCost() {
        return 5.0;
    }
}
