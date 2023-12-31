package com.justin.springboottest.designpatterns2.behavioral.templatemethod2;

public class TemplateMethodExample {
    public static void main(String[] args) {
        ComputerBuilder builder = new StandardComputerBuilder();
        builder.buildComputer();

        builder = new HighEndComputerBuilder();
        builder.buildComputer();
    }
}

 abstract class ComputerBuilder {

    // 組電腦
     public final void buildComputer() {
         addMotherboard(); // 主機板
         addProcessor(); // 處理器(i5 i7)
         addHardDisk(); // 硬碟
     }

    protected abstract void addMotherboard();
    protected abstract void addProcessor();
    protected abstract void addHardDisk();

}

 class StandardComputerBuilder extends ComputerBuilder {
    @Override
    protected void addMotherboard() {
        System.out.println("Adding standard motherboard");
    }

    @Override
    protected void addProcessor() {
        System.out.println("Adding standard processor");
    }

    @Override
    protected void addHardDisk() {
        System.out.println("Adding standard hard disk");
    }
}

 class HighEndComputerBuilder extends ComputerBuilder {
    @Override
    protected void addMotherboard() {
        System.out.println("Adding high-end motherboard");
    }

    @Override
    protected void addProcessor() {
        System.out.println("Adding high-end processor");
    }

    @Override
    protected void addHardDisk() {
        System.out.println("Adding high-end hard disk");
    }
}



