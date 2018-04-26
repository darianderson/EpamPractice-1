package ua.nure.kolesnikov.module2;

public class NewArrayExmp {
	
	public static void main(String[] args) {
		F f = int[]::new;
		
		int[] ar = f.m(5);
		System.out.println(java.util.Arrays.toString(ar));
	}

}

interface F {
	int[] m(int x);
}
