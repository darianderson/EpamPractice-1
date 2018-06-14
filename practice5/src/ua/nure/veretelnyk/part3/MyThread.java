package ua.nure.veretelnyk.part3;

public class MyThread extends Thread {
    Counter counter;

    public MyThread(Counter c){ counter = c; }

    @Override
    public void run() {
        for(int i=0; i<10; ++i) {
            System.out.println(counter.isEquals() + ": " + getName());
            counter.countOneIncrement();
            try {
                sleep(10);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
            counter.countTwoIncrement();
        }
    }
}
