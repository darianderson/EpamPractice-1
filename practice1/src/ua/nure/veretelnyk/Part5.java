package ua.nure.veretelnyk;

public class Part5 {
    public static void main(String[] args) {
        int n = Integer.parseInt(args[0]);
        int sum = 0;

        while (n > 0) {
            sum += n % 10;
            n = (int) Math.floor(n / 10);
        }
        System.out.println(sum);
    }
}
