package com.justin.springboottest.healthycoderapp;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.function.Executable;

import static org.junit.jupiter.api.Assertions.*;

class DietPlannerTest {

    private DietPlanner dietPlanner;

    @BeforeEach
    void setUp() {
        dietPlanner = new DietPlanner(20,30,50);
    }

    @RepeatedTest(value = 5, name = RepeatedTest.LONG_DISPLAY_NAME)
    void should_ReturnCorrectDietPlan_When_CorrectCoder() {
        // given
        Coder coder = new Coder(1.82, 75.0, 26, Gender.MALE);
        DietPlan expected = new DietPlan(2202, 110, 73, 275);

        // when
        DietPlan dietPlan = dietPlanner.calculateDiet(coder);

        // then
        Executable runnable = () -> assertEquals(expected.getCalories(), dietPlan.getCalories());
        Executable runnable2 = () -> assertEquals(expected.getProtein(), dietPlan.getProtein());
        Executable runnable3 = () -> assertEquals(expected.getFat(), dietPlan.getFat());
        Executable runnable4 = () -> assertEquals(expected.getCarbohydrate(), dietPlan.getCarbohydrate());
        assertAll(runnable,runnable2,runnable3,runnable4);
    }

    @AfterEach
    void tearDown() {
        System.out.println("A unit test was finished");
    }
}