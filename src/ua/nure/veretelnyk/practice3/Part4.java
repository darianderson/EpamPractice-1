package ua.nure.veretelnyk.practice3;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;

public class Part4 {
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
    public static void main(String[] args) throws NoSuchAlgorithmException {
        System.out.println(hash("password", "SHA-256"));
        System.out.println(hash("passwort", "SHA-256"));
    }
}
