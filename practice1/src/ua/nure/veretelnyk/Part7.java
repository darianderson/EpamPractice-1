package ua.nure.veretelnyk;

public class Part7 {
    private static final int A_ASCII = 64;// actually +1
    private static final int LETTERS = 26;

    public static int chars2digits(String number) {
        int value = 0;
        for (int i = 0; i < number.length(); ++i) {
            int key = (int) number.charAt(number.length() - 1 - i) - A_ASCII;
            value += key * Math.pow(LETTERS, i);
            //System.out.println(number.charAt(i) + " -> " + value);//+"|"+key
        }
        return value;
    }

    /*public static int getGreatestPower(int n) {
        int greatestPower = LETTERS;
        int power = 1;
        while (greatestPower < n) {
            greatestPower = (int) Math.pow(greatestPower, power);
            power++;
        }
        return (int) Math.pow(LETTERS, power - 1);
    }*/

    public static int Gp(int n) {
        return 1;
    }

    public static class Row {
        static int key = 1;
        static char[] ch = {'A'};

        public static void setToZero() {
            key = 1;
            ch = new char[1];
            ch[0] = 'A';
        }

        public static void incriment() {
            key++;
            ch[ch.length - 1]++;
            for (int i = ch.length - 1; i > 0; --i) {
                if (ch[i] == '[') {
                    ch[i] = 'A';
                    ch[i - 1]++;
                } else
                    break;
            }
            if (ch[0] > 'Z') {
                ch[0] = 'A';
                char[] tmp = new char[ch.length];
                for (int i = 0; i < tmp.length; ++i)
                    tmp[i] = ch[i];
                ch = new char[ch.length + 1];
                ch[0] = 'A';
                for (int i = 1; i < ch.length; ++i) {
                    ch[i] = tmp[i - 1];
                }
            }
        }
    }

    public static String digits2chars(int n) {
        String value = "";
        // Адекватное решение, но не рабочее
        /*
        for (int i = Gp(2); i >= 0; --i) {
            int key = (int) Math.floor(n / Math.pow(LETTERS, i));
            char ch = (char) (key + A_ASCII);
            value += ch;
            n -= key * LETTERS;
        }
        */

        // не очень адекватное решение, но рабочее
        for (int i = 1; i < n; ++i) {
            Row.incriment();
        }
        String s = "";
        for (int i = 0; i < Row.ch.length; ++i)
            s += Row.ch[i];
        Row.setToZero();
        return s;
    }

    public static String rightColumn(String number) {
        int key = chars2digits(number);
        String rightColumn = digits2chars(++key);
        return rightColumn;
    }

    public static void main(String[] args) {
        //System.out.println(chars2digits("ZZ"));
        System.out.println(
                "\nA -> " + chars2digits("A") + " -> " + digits2chars(chars2digits("A")) +
                        "\nB -> " + chars2digits("B") + " -> " + digits2chars(chars2digits("B")) +
                        "\nZ -> " + chars2digits("Z") + " -> " + digits2chars(chars2digits("Z")) +
                        "\nAA -> " + chars2digits("AA") + " -> " + digits2chars(chars2digits("AA")) +
                        "\nAZ -> " + chars2digits("AZ") + " -> " + digits2chars(chars2digits("AZ")) +
                        "\nBA -> " + chars2digits("BA") + " -> " + digits2chars(chars2digits("BA")) +
                        "\nZZ -> " + chars2digits("ZZ") + " -> " + digits2chars(chars2digits("ZZ")) +
                        "\nAAA -> " + chars2digits("AAA") + " -> " + digits2chars(chars2digits("AAA")));
    }
}
// 1234
// 4*10^0 + 3*10^1 +...

// AA
//  1*26^0 +1*26^1

//27
// A

// 702
//   0