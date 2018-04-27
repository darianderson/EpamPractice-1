package ua.nure.kolesnikov.module6;

public class FinallyExmp {

	static int x = 7;
	
	static int m() {
		try {
			boolean f = false;
			if (f) 
				throw new Exception();
		} catch (Exception ex) {
			return x;
		} finally {
			x = 100;
		}
		return x;
	}

	public static void main(String[] args) throws Exception {
		System.out.println(m());
	}

}