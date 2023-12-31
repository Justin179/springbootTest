package com.justin.springboottest.designpatterns2.structural.facade;

/*
我們建立了一個 ComputerFacade 類別，來隱藏一個複雜的子系統，包含了 CPU、Memory 和 HardDrive 三個類別。
當我們需要使用這個子系統時，只需要建立一個 ComputerFacade 物件，就可以自動進行初始化，並且隱藏了子系統的複雜性。
 */
 // Facade providing a simplified interface to the complex subsystem
public class Facade {
    private SubSystemOne one;
    private SubSystemTwo two;
    private SubSystemThree three;

    public Facade() {
        one = new SubSystemOne();
        two = new SubSystemTwo();
        three = new SubSystemThree();
    }

    public void methodA() {
        System.out.println("Facade Method A:");
        one.methodOne();
        two.methodTwo();
    }

    public void methodB() {
        System.out.println("Facade Method B:");
        two.methodTwo();
        three.methodThree();
    }
}

// Classes in a complex subsystem
class SubSystemOne {
    public void methodOne() {
        System.out.println("SubSystemOne Method");
    }
}

class SubSystemTwo {
    public void methodTwo() {
        System.out.println("SubSystemTwo Method");
    }
}

class SubSystemThree {
    public void methodThree() {
        System.out.println("SubSystemThree Method");
    }
}

