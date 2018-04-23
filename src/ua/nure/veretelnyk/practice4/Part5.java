package ua.nure.veretelnyk.practice4;

import java.util.Locale;
import java.util.ResourceBundle;
import java.util.Scanner;

public class Part5 {
    private static final String RES_STR = "resources";

    public static void main(String[] args) {
        Locale ru = new Locale("ru", "RU","Moscow");
        Locale en = Locale.ENGLISH;
        Locale.setDefault(ru);
        ResourceBundle res = ResourceBundle.getBundle(RES_STR);
        Scanner in = new Scanner(System.in);
        String line;
        while ( (line = in.nextLine()).trim() != "stop"){
            System.out.println(res.getString(line.trim()));
        }
    }
}