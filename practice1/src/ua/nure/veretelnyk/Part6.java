package ua.nure.veretelnyk;

public class Part6 {

    private static boolean isPrime(int n){
        if (n == 2)
            return true;
        if (n%2 == 0)
            return false;
        for(int i = 3; i <= Math.sqrt(n); i+=2){
            if (n%i == 0)
                return false;
        }
        return true;
    }

    public static void main(String[] args) {
        int size = Integer.parseInt(args[0]);
        int[] simple = new int[size];
        int selection = 0;
        for(int i = 2; selection < size; ++i){
            if(isPrime(i)){
                simple[selection] = i;
                selection++;
            }
        }
        for (int n : simple){
            System.out.print(n+" ");
        }
        System.out.println();
    }
}