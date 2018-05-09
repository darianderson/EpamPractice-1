package ua.nure.kolesnikov.module9.Containers;

import java.util.Arrays;
import java.util.List;
import java.util.function.Consumer;

public class ArrayStreamPrint {
	
	public static void main(String[] args) {
		List<Integer> col = Arrays.asList(1, 3, 5, 2, 4);
		
		col.stream().forEach(new Consumer<Integer>() {
			public void accept(Integer t) {
				System.out.print(t);
			}
		});
		System.out.println();
		
		col.stream().forEach(System.out::print);
		System.out.println();
		
		col.stream().forEach((x) -> {
			System.out.print(x);
		});

		col.stream().forEach((x) -> System.out.print(x));


		///////////// SMTH else
//		List<Integer> col = Arrays.asList(1, 3, 5, 2, 4);
//
//		col.forEach(System.out::print);
//		System.out.println();
//
//		col.stream()
//				.filter((x) -> x % 2 == 0)
//				.forEach(System.out::print);
//		System.out.println();


	}

}
