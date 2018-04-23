package ua.nure.veretelnyk.practice4;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part1 {
    private static final String FILE_PATH = "data/practice4/part1.txt";

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE_PATH), "UTF-8"));
        String line;
        Pattern p = Pattern.compile("(?U)(?:\\b|\\d)(\\w{3,})(?:\\b|\\d)");
        // TODO matches only for words covered by digits or boundary
        while ((line = in.readLine()) != null){
            Matcher m = p.matcher(line);
            while (m.find()) {
                line = line.replace(m.group(), m.group().toUpperCase());
            }
            System.out.println(line);
        }

        in.close();
    }
}

/*
Hello, how r u My friend ?
х2алехин,     друг.
Как ты   ПоЖиваешь ?
 */

//shatterproof cookie cutter life
//cold driven heavier than heaven