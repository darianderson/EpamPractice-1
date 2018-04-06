package ua.nure.veretelnyk.practice3;


import java.util.Arrays;
import java.util.Random;

public class Part1 {
    public static void main(String[] args) {
        String input = Util.getInput("Part1Input.txt");
        System.out.println("~~~~~~~~~~~~");
        System.out.println(convert1(input));
        System.out.println("~~~~~~~~~~~~");
        System.out.println(convert2(input));
        System.out.println("~~~~~~~~~~~~");
        System.out.println(convert3(input));
        System.out.println("~~~~~~~~~~~~");
        System.out.println(convert4(input));
    }
    private static String[][] getData(String input){
        String[] lines = input.split("\r\n");
        String[][] data = new String[lines.length][3];
        for(int i=0; i<lines.length; ++i)
            data[i] = lines[i].split(";");
        return data;
    }
    public static String convert1(String input){
        String[][] data = getData(input);
        StringBuilder builder = new StringBuilder();
        for(int i=1; i < data.length; ++i){
            builder.append(data[i][0]).append(" ==> ").append(data[i][2]).append("\n");
        }
        return builder.toString();
    }
    public static String convert2(String input){
        String[][] data = getData(input);
        StringBuilder builder = new StringBuilder();
        for(int i=1; i < data.length; ++i){
            builder.append(data[i][1]).append(" (email:  ").append(data[i][2]).append(")\n");
        }
        return builder.toString();
    }
    public static String convert3(String input){
        String[][] data = getData(input);
        StringBuilder builder = new StringBuilder();

        for(int i=1;i<data.length; ++i){
            String domain = data[i][2].split("@")[1];
            if(!domain.equals("0")) {

                builder.append(domain).append(" ==> ");
                for (int j = i; j < data.length; ++j) {

                    String currentDomain = data[j][2].split("@")[1];
                    if (currentDomain.equals(domain)) {
                        builder.append(data[j][0]).append(", ");
                        data[j][2] = "0@0";
                    }

                }
                builder.append("\n");

            }
        }

        return builder.toString();
    }

    public static String convert4(String input){
        String[] data = input.split("\r\n");
        StringBuilder builder = new StringBuilder();
        Random R = new Random();

        builder.append(data[0]).append(";Password\n");
        for(int i=1; i<data.length; ++i)
            builder.append(data[i]).append(";").append(R.nextInt(9000)+1000).append("\n");

        return builder.toString();
    }
}
