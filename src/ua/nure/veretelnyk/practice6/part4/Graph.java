package ua.nure.veretelnyk.practice6.part4;

import java.util.Arrays;

public class Graph {
    private boolean[][] matrix;
    private int nodes;
    // TODO check if it is exitsting
    public Graph(int nodes){
        this.nodes = nodes;
        matrix = new boolean[nodes][nodes];
    }

    public boolean add(int node, int[] connectedTO){
        if (node > nodes && connectedTO.length > nodes)
            return false;

        for(int i=0; i<connectedTO.length; ++i)
            matrix[node][connectedTO[i]] = true;

        return true;
    }

    public boolean remove(int node){
        if (node>=nodes)
            return false;

        Arrays.fill(matrix[node],false);
        for(int i=0; i<nodes; ++i)
            matrix[i][node] = false;

        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (boolean[] aMatrix : matrix) {
            for (int j = 0; j < aMatrix.length; ++j)
                sb.append(aMatrix[j] ? 1 : 0).append(" ");
            sb.append("\n");
        }
        return sb.toString();
    }
}
