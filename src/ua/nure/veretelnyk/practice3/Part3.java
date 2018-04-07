package ua.nure.veretelnyk.practice3;

public class Part3 {
    /**
     * Дан текст. Напечатать текст, поставив первый символ каждого слова в верхний регистр.
     */
    public static void main(String[] args) {
        String input = Util.getInput("Part3Input.txt");

        StringBuilder builder = new StringBuilder(input);
        char[] spaces = {' ', '\t','\n','\n','\f','\r'};

        for (int i=0; i<input.length(); ++i){
            for (char space : spaces) {
                if (input.charAt(i) == space) {
                    builder.setCharAt(++i, Character.toUpperCase(input.charAt(i)));
                }
            }
        }
        System.out.println(builder);

        /*
        String[] words = input.split("\\p{Space}");
        StringBuilder builder = new StringBuilder();
        for(int i=0; i<words.length; ++i){
            builder.append(words[i].replaceAll(
                    String.valueOf(words[i].charAt(0)),
                    String.valueOf(Character.toUpperCase(words[i].charAt(0))))).append(" ");*/
        /*
        StringBuilder sb = new StringBuilder(input);
        for(int i=1; i<input.length(); ++i){
            if(sb.charAt(i-1) == ' '){
                String first = String.valueOf(sb.charAt(i));
                sb.setCharAt(i,first.toUpperCase().charAt(0));
            }
        }
        System.out.println(sb);
        */


//        Up And Replacement
    }
}



















