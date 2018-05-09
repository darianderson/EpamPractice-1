package ua.nure.kolesnikov.module9.Containers;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class GoOnForList {
	
	public static void main(String[] args) {
		List<Integer> col = Arrays.asList(1, 3, 5, 2, 4);
		//Collection<Integer> col = Arrays.asList(1, 3, 5, 2, 4);
		
		System.out.println(col);
		
		// (1)
		for (Integer el : col) {
			System.out.print(el + " ");
		}
		System.out.println();

		// (2)
		Iterator<Integer> it = col.iterator();
		while (it.hasNext()) {
			Integer el = it.next();
			System.out.print(el + " ");
		}
		System.out.println();

		// (3)
		for (int j = 0; j < col.size(); j++) {
			Integer el = col.get(j);
			System.out.print(el + " ");
		}
		System.out.println();

		// (4)
		for (Integer el : col.toArray(new Integer[0])) {
			System.out.print(el + " ");
		}
		System.out.println();

		// (5)
		for (Object el : col.toArray()) {
			System.out.print(el + " ");
		}
		System.out.println();
		
		

	}

}
