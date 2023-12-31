package com.justin.springboottest.designpatterns2.behavioral.iterator;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
/*
Java標準庫中的許多集合類（如List、Set、Map等）都實現了迭代器
 */
public class IteratorPatternExample {
    public static void main(String[] args) {
        List<String> names = new ArrayList<>();
        names.add("Alice");
        names.add("Bob");
        names.add("Charlie");

        // 使用迭代器來遍歷集合並輸出元素
        // 使用iterator()方法獲取該集合的迭代器
        Iterator<String> iterator = names.iterator();
        // 使用while循環和hasNext()方法來檢查是否還有下一個元素，然後使用next()方法來獲取元素並進行操作。
        // 迭代器模式提供了一種統一的方法來遍歷各種不同類型的集合
        while (iterator.hasNext()) {
            String name = iterator.next();
            System.out.println(name);
        }
    }
}

