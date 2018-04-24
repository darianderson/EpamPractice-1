//package ua.nure.veretelnyk.practice5.part4_tmp;
//
//public class FindMax extends Thread {
//    private int max;
//    private int[][] matrix;
//
//    public FindMax(int[][] matrix) {
//        this.matrix = matrix;
//        max = matrix[0][0];
//    }
//
//    public int getMax(){ return max; }
//
//    @Override
//    public void run() {
//        for(int i=0; i<matrix.length; ++i) {
//            System.out.println(i +" " + getName());
//            for (int j = 0; j < matrix[i].length; ++j) {
//                if (matrix[i][j] > max)
//                    max = matrix[i][j];
//                try {
//                    sleep(1);
//                } catch (InterruptedException e) {
////                    e.printStackTrace();
//                }
//            }
//        }
//    }
//}
