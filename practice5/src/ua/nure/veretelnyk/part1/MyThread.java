package ua.nure.veretelnyk.part1;

public class MyThread extends Thread {
    @Override
    public void run() {
        for(int i=0; i<6; ++i){
            System.out.println(getName());
            try {
                sleep(500);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
}