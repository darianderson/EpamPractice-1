//package ua.nure.veretelnyk.practice5.part4_tmp;
//
//import java.util.Random;
//
//public class Part4 {
//    public static void main(String[] args) throws InterruptedException {
//        int M = 18;
//        int N = 100;
//        int[][] matrix = new int[M][N];
//        System.out.println();
//        //fill random
//        Random R = new Random();
//        for(int i=0; i<M; ++i)
//            for(int j=0; j<N; ++j)
//                matrix[i][j] = R.nextInt(20);
//
//        // Step by step
//        long start = System.nanoTime();
//        int max = matrix[0][0];
////        for(int i=0; i<M; ++i)
////            for(int j=0; j<N; ++j) {
////                if (matrix[i][j] > max)
////                    max = matrix[i][j];
////                Thread.sleep(1);
////            }
////        System.out.println( (System.nanoTime()-start) * Math.pow(10,-9) + " secs");
//
//
//        start = System.nanoTime();
//        final int CORES = 4;
//        FindMax[] threads = new FindMax[CORES];
//        final int STEP = M/CORES;
//
//        for(int i=0; i<CORES; ++i) {
//            int first = i*STEP;
//            int last = (i+1)*STEP;
//            if (i == CORES-1)
//                last = M;
//            int[][] matrixTmp = new int[last-first][N];
//            System.out.println(first+":"+last);
//            for(int j=0; j< last-first; ++j)
//                System.arraycopy(matrix[j], 0, matrixTmp[j], 0, N);
//            threads[i] = new FindMax(matrixTmp);
//        }
//
//        for (int i=0; i<CORES; ++i)
//            threads[i].start();
//
//        max = threads[0].getMax();
//        for(int i=1; i<CORES; ++i) {
//            threads[i].join();
//            if (threads[i].getMax() > max)
//                max = threads[i].getMax();
//        }
//
//        System.out.println( (System.nanoTime()-start) * Math.pow(10,-9) + " secs");
//    }
//}
//
//
//// Part5: position | seek(pos) | Random Access File