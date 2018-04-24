package ua.nure.veretelnyk.practice5.part3;

public class Counter {
    private int count1 = 0;
    private int count2 = 0;

    public synchronized void increment() throws InterruptedException {
        count1++;
        Thread.sleep(10);
        count2++;
        System.out.println( (count1 == count2) + ": " + Thread.currentThread().getName());
    }

    public void countOneIncrement(){
        count1++;
    }

    public void countTwoIncrement(){
        count2++;
    }

    public boolean isEquals(){ return count1 == count2; }

}
