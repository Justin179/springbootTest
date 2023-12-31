package com.justin.springboottest.designpatterns2.behavioral.templatemethod;

// Abstract class defining the template method
public abstract class CookingRecipe {
    // Template method
    public final void cook() {
        prepareIngredients(); // 備料 (非共通開放實作)
        cookRecipe(); // 煮 (非共通開放實作)
        cleanUp(); // 善後 (共通的直接做掉)
    }

    // Abstract methods to be implemented by subclasses
    protected abstract void prepareIngredients();
    protected abstract void cookRecipe();

    // Common method
    private void cleanUp() {
        System.out.println("Cleaning up utensils");
    }
}

// Concrete class for cooking rice
 class CookRice extends CookingRecipe {
    protected void prepareIngredients() {
        System.out.println("Washing rice and measuring water");
    }

    protected void cookRecipe() {
        System.out.println("Boiling rice and water together");
    }
}

// Concrete class for making tea
 class MakeTea extends CookingRecipe {
    protected void prepareIngredients() {
        System.out.println("Boiling water and adding tea leaves");
    }

    protected void cookRecipe() {
        System.out.println("Straining tea leaves and pouring tea");
    }
}

