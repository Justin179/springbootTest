package com.justin.springboottest.interviewprep;

import java.util.Arrays;
import java.util.List;

public class ParallelStreamTest {
    public static void main(String[] args) {
        // parallelStream 超簡明例子
        List<Integer> numbers = Arrays.asList(1, 2, 5, 4);
        numbers.parallelStream() .forEach(num->System.out.println(Thread.currentThread().getName()+">>"+num));
    }
}
