package ua.nure.kolesnikov.module9;

import java.util.Collection;
import java.util.TreeSet;

public class TreeSetExmp {

	public static void main(String[] args) {
		Collection<C> col = new TreeSet<>();
		col.add(new C(1));
		System.out.println("~~~");
		
		col.add(new C(5));
		System.out.println("~~~");

		col.add(new C(3));
		System.out.println("~~~");

		col.add(new C(4));
		System.out.println("~~~");

		col.add(new C(3));
		System.out.println("~~~");

		
		System.out.println(col);
		
		System.out.println(col.contains(new C(3)));
	}

}

class C implements Comparable<C> {
	int x;
	public C(int x) { this.x = x; }
	public String toString() { return "A(" + x + ")"; }

	@Override
	public boolean equals(Object obj) {
		ua.nure.kolesnikov.module9.A a = (ua.nure.kolesnikov.module9.A)obj;
		System.out.printf("A(%s).equals(%s)%n", x, a.x);
		return x == a.x;
	}

	@Override
	public int hashCode() {
		System.out.printf("A(%s).hashCode()%n", x);
		return x;
	}

	@Override
	public int compareTo(C a) {
		System.out.printf("A(%s).compareTo(%s)%n", x, a.x);
		return x - a.x;
	}

}
