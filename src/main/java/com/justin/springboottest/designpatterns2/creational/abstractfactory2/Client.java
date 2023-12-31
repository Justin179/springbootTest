package com.justin.springboottest.designpatterns2.creational.abstractfactory2;

public class Client {
    public static void main(String[] args) {
        // 要產品找工廠生產(即new)，client不要自己手動生產(即new)產品

        // new工廠
        CarFactory sedanFactory = new SedanFactory();
        CarFactory suvFactory = new SUVFactory();
        // 透過工廠生產產品
        Car sedan = sedanFactory.createCar();
        Car suv = suvFactory.createCar();

        System.out.println("Sedan: " + sedan.getCarName());
        System.out.println("SUV: " + suv.getCarName());
    }
}

interface CarFactory {
    Car createCar();
}

class SedanFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new Sedan();
    }
}

class SUVFactory implements CarFactory {
    @Override
    public Car createCar() {
        return new SUV();
    }
}


interface Car {
    String getCarName();
}

class Sedan implements Car {
    @Override
    public String getCarName() {
        return "Sedan";
    }
}

class SUV implements Car {
    @Override
    public String getCarName() {
        return "SUV";
    }
}


