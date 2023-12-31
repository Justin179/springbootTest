package com.justin.springboottest.designpatterns2.creational.builder3;

public class Client {
    public static void main(String[] args) {
        Computer.ComputerBuilder computerBuilder = new Computer.ComputerBuilder("Transcend", "Kingston");
        computerBuilder.setBluetoothEnabled(true);
        computerBuilder.setGraphicsCardEnabled(true);
        Computer computer = computerBuilder.build();
        System.out.println(computer);
    }
}
