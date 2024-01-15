package com.justin.springboottest.java8learning;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public class Java8TesterTest3 {

//    enum Weekday {
//        MONDAY,
//        TUESDAY,
//        WEDNESDAY,
//        THURSDAY,
//        FRIDAY,
//        SATURDAY,
//        SUNDAY
//    }

    // 加料
    enum Weekday {
        MONDAY("星期一"),
        TUESDAY("星期二"),
        WEDNESDAY("星期三"),
        THURSDAY("星期四"),
        FRIDAY("星期五"),
        SATURDAY("星期六"),
        SUNDAY("星期日");

        private final String name;

        Weekday(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
    
    @Test
    void Enum(){
        Weekday[] weekdays = Weekday.values();
        for (Weekday weekday: weekdays){
            System.out.println(weekday);
        }
    }

    @Test
    void Enum2(){
        Weekday monday = Weekday.MONDAY;
        switch (monday){
            case MONDAY :{System.out.println("周一");break;}
            case SUNDAY :{System.out.println("周末");break;}
        }
    }
    /*
    枚举高级用法
    下面的枚举的高级用法可能很少使用，许多使用了很久枚举的同学可能也不知道，其实这些高级用法一点都不奇怪，
    如果你把枚举就看做一个特殊写法的类，那么就都一目了然
     */
    interface Shape {
        // 计算面积
        double getArea();
    }
    enum Rectangle implements Shape {
        SMALL(3, 4),
        MEDIUM(4, 5),
        LARGE(5, 6);

        private int length;
        private int width;

        Rectangle(int length, int width) {
            this.length = length;
            this.width = width;
        }

        public double getArea() {
            return length * width;
        }
    }
    
    @Test
    void Enum3(){
        Shape shape = Rectangle.LARGE;
        double shapeArea = shape.getArea();
        System.out.println(shapeArea); // 输出：30.0
    }

    public enum Calc {
        // 加法
        PLUS {
            public int apply(int x, int y) {
                return x + y;
            }
        },
        // 减法
        MINUS {
            public int apply(int x, int y) {
                return x - y;
            }
        },
        // 乘法
        MULTIPLY {
            public int apply(int x, int y) {
                return x * y;
            }
        };

        public abstract int apply(int x, int y);

    }
    
    @Test
    void CalcTest(){
        // 加法
        int res = Calc.PLUS.apply(2, 3);
        System.out.println(res); // 5
        // 减法
        res = Calc.MINUS.apply(2, 3);
        System.out.println(res); // -1
        // 乘法
        res = Calc.MULTIPLY.apply(2, 3);
        System.out.println(res); // 6
    }

    @Test
    void Iterable(){
        List<String> arrayList = new ArrayList<>();
        // 向 List 中添加元素
        arrayList.add("www");
        arrayList.add("wdbyte");
        arrayList.add("com");
        HashSet<String> hashSet = new HashSet<>();
        hashSet.addAll(arrayList);

        printCollection(arrayList);
        printCollection(hashSet);
    }

    void printCollection(Iterable<String> iterable) {
        // 当前日期
        LocalDate now = LocalDate.now();
        Iterator<String> iterator = iterable.iterator();
        while (iterator.hasNext()) {
            String obj = iterator.next();
            System.out.println(now + ":" + obj.toString());
        }
    }

    @Test
    void ConcurrentModificationException(){
        List<String> arrayList = new ArrayList();
        arrayList.add("a");
        arrayList.add("b");
        Iterator<String> iterator = arrayList.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if ("a".equals(next)) {
                iterator.remove();
            }
        }
        System.out.println(arrayList); // output: [b]
    }
    


















































































































    
}






















