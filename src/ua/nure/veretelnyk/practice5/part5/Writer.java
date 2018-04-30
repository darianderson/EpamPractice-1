package ua.nure.veretelnyk.practice5.part5;

import java.io.IOException;

public class Writer extends Thread{
    private static final int COUNTS = 20;
    private int num;

    public Writer(int num) {
        this.num = num;
    }

    @Override
    public void run() {
        try {
            RAF raf = RAF.getInstance();
            for(int i=0; i<COUNTS; ++i){
                raf.goTo(num);
                raf.write(String.valueOf(i));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
