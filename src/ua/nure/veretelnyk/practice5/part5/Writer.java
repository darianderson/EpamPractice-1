package ua.nure.veretelnyk.practice5.part5;

import java.io.Console;
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
                raf.goTo(num*i+i); //problem here
                raf.write(String.valueOf(i) + "\n");
                System.out.println(i + ": " + getName());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
