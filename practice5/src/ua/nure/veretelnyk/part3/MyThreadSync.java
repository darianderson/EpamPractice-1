package ua.nure.veretelnyk.part3;

public class MyThreadSync extends Thread {
    Counter counter;

    public MyThreadSync(Counter c){ counter = c; }

    @Override
    public void run() {
        for(int i=0; i<10; ++i){
            try {
                counter.increment();
            } catch (InterruptedException e) {
                return;
            }
        }
    }
}