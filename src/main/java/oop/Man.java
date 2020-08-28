package oop;

public class Man extends Person {

    int id = 1;

    public void playBaseball() {
        System.out.println("Man: play baseball");
    }

    @Override
    public void sleep() {
        System.out.println("Man: sleep");
    }

    @Override
    public void work() {
        System.out.println("Man: work");
    }
}
