package ua.nure.kolesnikov.module8;

public class NotifyExmp {

	public static void main(String[] args) throws Exception {
		
		new Thread() {
			public void run() {
				try {
					sleep(3000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
				System.out.println("Child tries to notify main thread");
				synchronized("asdf") {
					"asdf".notify();
				}
			}
		}.start();
		
		synchronized ("asdf") {
			"asdf".wait();
		}
		
		System.out.println("ok");

	}
}