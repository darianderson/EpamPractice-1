package ua.nure.veretelnyk.part2;

import java.util.Scanner;

public class Spam {
    private long[] times;
    private String[] messages;
    private Thread[] threads;
    private int length;

    public Spam(String[] msgs, long[] t) {
        length = t.length;
        times = new long[length];
        messages = new String[length];
        System.arraycopy(t,0, times,0,length);
        System.arraycopy(msgs,0,messages,0,length);
        threads = new Thread[length];
    }

    public void start() {
        for(int i=0; i<length; ++i)
            threads[i] = new WrightEvery(messages[i], times[i]);

        for(int i=0; i<length; ++i)
            threads[i].start();
    }

    public void stop() throws InterruptedException {
        for(int i=0; i<length; ++i)
            threads[i].interrupt();
    }

    public static void main(String[] args) throws InterruptedException {
        Spam spam = new Spam(new String[] {"hi", "lol", "spam", "kid"},
                new long[] {3000, 4000, 2000, 5000} );
        spam.start();

        // wait for Enter
        Scanner in = new Scanner(System.in);
        in.nextLine(); // return only if Enter obtained
        in.close();

        spam.stop();
    }

}