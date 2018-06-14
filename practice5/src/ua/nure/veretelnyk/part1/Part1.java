package ua.nure.veretelnyk.part1;

public class Part1 {
    public static void main(String[] args) {
        Thread t1 = new MyThread();
        Thread t2 = new Thread(new RunnableImpl());

        t1.start();
        t2.start();

    }
}