package ua.nure.kolesnikov.module6;

public class CloseResurese {

	public static void main(String[] args) {
		try (B a = new B(1); B a2 = new B(2)) {
			System.out.println("try");
			int x = 7 / 0; // NPE
		} catch (Exception ex) {
			System.out.println("catch");
		} finally {
			System.out.println("finally");
		}
	}

}

class B implements AutoCloseable {
	private int x;
	public B(int x) {System.out.printf("A#constr(%s)%n", x); this.x = x;}
	public void close() { System.out.printf("A#close, x=%s%n", x); }
	
	


}
