package com.justin.springboottest.designpatterns2.structural.composite;

import java.util.ArrayList;
import java.util.List;

public class Client {
    public static void main(String[] args) {
        Leaf leaf1 = new Leaf("1");
        Leaf leaf2 = new Leaf("2");
        Leaf leaf3 = new Leaf("3");

        Composite composite = new Composite();
        composite.add(leaf2);
        composite.add(leaf3);

        Composite composite2 = new Composite();
        composite2.add(composite);
        composite2.add(leaf1);

        composite2.operation();
    }
}


// Component 介面或抽象類別
interface Component {
    void operation();
}

// Leaf 類別
class Leaf implements Component {
    private String name;

    public Leaf(String name) {
        this.name = name;
    }

    public void operation() {
        System.out.println("Leaf " + name + " operation performed.");
    }
}

// Composite 類別
class Composite implements Component {
    private List<Component> children = new ArrayList<>();

    public void add(Component component) {
        children.add(component);
    }

    public void remove(Component component) {
        children.remove(component);
    }

    public void operation() {
        System.out.println("Composite operation performed:");
        for (Component component : children) {
            component.operation();
        }
    }
}