package ua.nure.veretelnyk.practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    private static final String FILE_PATH = "data/practice4/part3.txt";

    public static void main(String[] args) throws IOException{
        BufferedReader file = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE_PATH), "UTF-8"));

        StringBuilder sb = new StringBuilder();
        String FileLine;
        while ( (FileLine = file.readLine()) != null)
            sb.append(FileLine).append(" ");
        file.close();

        System.out.println(sb);
        Scanner in = new Scanner(System.in);
        String line;
        while ( ! "stop".equals(line = in.nextLine())){
            String regex = "";
            switch (line){
                case "char":
                    regex = "(?U)\\b\\w\\b";
                    break;
                case "string":
                    regex = "(?U)\\b[^\\d\\W]{2,}\\b";
                    break;
                case "double":
                    regex = "[+-]?([0-9]*[.])[0-9]+";
                    break;
                default:
                case "int":
                    regex = "\\s\\d+\\s";
                    break;
            }
            Pattern p = Pattern.compile(regex);
            Matcher m = p.matcher(sb.toString());
            while (m.find()) {
                System.out.print(m.group() + " ");
            }
        }
    }
}