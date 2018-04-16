package ua.nure.veretelnyk.practice4;

import java.io.*;

public class Part1 {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream("part1.txt"), "UTF-8"));
        String line;
        while ((line = in.readLine()) != null){
            String[] words = line.split(" ");
            for (String word : words) {
                if (word.length() > 3) {
                    String upperWord = word.toUpperCase();
                    line = line.replaceAll(word, upperWord);
                }
            }
            // TODO not showing words with numbers
            System.out.println(line);
        }

        in.close();
    }
}
