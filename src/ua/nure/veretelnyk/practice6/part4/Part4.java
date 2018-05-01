package ua.nure.veretelnyk.practice6.part4;

public class Part4 {
	
	public static void main(String[] args) {
        Graph g = new Graph(6);
        g.add(0, new int[]{});
        g.add(1, new int[]{2,5});
        g.add(2, new int[]{1,5,4,3});
        g.add(3, new int[]{2,4});
        g.add(4, new int[]{5,2,3});
        g.add(5, new int[]{1,2,4});

        System.out.println(g);

        g.remove(3);
        System.out.println(g);
	}

}
