package oop;

public class Woman extends Person {

    int id = 2;

    public void playSoftball() {
        System.out.println("Woman: play softball");
    }

    @Override
    public void sleep() {
        System.out.println("Women: sleep");
    }

    @Override
    public void work() {
        System.out.println("Women: work");
    }
}
