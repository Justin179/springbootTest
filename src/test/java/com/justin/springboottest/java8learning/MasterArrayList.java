package com.justin.springboottest.java8learning;

import org.assertj.core.util.Lists;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public class MasterArrayList {
    @Test
    void listOf(){
        // 也可以在创建 List 时指定值，但是这种方式创建的 List 不能修改。
        List<String> list = List.of("www", "wdbyte", "com");
        list.add("e");
        System.out.println(list); // UnsupportedOperationException (ImmutableCollections)
    }
    
    @Test
    void sort(){
        List<String> list = new ArrayList();
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("d");
        list = list.stream().sorted().collect(Collectors.toList());
        System.out.println(list); // [a, b, c, d]

        // 排序时指定降序还是升序：Comparator.reverseOrder() 降序
        list = list.stream().sorted(Comparator.reverseOrder()).collect(Collectors.toList());
        System.out.println(list); //  [d, c, b, a]

        Collections.sort(list);
        System.out.println(list); // [a, b, c, d]
        // 降序
        Collections.sort(list,Comparator.reverseOrder());
        System.out.println(list); // [d, c, b, a]

        list.sort(Comparator.comparing(Function.identity()));
        System.out.println(list); // [a, b, c, d]
    }

    @Test
    void listToArray(){
        List<String> list = new ArrayList();
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("d");

        String[] array = list.toArray(new String[0]);
        System.out.println(Arrays.toString(array));

        String[] array2 = list.stream().toArray(String[]::new);
        System.out.println(Arrays.toString(array2));
    }
    
    @Test
    void arrayToList(){
        List<String> list = new ArrayList();
        list.add("b");
        list.add("c");
        list.add("a");
        list.add("d");
        String[] array = list.toArray(new String[0]);

        // 方式1
        List<String> list0 = Lists.newArrayList(array);
        list0.add("e");
        System.out.println(list0);

        // 方式2
        List<String> list1 = Arrays.stream(array).toList();
        // list1.add("e"); 报错，不能修改的 List
        System.out.println(list1);

        // 方式3
        List<String> list2 = Arrays.asList(array);
        //list2.add("e"); 报错，不能修改的 List
        System.out.println(list2);

        // 方式4
        List<String> list3 = new ArrayList<>(Arrays.asList(array));
        list3.add("e");
        System.out.println(list3);

        // 方式5
        List<String> list4 = Arrays.stream(array).collect(Collectors.toList());
        list4.add("e");
        System.out.println(list4);
    }

    @Test
    void listToMap(){
        List<Dog> list = new ArrayList();
        list.add(new Dog("大黄", 1));
        list.add(new Dog("小黑", 2));

        // 方式1
        Map<String, Dog> dogMap = list.stream()
                .collect(Collectors.toMap(Dog::getName, Function.identity(), (o, n) -> o));
        System.out.println(dogMap.get("大黄"));

        // 方式2
        Map<String, Dog> dogMap2 = new HashMap<>();
        for (Dog dog : list) {
            dogMap2.put(dog.getName(), dog);
        }
        System.out.println(dogMap.get("大黄"));
    }

    @Test
    void toDeleteUseIterator(){
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
