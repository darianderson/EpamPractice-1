package ua.nure.kolesnikov.module7;

public class ReadinByBytes {

	private static final String EOL = System.lineSeparator();

	public static void main(String[] args) throws Exception {

		byte[] ar = new byte[2];
		String line = null;

		while (!("stop" + EOL).equals(line)) {
			int n = System.in.read(ar, 0, 2);
			line = new String(ar, 0, n);
			System.out.print("*");
			System.out.print(line);
		}

		System.out.println("ok");
	}
}
