package ua.nure.kolesnikov.module9.comprasion;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

public class TreeSetComparator {

	public static void main(String[] args) {
		// Set<A> set = new HashSet<>(); // <== hash container
		// Set<A> set = new TreeSet<>(); // <== binary tree container

		// binary tree container with comparator
//		Set<A> set = new TreeSet<>(new Comparator<A>() {
//			public int compare(A a, A a2) {
//				System.out.printf("Comp#compare(%s, %s)%n", a.x, a2.x);
//				return -a.x + a2.x;
//			}
//		});

		// comparator implementation with lambda expression
		Set<A> set = new TreeSet<>((a, a2) -> a.x - a2.x);

		set.add(new A(1));
		System.out.println("~~~");

		set.add(new A(3));
		System.out.println("~~~");

		set.add(new A(2));
		System.out.println("~~~");

		set.add(new A(4));
		System.out.println("~~~");

		System.out.println(set);
	}

}

class A implements Comparable<A> {

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
