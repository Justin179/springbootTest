package com.justin.springboottest.designpatterns2.creational.prototype;

public interface Prototype {
    Prototype clone();
}

// 具体类实现原型接口
class ConcretePrototype implements Prototype {
    private String name;

    public ConcretePrototype(String name) {
        this.name = name;
    }

    // 实现克隆方法
    @Override
    public Prototype clone() {
        return new ConcretePrototype(this.name);
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}

