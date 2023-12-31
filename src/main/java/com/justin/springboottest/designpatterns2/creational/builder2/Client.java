package com.justin.springboottest.designpatterns2.creational.builder2;

public class Client {
    public static void main(String[] args) {
        MealBuilder mealBuilder = new MealBuilder();

        // 寫法一
//        mealBuilder.selectMainCourse("Buger");
//        mealBuilder.selectDrink("Coffee");
//        mealBuilder.selectDessert("Ice Cream");
//        Meal meal1 = mealBuilder.build();

        // 寫法二
        Meal meal1 = mealBuilder.selectMainCourse("Buger")
                .selectDrink("Coffee")
                .selectDessert("Ice Cream")
                .build();

        // 兩個寫法的結果是一樣的，但寫法二明顯簡潔許多
        // System.out.println(meal1);
    }
}
