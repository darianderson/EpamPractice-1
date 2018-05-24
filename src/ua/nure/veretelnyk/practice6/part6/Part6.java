package ua.nure.veretelnyk.practice6.part6;

import ua.nure.veretelnyk.practice6.part1.WordContainer;

import java.io.File;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part6 {

    private static final String FILE_NAME = "data/practice6/part6.txt";

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


        frequency(sb.toString());
        System.out.println("~~~~~~~~");

        //length(words);
        System.out.println("~~~~~~~~");

        //duplicates(words);
        System.out.println("~~~~~~~~");

	}

    // lostless hope
	private static void frequency (String words){
        Map<String, Integer> map = new HashMap<>();
        Matcher m = Pattern.compile("\\w+").matcher(words);
        while (m.find()){
            String wordGroup = m.group();
            if (map.containsKey(wordGroup))
                map.put(wordGroup, map.get(wordGroup) + 1);
            else
                map.put(wordGroup, 1);
        }

        Collection<Integer> collection = map.values();

	}

    private static void length (WordContainer words){ }

    private static void duplicates(WordContainer words) { }
}

