package ua.nure.veretelnyk.practice5.part1;

public class RunnableImpl implements Runnable {
    @Override
    public void run() {
        for(int i=0; i<6; ++i){
            System.out.println(Thread.currentThread().getName());
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                //e.printStackTrace();
            }
        }
    }
}
