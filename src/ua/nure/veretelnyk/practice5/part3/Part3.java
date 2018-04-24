package ua.nure.veretelnyk.practice5.part3;

public class Part3 {
    public static void main(String[] args) {
        Thread[] threads = new Thread[5];
        Counter c = new Counter();
        for(int i=0; i<5; ++i)
            threads[i] = new MyThreadSync(c);

        for(int i=0; i<5; ++i)
            threads[i].start();
    }
}