package com.justin.springboottest.interviewprep;

class MyClass2 {
    public MyClass2() {
        System.out.println("MyClass instance created");
    }
}

// 2. 动态创建对象
public class ReflectionExample2 {
    public static void main(String[] args) throws IllegalAccessException, InstantiationException {
        Class<?> myClass = MyClass.class;
        MyClass instance = (MyClass) myClass.newInstance();
    }
}

