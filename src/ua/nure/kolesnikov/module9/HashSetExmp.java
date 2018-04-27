package ua.nure.kolesnikov.module9;

import java.util.Collection;
import java.util.HashSet;

public class HashSetExmp {

	public static void main(String[] args) {
		Collection<A> col = new HashSet<>();
		col.add(new A(1));
		System.out.println("~~~");
		
		col.add(new A(2));
		System.out.println("~~~");

		col.add(new A(3));
		System.out.println("~~~");

		col.add(new A(2));
		System.out.println("~~~");
		
		System.out.println(col);
		
		System.out.println(col.contains(new A(3)));
	}

}

class A {
	int x;
	public A(int x) { this.x = x; }
	public String toString() { return "A(" + x + ")"; }
	
	@Override
	public boolean equals(Object obj) {
		A a = (A)obj;
		System.out.printf("A(%s).equals(%s)%n", x, a.x);
		return x == a.x;
	}
	
	@Override
	public int hashCode() {
		System.out.printf("A(%s).hashCode()%n", x);
		return x;
	}
	
}