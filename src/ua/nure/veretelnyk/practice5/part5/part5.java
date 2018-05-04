package ua.nure.veretelnyk.practice5.part5;

import java.io.*;
import java.util.Scanner;

public class part5 {

    public static void main(String[] args) throws IOException, InterruptedException {
        final String path = "data/practice5/part5.txt";

        File file = new File(path);
        boolean isDeleted = false;
        if (file.exists())
            isDeleted = file.delete();
        boolean isCreated = file.createNewFile();
        if (!isCreated && !isDeleted)
            System.out.println("File wasn\'t created or deleted.");

        RandomAccessFile raf = new RandomAccessFile(file,"rw");
        Thread[] threads = new Thread[10];

        for(int i=0; i<threads.length; ++i)
            threads[i] = new Writer(i, raf);

        for (Thread t : threads)
            t.start();


        for (Thread t : threads)
            t.join();

        raf.close();

        Scanner in = new Scanner(new File(path), "UTF-8");
        while (in.hasNextLine())
            System.out.println(in.nextLine());

        in.close();
    }
}
