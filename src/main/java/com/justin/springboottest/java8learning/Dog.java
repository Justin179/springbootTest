package com.justin.springboottest.java8learning;

import lombok.Data;

@Data
public class Dog {
    private String name;
    private Integer age;

    public Dog() {
    }

    public Dog(String name) {
        this.name = name;
    }

    public Dog(String name, Integer age) {
        this.name = name;
        this.age = age;
    }
}
