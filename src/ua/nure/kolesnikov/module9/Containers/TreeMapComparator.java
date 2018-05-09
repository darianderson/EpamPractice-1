package ua.nure.kolesnikov.module9.Containers;

import java.util.Map;
import java.util.TreeMap;

public class TreeMapComparator {

	public static void main(String[] args) {
		//Map<A, String> map = new TreeMap<>();
		Map<A, String> map = new TreeMap<>((a, a2) -> -a.x + a2.x);

		map.put(new A(1), "A");
		System.out.println("~~~");

		map.put(new A(3), "C");
		System.out.println("~~~");

		map.put(new A(2), "B");
		System.out.println("~~~");

		map.put(new A(3), "D");
		System.out.println("~~~");

		System.out.println(map);
		
		for (A key : map.keySet()) {
			System.out.printf("%s ==> %s%n",
					key,
					map.get(key));
		}
	}


	static class A {
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
			A a = (A)obj;
			System.out.printf("A(%s).equals(%s)%n", x, a.x);
			return x == a.x;
		}

		@Override
		public int hashCode() {
			System.out.printf("A(%s).hashCode()%n", x);
			return 0;
		}



	}

}
