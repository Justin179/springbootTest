package com.justin.springboottest.designpatterns2.structural.flyweight2;

import java.util.HashMap;
import java.util.Map;

// 一支程式寫完多好

public class Client {
    public static void main(String[] args) {
        FlyweightFactory flyweightFactory = new FlyweightFactory();
        Flyweight flyweight1 = flyweightFactory.getFlyweight("A");
        Flyweight flyweight2 = flyweightFactory.getFlyweight("B");
        Flyweight flyweight3 = flyweightFactory.getFlyweight("A");
        Flyweight flyweight4 = flyweightFactory.getFlyweight("C");
        Flyweight flyweight5 = flyweightFactory.getFlyweight("B");

        flyweight1.operation();
        flyweight2.operation();
        flyweight3.operation();
        flyweight4.operation();
        flyweight5.operation();
    }
}

/*
FlyweightFactory 類在創建後存儲它們來回收創建的對象。每次請求對象時，工廠都會查找對象以檢查它是否已經被創建。
如果是，則返回現有的對象，否則創建一個新的對象，存儲它，然後返回它。
一個種類就只會創建一次
 */
class FlyweightFactory {
    private final Map<String, Flyweight> flyweights = new HashMap<>();

    public Flyweight getFlyweight(String key) {
        if (flyweights.containsKey(key)) {
            return flyweights.get(key);
        } else {
            Flyweight flyweight = new ConcreteFlyweight(key);
            flyweights.put(key, flyweight);
            return flyweight;
        }
    }
}

interface Flyweight {
    void operation();
}

class ConcreteFlyweight implements Flyweight {

    // intrinsic 固有的，本質的，根本的
    private final String intrinsicState;

    public ConcreteFlyweight(String intrinsicState) {
        System.out.println("實體化: " + intrinsicState);
        this.intrinsicState = intrinsicState;
    }

    @Override
    public void operation() {
        System.out.println("ConcreteFlyweight: " + intrinsicState);
    }
}



