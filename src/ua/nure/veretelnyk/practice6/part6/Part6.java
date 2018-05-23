package ua.nure.veretelnyk.practice6.part6;

import ua.nure.veretelnyk.practice6.part1.Word;
import ua.nure.veretelnyk.practice6.part1.WordContainer;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    private static final String FILE_NAME = "data/practice6/part6.txt";

	public static void main(String[] args) {

        WordContainer words = new WordContainer();
        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME), "UTF-8");
            while (scanner.hasNextLine()) {
                sb.append(scanner.nextLine()).append(System.lineSeparator());
            }
        } catch (IOException ex) {
            ex.printStackTrace();
        }

        Matcher m = Pattern.compile("\\w+").matcher(sb.toString());
        while (m.find())
            words.add(new Word(m.group()));

        frequency(words);
        System.out.println("~~~~~~~~");

        length(words);
        System.out.println("~~~~~~~~");

        duplicates(words);
        System.out.println("~~~~~~~~");

	}


	private static void frequency (WordContainer words){ }

    private static void length (WordContainer words){ }

    private static void duplicates(WordContainer words) { }
}

