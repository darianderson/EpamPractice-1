package ua.nure.veretelnyk.part5;

import java.io.IOException;
import java.io.RandomAccessFile;

public class Writer extends Thread{
    private int num;
    private final RandomAccessFile raf;
    private long pointer;
    private static final long STRING_LENGTH = 20L + System.lineSeparator().length();

    Writer(int num, RandomAccessFile raf){
        this.num = num;
        this.raf =raf;
        pointer = num*STRING_LENGTH;
    }

    @Override
    public void run() {
        for(int i=0; i<20; ++i){
            synchronized (raf){
                try{
                    raf.seek(pointer+i);
                    raf.writeBytes(String.valueOf(num));
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
        synchronized (raf) {
            try {
                raf.seek(pointer + 20);
                raf.write(System.lineSeparator().getBytes());
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
