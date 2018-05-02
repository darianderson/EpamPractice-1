package ua.nure.veretelnyk.practice5.part5;

import java.io.*;
import java.util.Scanner;

public class part5 {
    public static void main(String[] args) throws IOException, InterruptedException {

        Writer[] writer = new Writer[10];
        for(int i=0; i<10; ++i){
            writer[i] = new Writer(i);
            writer[i].start();
        }

        for (int i=0; i<10; ++i)
            writer[i].join();

        Scanner scanner = new Scanner(new File(RAF.FILE_PATH), "UTF-8");
        while (scanner.hasNextLine())
            System.out.println(scanner.nextLine() + "\n");
    }
}
