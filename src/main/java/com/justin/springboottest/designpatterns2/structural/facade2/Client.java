package com.justin.springboottest.designpatterns2.structural.facade2;

public class Client {
    public static void main(String[] args) {
        ComputerFacade computer = new ComputerFacade();
        computer.start(); // 透過facade 封裝複雜的操作
    }
}
