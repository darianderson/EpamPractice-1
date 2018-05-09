package ua.nure.kolesnikov.module9.Containers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class ArrayListStreamSorted {
	
	public static void main(String[] args) {
		List<String> col = Arrays.asList("adsf", "134", "+@!#$%", "����");
		
		col.forEach(System.out::println);
		
		System.out.println("~~~");
		
		col.stream().sorted().forEach(System.out::println);
		System.out.println("~~~");

		col.stream().sorted((s, s2) -> -s.compareTo(s2)).forEach(System.out::println);
		
	}

}
