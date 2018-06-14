package ua.nure.veretelnyk;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Part4 {
    /**
     * Написать статический метод, который на вход принимает два параметра:
     * (1) строку, хеш которой нужно получить; (2) названия алгоритма хеширования.
     * Выход  должен представлять из себя строку из шестнадцатеричных цифр:
     * каждому байту соответствует две шестнадцатеричные цифры.
     * Например, если некоторый элемент массива байт равен -29,
     * то в двоичном разложении он имеет вид 1110_0011 и ему соответствует пара E3.
     */
    public static String hash(String input, String algorithm)  throws NoSuchAlgorithmException {
        MessageDigest digest = MessageDigest.getInstance(algorithm);
        digest.update(input.getBytes());
        byte[] hash = digest.digest();
        String[] hash16 = new String[hash.length];
        for (int i=0; i<hash.length; ++i) {
            Byte b = hash[i];
            hash16[i] = Integer.toHexString(b.intValue());
        }
        return Arrays.toString(hash16);
    }

    /**
     * Для хеширования информации (например, паролей) используют метод MessageDigest#digest,
     * который возвращает хеш в виде массива байт.
     */
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("password", "SHA-256"));
        System.out.println(hash("passwort", "SHA-256"));
    }
}
