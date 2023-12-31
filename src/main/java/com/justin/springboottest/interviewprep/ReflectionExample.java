package com.justin.springboottest.interviewprep;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

class MyClass {
    private String privateField;
    public int publicField;

    private void privateMethod() {
        System.out.println("Private method");
    }

    public void publicMethod() {
        System.out.println("Public method");
    }
}

// 1. 获取类的信息
public class ReflectionExample {
    public static void main(String[] args) throws NoSuchFieldException, NoSuchMethodException {
        Class<?> myClass = MyClass.class;

        String className = myClass.getName();
        Class<?> superClass = myClass.getSuperclass();
        Field[] fields = myClass.getDeclaredFields();
        Method[] methods = myClass.getDeclaredMethods();

        System.out.println("Class name: " + className);
        System.out.println("Superclass: " + superClass.getName());
        System.out.println("Fields:");
        for (Field field : fields) {
            System.out.println(field.getName());
        }
        System.out.println("Methods:");
        for (Method method : methods) {
            System.out.println(method.getName());
        }
    }
}
