package com.justin.springboottest.designpatterns2.creational.prototype2;

/*
在 Java 中，如果要使用原型模式，需要實現 Cloneable 接口。這個接口是一個標記接口，
它沒有任何方法，只是用來標記一個類別可以被克隆。
這個接口是 Java 語言提供的原生接口，你可以在自己的代碼中直接使用它。
 */
public class Shape implements Cloneable {
    private String type;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Object clone() {
        Object clone = null;

        try {
            clone = super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }

        return clone;
    }
}

