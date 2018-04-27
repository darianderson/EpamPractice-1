package ua.nure.kolesnikov.module8;

public class MultiThreadingExmp {

	public static void main(String[] args) throws Exception {
		Thread t = new MyThread();
		System.out.println(t.getState());
		
	//	t.run();
		t.start();
		
		Thread t2 = new Thread(new MyThread2());
		t2.start();
	}
}


class MyThread extends Thread {
	public void run() {
		while (true) {
			System.out.println(getName());
			try { sleep(333); }
			catch (Exception e) { e.printStackTrace(); }
		}
	}
}

class MyThread2 implements Runnable {

	@Override
	public void run() {
		while (true) {
			System.out.println(Thread.currentThread().getName());
			try { Thread.sleep(1333); }
			catch (Exception e) { e.printStackTrace(); }
		}
		
	}
	
}

