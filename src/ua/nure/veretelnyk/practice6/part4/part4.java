package ua.nure.veretelnyk.practice6.part4;

public class part4 {
    public static void main(String[] args) {
        Graph g = new Graph();

        g.add(0, new int[] { 2,3});
        g.add(1, new int[] { 1,2,3,4});
        g.add(2, new int[] { 1});
        g.add(3, new int[] { });
        g.add(4, new int[] { 4,1});
        System.out.println(g);
    }
}