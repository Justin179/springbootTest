package com.justin.springboottest.designpatterns2.behavioral.visitor;

public interface Element {
    void accept(Visitor visitor);
}

class ElementA implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    // 其他 ElementA 的方法和屬性
}

class ElementB implements Element {
    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }

    // 其他 ElementB 的方法和屬性
}
