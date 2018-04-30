package ua.nure.veretelnyk.practice5.part5;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;

public class RAF {
    private static RAF instance = null;
    public static final String FILE_PATH = "data/practice5/part5.txt";

    private RandomAccessFile file;


    public static RAF getInstance() throws IOException{
        if (instance == null) {
            new File(FILE_PATH).createNewFile();
            instance = new RAF();
        }
        return instance;
    }

    // переход на указанный символ
    public synchronized long goTo(int num) throws IOException{
        file = new RandomAccessFile(FILE_PATH, "r");

        file.seek(num);
        long pointer = file.getFilePointer();
        file.close();

        return pointer;
    }

    public synchronized void write(String msg) throws IOException{
        file = new RandomAccessFile(FILE_PATH, "rw");
        file.write(msg.getBytes());
        file.close();
    }


}
