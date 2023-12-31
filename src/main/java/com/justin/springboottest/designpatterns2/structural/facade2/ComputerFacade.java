package com.justin.springboottest.designpatterns2.structural.facade2;

public class ComputerFacade {
    private final CPU processor;
    private final Memory ram;
    private final HardDrive hd;

    public ComputerFacade() {
        this.processor = new CPU();
        this.ram = new Memory();
        this.hd = new HardDrive();
    }

    public void start() {
        processor.freeze();
        ram.load(1, hd.read(1, 1));
        processor.jump(1);
        processor.execute();
    }
}

class CPU {
    public void freeze() { }
    public void jump(long position) {  }
    public void execute() {  }
}

class Memory {
    public void load(long position, byte[] data) {  }
}

class HardDrive {
    public byte[] read(long lba, int size) { return null; }
}