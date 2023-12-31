package com.justin.springboottest.designpatterns2.structural.decorator2;

public interface ChristmasTree {
    String decorate();
}

class ChristmasTreeImpl implements ChristmasTree {
    @Override
    public String decorate() {
        return "Christmas tree";
    }
}

