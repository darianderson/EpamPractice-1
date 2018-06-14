package ua.nure.veretelnyk.part4;

import java.util.Random;

public class Part4 {
    public static void main(String[] args) throws InterruptedException {
        int M = 4;
        int N = 100;
        int[][] matrix = new int[M][N];

        //fill random
        Random R = new Random();
        for(int i=0; i<M; ++i)
            for(int j=0; j<N; ++j)
                matrix[i][j] = R.nextInt(20);

        // Step by step
        long start = System.nanoTime();
        int max = matrix[0][0];
        for(int i=0; i<M; ++i)
            for(int j=0; j<N; ++j) {
                if (matrix[i][j] > max)
                    max = matrix[i][j];
                Thread.sleep(1);
            }
        System.out.println( (System.nanoTime()-start) * Math.pow(10,-9) + " secs. Max is "+max);


        // multithreading
        start = System.nanoTime();
        final int CORES = 4;
        FindMax[] threads = new FindMax[CORES];
        final int STEP = M/CORES;

        // init and start threads
        for(int i=0; i<CORES; ++i) {
            int first = i*STEP;
            int last = (i+1)*STEP;
            threads[i] = new FindMax(matrix, first, i == CORES-1 ? M : last);
            threads[i].start();
        }

        // waiting until everyone finish
        for (int i=0; i<CORES; ++i)
            threads[i].join();

        // finding maximum from all threads
        max = threads[0].getMax();
        for(int i=1; i<CORES; ++i) {
            if (threads[i].getMax() > max)
                max = threads[i].getMax();
        }

        System.out.println( (System.nanoTime()-start) * Math.pow(10,-9) + " secs. Max is "+max);
    }
}

// Part5: position | seek(pos) | Random Access File