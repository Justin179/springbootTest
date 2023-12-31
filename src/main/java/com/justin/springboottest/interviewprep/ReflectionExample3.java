package com.justin.springboottest.interviewprep;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class MyClass3 {
    private String privateField;

    private void privateMethod() {
        System.out.println("Private method");
    }
}

// 3. 访问字段和调用方法
public class ReflectionExample3 {
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, NoSuchMethodException, InvocationTargetException, InvocationTargetException {
        Class<?> myClass = MyClass3.class;
        MyClass3 instance = new MyClass3();

        Field privateField = myClass.getDeclaredField("privateField");
        privateField.setAccessible(true); // 对私有成员的访问需要调用 setAccessible(true) 方法来取消 Java 访问权限限制
        privateField.set(instance, "Reflection");

        Method privateMethod = myClass.getDeclaredMethod("privateMethod");
        privateMethod.setAccessible(true); // 对私有成员的访问需要调用 setAccessible(true) 方法来取消 Java 访问权限限制
        privateMethod.invoke(instance);
    }
}

