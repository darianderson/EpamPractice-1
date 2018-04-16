package ua.nure.veretelnyk.practice4;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Part3 {
    public static void main(String[] args) throws IOException{
        BufferedReader file = new BufferedReader(
                new InputStreamReader(new FileInputStream("part3.txt"), "UTF-8"));

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
                    regex = "\\s\\D\\s";  // TODO fix it for single chars and start and after chars, that already founded
                    break;
                case "string":
                    regex = "[a-zA-Zа-яА-Я][a-zA-Zа-яА-Я]+";  // TODO change for every symbol here, multilanguage
                    break;
                case "double":
                    regex = "\\d?+\\.\\d+";
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
//word 34 haha l maybe 83 h lol 0.23 haha .287 s 98
// word boundary
