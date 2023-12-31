package com.justin.springboottest.designpatterns2.creational.builder2;

public class Meal {
    private String mainCourse;
    private String drink;
    private String dessert;

    public Meal() {
        // 預設建構子
    }

    // Getter 和 Setter 方法
    public String getMainCourse() {
        return mainCourse;
    }

    public void setMainCourse(String mainCourse) {
        this.mainCourse = mainCourse;
    }

    public String getDrink() {
        return drink;
    }

    public void setDrink(String drink) {
        this.drink = drink;
    }

    public String getDessert() {
        return dessert;
    }

    public void setDessert(String dessert) {
        this.dessert = dessert;
    }
}

