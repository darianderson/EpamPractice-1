package ua.nure.kolesnikov.module9.Containers;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;

public class StreamToUpperCase {
	
	public static void main(String[] args) {
		List<String> col = Arrays.asList("adSF", "@!#$%", "����");
		
		col.forEach(System.out::println);
		System.out.println();
		
		col.stream()
			.map(String::toUpperCase)
			.forEach(System.out::println);
		System.out.println();
	}

}
