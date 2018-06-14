package ua.nure.veretelnyk;

import java.util.Arrays;

public class Part2 {
    private static final String FILE_PATH = "practice3/data/Part2Input.txt";
    /**
     * Дан текст. Найти и напечатать все слова максимальной и все слова минимальной длины.
     * Словом считать последовательность содержащую только буквы (все остальные символы в состав слова не входят).
     */
    public static void main(String[] args) {
        String input = Util.getInput(FILE_PATH);
        input = input.replaceAll("\\p{Punct}\\p{Space}", " ");
        String[] words = input.split("[\\p{Punct}\\p{Space}]");
        bubbleSort(words);
        StringBuilder min = new StringBuilder();
        for(int i=1; i<words.length; ++i) {
            if (words[0].length() == words[i].length())
                min.append(words[i]).append(",");
            else
                break;
        }
//        for(String w :words)
//            System.out.println(w);
        StringBuilder max = new StringBuilder();
        for(int i=words.length-2; i>=0; --i) {
            if (words[words.length-1].length() == words[i].length())
                max.append(words[i]).append(",");
            else
                break;
        }

        String[] minArr = min.toString().split(",");
        String[] maxArr = max.toString().split(",");
        Arrays.sort(minArr);
        Arrays.sort(maxArr);

        minArr = removeSame(minArr);
        maxArr = removeSame(maxArr);

        StringBuilder builder = new StringBuilder();
        builder.append("Min: ");
        for(String w : minArr)
            builder.append(w).append(", ");
        builder.append("\nMax: ");
        for(String w : maxArr)
            builder.append(w).append(", ");
        builder.append("\n");

        System.out.println(builder);
        //return builder.toString();
    }

    private static String[] removeSame(String[] arr){
        for(int i=1; i<arr.length;++i){
            if(arr[i].equals(arr[i-1])){
                String[] tmp = new String[arr.length-1];
                System.arraycopy(arr,0,tmp,0,i);
                System.arraycopy(arr,i+1,tmp,i,tmp.length-i);
                arr = new String[tmp.length];
                System.arraycopy(tmp,0,arr,0,tmp.length);
                --i;
            }
        }
        return arr;
    }

    private static void bubbleSort(String[] arr){
        String tmp;

        for (int i = 0; i < arr.length; ++i) {
            for (int j = 1; j < arr.length - i; ++j) {
                if (arr[j - 1].length() > arr[j].length()) {
                    tmp = arr[j - 1];
                    arr[j - 1] = arr[j];
                    arr[j] = tmp;
                }
            }
        }
    }
}
