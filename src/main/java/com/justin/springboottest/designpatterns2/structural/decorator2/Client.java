package com.justin.springboottest.designpatterns2.structural.decorator2;

public class Client {

    // 最內層那個就是base，再一層一層往上疊加
    public static void main(String[] args) {
        ChristmasTree tree1 = new Garland(new ChristmasTreeImpl());
        System.out.println(tree1.decorate());
        ChristmasTree tree2 = new BubbleLights(new Garland(new Garland(new ChristmasTreeImpl())));
        System.out.println(tree2.decorate());
    }
}
