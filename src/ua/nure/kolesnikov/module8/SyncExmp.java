package ua.nure.kolesnikov.module8;

public class SyncExmp {

	private boolean flag;

	// try to remove synchronized key word!!!
	public synchronized void m(boolean flag) {
			this.flag = flag;
			try {
				Thread.sleep(200);
			} // ����
			catch (InterruptedException e) {
				e.printStackTrace();
			}
			System.out.println(this.flag + " == " + flag);
	}

	public static void main(String[] args) {
		final SyncExmp t = new  SyncExmp();

		new Thread() {
			public void run() {
				while (true) {
					t.m(true);
					try {
						sleep(1);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}.start();

		new Thread() {
			public void run() {
				while (true) {
					t.m(false);
					try {
						sleep(1);
					} catch (Exception ex) {
						ex.printStackTrace();
					}
				}
			}
		}.start();
	}
}
