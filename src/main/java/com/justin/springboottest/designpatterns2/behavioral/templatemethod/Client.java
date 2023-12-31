package com.justin.springboottest.designpatterns2.behavioral.templatemethod;

public class Client {
    public static void main(String[] args) {
        System.out.println("Making rice:");
        CookingRecipe riceRecipe = new CookRice();
        riceRecipe.cook();

        System.out.println("\nMaking tea:");
        CookingRecipe teaRecipe = new MakeTea();
        teaRecipe.cook();
    }
}
