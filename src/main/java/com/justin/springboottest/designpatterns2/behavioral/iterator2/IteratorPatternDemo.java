package com.justin.springboottest.designpatterns2.behavioral.iterator2;

import java.util.ArrayList;
import java.util.Iterator;

public class IteratorPatternDemo {
    public static void main(String[] args) {
        ArrayList<String> names = new ArrayList<String>();
        names.add("John");
        names.add("Mike");
        names.add("Lisa");
        names.add("Peter");

        Iterator<String> it = names.iterator();
        while(it.hasNext()) {
            String name = it.next();
            System.out.println("Name: " + name);
        }
    }
}

