package ua.nure.kolesnikov.module6;

public class GetSuppressedExmp {

	public static void main(String[] args) {
		try (A a = new A()) {
			throw new Error();
		} catch (Error ex) {
			System.out.println("catch 1");
			
			System.out.println(
					ex.getSuppressed().length);
			
			System.out.println(
					ex.getSuppressed()[0]);
			
			ex.getSuppressed()[0].printStackTrace();
			
			
		} catch (Exception ex) {
			System.out.println("catch 2");
		}

	}
}

class A implements AutoCloseable {
	public A() {
		System.out.println("A#constr");
	}

	public void close() throws Exception {
		System.out.printf("A#close%n");
		throw new Exception();
	}

}
