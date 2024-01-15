package com.justin.springboottest.interviewprep;

import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class StreamLaziness {
    public static void main(String[] args) {
        List<String> strings = Arrays.asList("abc", "def", "gkh", "abc");
        Stream<Integer> stream = strings.stream().filter(new Predicate() {
            @Override
            public boolean test(Object o) {
                System.out.println("Predicate.test 执行");
                return true;
            }
        });

        System.out.println("count 执行");
        stream.count();
    }
    /*
    按执行顺序应该是先打印 4 次「Predicate.test 执行」，再打印「count 执行」。实际结果恰恰相反。
    说明 filter 中的方法并没有立刻执行，而是等调用count()方法后才执行。
     */
}
