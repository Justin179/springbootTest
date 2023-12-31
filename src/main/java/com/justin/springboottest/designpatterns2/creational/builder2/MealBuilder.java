package com.justin.springboottest.designpatterns2.creational.builder2;

public class MealBuilder {
    private Meal meal;

    public MealBuilder() {
        meal = new Meal();
    }

    public Meal build() {
        return meal;
    }

    public MealBuilder selectMainCourse(String mainCourse) {
        meal.setMainCourse(mainCourse);
        return this;
    }

    public MealBuilder selectDrink(String drink) {
        meal.setDrink(drink);
        return this;
    }

    public MealBuilder selectDessert(String dessert) {
        meal.setDessert(dessert);
        return this;
    }


}

