package ua.nure.kolesnikov.module9.comprasion;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.TreeSet;

public class ArrayListComparator {

	public static void main(String[] args) {
//		Collection<A> set = new HashSet<>(); // <== hash container
//		Collection<A> set = new TreeSet<>(); // <== hash container
		Collection<A> set = new ArrayList<>(); // <== hash container



		set.add(new A(1));
		System.out.println("~~~");

		set.add(new A(3));
		System.out.println("~~~");

		set.add(new A(2));
		System.out.println("~~~");

		set.add(new A(4));
		System.out.println("~~~");

		System.out.println(set);
		
		System.out.println(set.contains(new A(4)));
	}


	static class A implements Comparable<A> {

		int x;

		public A(int x) {
			this.x = x;
		}

		@Override
		public String toString() {
			return "A(" + x + ")";
		}

		@Override
		public boolean equals(Object obj) {
			int x2 = ((A) obj).x;
			System.out.printf("A(%s)#equals(%s)%n", x, x2);
			return x == x2;
		}

		@Override
		public int hashCode() {
			System.out.printf("A(%s)#hashCode()%n", x);
			return x;
		}

		@Override
		public int compareTo(A a) {
			System.out.printf("A(%s)#compareTo(%s)%n", x, a.x);
			return x - a.x;
		}
	}


}
