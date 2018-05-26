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

        StringBuilder sb = new StringBuilder();
        try {
            Scanner scanner = new Scanner(new File(FILE_NAME), "UTF-8");
            while (scanner.hasNextLine())
                sb.append(scanner.nextLine()).append(System.lineSeparator());

        } catch (IOException ex) {
            ex.printStackTrace();
        }

        List<String> list = new ArrayList<>();
        Matcher m = Pattern.compile("\\w+").matcher(sb.toString());
        while (m.find())
            list.add(m.group());


        String[] words = list.toArray(new String[0]);

        frequency(words);
        System.out.println("~~~~~~~~");

        length(words);
        System.out.println("~~~~~~~~");

        duplicates(words);
        System.out.println("~~~~~~~~");

	}


	private static void frequency (String[] words){
        WordContainer container = new WordContainer();

        for(String word : words)
            container.add(new Word(word));

        container.sort(Comparator.naturalOrder());
        int counter=0;

        for(Word word : container){
            System.out.println(word);
            if (++counter == 3)
                break;
        }
	}

    private static void length (String[] words){
        Set<String> set = new TreeSet<>((o1, o2) ->
                o2.length()!=o1.length() ? o2.length()- o1.length() : o1.compareTo(o2) );
        set.addAll( Arrays.asList(words) );
        int count=0;


        for(String word : set){
            System.out.println(word + " ==> " + word.length());
            if (++count == 3)
                break;
        }
    }

    private static void duplicates(String[] words) {
	    int counter = 0;
	    String[] wordsPrinted = new String[3];
	    for(int i=0; i<words.length-1; ++i)
	        for(int j=i+1; j<words.length; ++j)
	            if( words[i].equals(words[j]) ) {
	                if (counter != 0 && wordsPrinted[counter-1].equals(words[i]))
	                    break;
	                wordsPrinted[counter] = words[i];
                    System.out.println((new StringBuilder(words[i].toUpperCase()).reverse()));
                    if (++counter == 3)
                        return;
                }

    }
}


// Down in Ashes - Show You How
// We Are The Empty - Rogue