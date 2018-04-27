package ua.nure.kolesnikov.module9;

import java.util.Collection;
import java.util.Comparator;
import java.util.TreeSet;

class ComparaotrExmp {

	public static void main(String[] args) {
		Comparator<D> c = new Comparator<D>() {
			public int compare(D a, D a2) {
				System.out.printf("Comp.compare(%s, %s)%n", a.x, a2.x);
				return -a.x + a2.x; 
			}
		};
		
		
 		Collection<D> col = new TreeSet<D>(c);
		
		col = new TreeSet<>((a, a2) -> a.x - a2.x);
		
		col.add(new D(1));
		System.out.println("~~~");
		
		col.add(new D(5));
		System.out.println("~~~");

		col.add(new D(3));
		System.out.println("~~~");

		col.add(new D(4));
		System.out.println("~~~");

		col.add(new D(5));
		System.out.println("~~~");

		
		System.out.println(col);
		
		System.out.println(col.contains(new D(3)));
	}

}

class D implements Comparable<A> {
	int x;
	public D(int x) { this.x = x; }
	public String toString() { return "A(" + x + ")"; }
	
	@Override
	public boolean equals(Object obj) {
		D a = (D)obj;
		System.out.printf("A(%s).equals(%s)%n", x, a.x);
		return x == a.x;
	}
	
	@Override
	public int hashCode() {
		System.out.printf("A(%s).hashCode()%n", x);
		return x;
	}
	
	@Override
	public int compareTo(A a) {
		System.out.printf("A(%s).compareTo(%s)%n", x, a.x);
		return x - a.x;
	}
	
}