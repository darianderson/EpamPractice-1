package ua.nure.veretelnyk.practice3;

import java.util.Map;
import java.util.TreeMap;

public class Part5 {
    private static TreeMap<Integer,String> romanData = new TreeMap<Integer, String>();
    static {
        romanData.put(1000, "M");
        romanData.put(900, "CM");
        romanData.put(500, "D");
        romanData.put(400, "CD");
        romanData.put(100, "C");
        romanData.put(90, "XC");
        romanData.put(50, "L");
        romanData.put(40, "XL");
        romanData.put(10, "X");
        romanData.put(9, "IX");
        romanData.put(5, "V");
        romanData.put(4, "IV");
        romanData.put(1, "I");
    }

    public static String decimal2Roman(int x) {
        int l =  romanData.floorKey(x);
        if ( x == l ) {
            return romanData.get(x);
        }
        return romanData.get(l) + decimal2Roman(x-l);
    }
    private static int getKey(String k){
        for(Map.Entry<Integer, String> entry : romanData.entrySet())
            if(entry.getValue().equals(k))
                return entry.getKey();
        return 0;
    }
    public static int roman2Decimal(String s) {
        int dec=0;
        int[] numbers = new int[s.length()];
        for(int i=0;i < s.length(); ++i){
            //System.out.println(getValue(String.valueOf(s.charAt(i))));
            numbers[i] = getKey(String.valueOf(s.charAt(i)));
        }
        dec += numbers[s.length()-1];
        for(int i=s.length()-2; i>=0;--i){
            if(numbers[i]<numbers[i+1])
                dec -= numbers[i];
            else
                dec += numbers[i];
        }
        return dec;
    }

    public static void main(String[] args) {
        for(int x=1; x<=100; ++x)
            System.out.println(x+" ====> "+decimal2Roman(x) +" ====> " +roman2Decimal(decimal2Roman(x)));
    }
}
