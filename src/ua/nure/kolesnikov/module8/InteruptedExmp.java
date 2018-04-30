package ua.nure.kolesnikov.module8;

public class InteruptedExmp {

	public static void main(String[] args) throws Exception {
		Thread t = Thread.currentThread();
		t.interrupt();
		System.out.println(t.isInterrupted());
		try {
			t.join();
			//Thread.sleep(1000);
			//synchronized ("asdf") { "asdf".wait(); }
		} catch (Exception ex) {
			System.out.println(t.isInterrupted());
		}
		System.out.println("Ok");

	}

	public static void main2(String[] args) throws InterruptedException {
		Thread t = Thread.currentThread();
		System.out.println(t.isInterrupted());
		System.out.println(t.isInterrupted());

		t.interrupt();

		System.out.println(t.isInterrupted());
		System.out.println(t.isInterrupted());
	}
}