package ua.nure.veretelnyk.practice4;

import java.io.*;
import java.util.Random;

public class Part2 {
    private static final String FILE = "data/practice4/part2.txt";
    private static final String FILE_SORTED = "data/practice4/part2_sorted.txt";

    private static final int NUMBERS_LENGTH = 10;
    private static void init() throws IOException {
        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(FILE), "UTF-8"));

        Random R = new Random();
        for(int i=0; i<NUMBERS_LENGTH; ++i)
            out.append(String.valueOf(R.nextInt(50))).append(" ");

        out.close();
    }

    private static void InsertionSort(int[] arr){
        int tmp;

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 1; j < arr.length - i; ++j) {
                if (arr[j - 1] > arr[j]) {
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }

    private static void sort() throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE), "UTF-8"));
        String line = in.readLine();
        in.close();

        String[] numbers = line.split(" ");
        int[] arr = new int[NUMBERS_LENGTH];
        for (int i=0; i<arr.length; ++i)
            arr[i] = Integer.parseInt(numbers[i]);
        InsertionSort(arr);

        BufferedWriter out = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(FILE_SORTED), "UTF-8"));
        for (int anArr : arr) {
            out.append(String.valueOf(anArr)).append(" ");
        }
        out.close();
    }

    private static void print() throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE), "UTF-8"));

        System.out.println("input  ==> " + in.readLine());
        in.close();

        in = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE_SORTED), "UTF-8"));

        System.out.println("output ==> " + in.readLine());
        in.close();
    }

    public static void main(String[] args) throws IOException {
        init();
        sort();
        print();
    }
}
