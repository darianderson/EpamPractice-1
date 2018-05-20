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
	private static WordContainer words = new WordContainer();

	public static void main(String[] args) {
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

        frequency();
        System.out.println(words.get(0) + "\n" + words.get(1) +"\n" + words.get(2));
	}
	private static void frequency(){
	    words.sort(Comparator.naturalOrder());
    }

}