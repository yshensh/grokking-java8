package oop;

/**
 * 接口的使用
 * 1。 接口使用上也满足多态性(polymorphism)
 * 2. 接口， 实际上定义了一种规范。体现了现实世界中“如果你是/要...则 必须能...”的思想。
 *    继承是一个"是不是"的关系，而接口实现则是 "能不能" 的关系。
 */

public class InterfaceTest {
    public static void main(String[] args) {
        Computer computer = new Computer();

        // method 1: instantiate Flash directly
        Flash flash =  new Flash();
        computer.transferData(flash);

        // method 2: use interface as type
        // Interface can not be instantiated directly but can be used as type by upcasting its subclass.
        USB usb = new Flash();
        computer.transferData(usb);
    }
}

interface USB {
    void start();

    void stop();
}

class Computer {
    public void transferData(USB usb) { // polymorphism of interface USB usb = new Flash();
        usb.start();
        System.out.println("details on data transfer");
        usb.stop();
    }
}

class Flash implements USB {
    @Override
    public void start() {
        System.out.println("Flash drive starts");
    }

    @Override
    public void stop() {
        System.out.println("Flash drive stops");
    }
}

class Printer implements USB {
    @Override
    public void start() {
        System.out.println("Printer starts");
    }

    @Override
    public void stop() {
        System.out.println("Printer stops");
    }
}