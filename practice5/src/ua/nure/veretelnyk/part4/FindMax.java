package ua.nure.veretelnyk.part4;

public class FindMax extends Thread {
    private int max;
    private int[][] matrix;
    private int start, end;

    public FindMax(int[][] matrix, int start, int end) {
        this.matrix = matrix;
        max = matrix[start][0];
        this.start = start;
        this.end = end;
    }

    public int getMax(){ return max; }

    @Override
    public void run() {
        for(int i=start; i<end; ++i) {
            for (int j = 0; j < matrix[i].length; ++j) {
                if (matrix[i][j] > max)
                    max = matrix[i][j];

                try {
                    sleep(1);
                } catch (InterruptedException e) {
//                    e.printStackTrace();
                }
            }
        }
    }

}