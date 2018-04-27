package ua.nure.veretelnyk.practice5.part2;

public class WrightEvery extends Thread {
    private String message;
    private long time;

    public WrightEvery(String message, long time) {
        this.message = message;
        this.time = time;
    }

    @Override
    public void run() {
        while (true){
            try {
                sleep(0);
            } catch (InterruptedException e) {
                return;
            }
            System.out.println(message);

            if (isInterrupted())
                return;
        }
    }
}
