package com.justin.springboottest.healthycoderapp;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.condition.DisabledOnOs;
import org.junit.jupiter.api.condition.OS;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;

class BMICalculatorTest {

    private String environment = "prod";

    @BeforeAll
    static void beforeAll(){
        System.out.println("before all unit tests");
    }

    @AfterAll
    static void afterAll(){
        System.out.println("after all unit tests");
    }

    /*
    因為一個方法，可能會需要寫多個tests，所以可以透過這個 @Nested 來手動整理與分類
     */
    @Nested
    class IsDietRecommendedTests{
        @ParameterizedTest
        @ValueSource(doubles = {89.0,95.0,110.0})
        void isDietRecommended(Double coderWeight) {
            // given
            double weight = coderWeight;
            double height = 1.72;

            // when
            boolean dietRecommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertTrue(dietRecommended);
        }

        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvSource(value = {"89.0,1.72","95.0,1.75","110.0,1.78"})
        void isDietRecommendedCsv(Double coderWeight, Double coderHeight) {
            // given
            double weight = coderWeight;
            double height = coderHeight;

            // when
            boolean dietRecommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertTrue(dietRecommended);
        }


        @ParameterizedTest(name = "weight={0}, height={1}")
        @CsvFileSource(resources = "/diet-recommended-input-data.csv", numLinesToSkip = 1)
        void isDietRecommendedCsvFile(Double coderWeight, Double coderHeight) {
            // given
            double weight = coderWeight;
            double height = coderHeight;

            // when
            boolean dietRecommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertTrue(dietRecommended);
        }


        @Test
        void should_ReturnFalse_When_DietNotRecommended() {
            // given
            double weight = 50.0; // KG
            double height = 1.92; // M

            // when
            boolean dietRecommended = BMICalculator.isDietRecommended(weight, height);

            // then
            assertFalse(dietRecommended);
        }

        @Test
        void should_ThrowArithmeticException_When_HeightZero() {
            // given
            double weight = 50.0; // KG
            double height = 0.0; // M

            // when
            Executable callable = () -> BMICalculator.isDietRecommended(weight, height);

            // then
            assertThrows(ArithmeticException.class, callable);
        }

    }

    @Nested
    @DisplayName(">>>> sample inner class display name")
    class FindCoderWithWorstBMITests{

        @Test
        @DisplayName(">>>> sample method display name")
//        @Disabled
        @DisabledOnOs(OS.WINDOWS)
        void findCoderWithWorstBMI() {
            // given
            ArrayList<Coder> coders = new ArrayList<>();
            coders.add(new Coder(1.80,60.0));
            coders.add(new Coder(1.82,98.0)); //
            coders.add(new Coder(1.82,64.7));

            // when
            Coder coderWorseBMI = BMICalculator.findCoderWithWorstBMI(coders);

            // then
            // 寫法1: 這個寫法的問題在於，如果其中一個失敗，在失敗後面的結果都看不到，所以才要改用AssertAll
//        assertEquals(1.82,coderWorseBMI.getHeight());
//        assertEquals(98.0,coderWorseBMI.getWeight());

            // 更好的寫法
            Executable runnable1 = () -> assertEquals(1.82, coderWorseBMI.getHeight());
            Executable runnable2 = () -> assertEquals(98.0,coderWorseBMI.getWeight());
            assertAll(runnable1,runnable2);

        }

        @Test
        void findCoderWithWorstBMI_When_CoderListNotEmpty() {
            // given
            ArrayList<Coder> coders = new ArrayList<>();

            // when
            Coder coderWorseBMI = BMICalculator.findCoderWithWorstBMI(coders);

            // then
            assertNull(coderWorseBMI);
        }

        @Test
        void should_ReturnCoderWithWorstBMIIn1Ms_When_CoderListHas10000Elements(){

            assumeTrue(environment.equals("prod")); // test skips instead of failed

            // given
            List<Coder> coderList = new ArrayList<>();
            for (int i = 0; i < 10000; i++) {
                coderList.add(new Coder(1.0+i,10.0+i));
            }

            // when
            Executable callable = () -> BMICalculator.findCoderWithWorstBMI(coderList);


            // then
            assertTimeout(Duration.ofMillis(500),callable);

        }
    }







    @Test
    void should_ReturnCorrectBMIScoreArray_When_CoderListNotEmpty(){
        
        // given
        ArrayList<Coder> coders = new ArrayList<>();
        coders.add(new Coder(1.80,60.0));
        coders.add(new Coder(1.82,98.0));
        coders.add(new Coder(1.82,64.7));
        double[] expected = {18.52,29.59,19.53};
        
        // when
        double[] bmiScores = BMICalculator.getBMIScores(coders);

        // then
        assertArrayEquals(expected, bmiScores);
    }







}













