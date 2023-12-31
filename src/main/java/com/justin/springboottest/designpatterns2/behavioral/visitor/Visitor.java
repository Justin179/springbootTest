package com.justin.springboottest.designpatterns2.behavioral.visitor;
/*
當你需要對不同類型的元素進行不同的操作時，
只需在 Visitor 接口中定義相應的方法，在具體的 Visitor 實現類中實現這些方法即可。
這使得你可以在不修改元素本身的情況下輕鬆地添加新的操作。
 */
public interface Visitor {
    void visit(ElementA elementA);
    void visit(ElementB elementB);
    // 可以根據需要添加其他訪問方法
}

class ConcreteVisitor implements Visitor {
    @Override
    public void visit(ElementA elementA) {
        // 對 ElementA 的操作或算法
        System.out.println("Visitor is visiting ElementA");
    }

    @Override
    public void visit(ElementB elementB) {
        // 對 ElementB 的操作或算法
        System.out.println("Visitor is visiting ElementB");
    }
}
