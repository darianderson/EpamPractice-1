package ua.nure.kolesnikov.module9.Containers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListStreamPrintLength {
	
	public static void main(String[] args) {
		List<String> col = Arrays.asList("adsf", "134", "@!#$%", "����");
		
		col.forEach(System.out::print);
		System.out.println();
		
		col.stream()
			.map(String::length)
			.forEach(System.out::print);
		System.out.println();

		Stream<Integer> stream = col.stream().map(String::length);
		System.out.println();
		
		System.out.println(col.stream());
	}

}
