package ua.nure.veretelnyk.part1;

import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Part1 {

	public static void main(String[] args) {
		List<Word> list = new WordContainer();
		Scanner in = new Scanner(System.in);

		System.out.println("Enter words (stop to finish): ");
		String line = in.next();

        while ( !"stop".equals(line)){
            list.add(new Word(line));
            line = in.next();
        }
        list.sort(Comparator.naturalOrder());
		System.out.println(list);

	}

}
