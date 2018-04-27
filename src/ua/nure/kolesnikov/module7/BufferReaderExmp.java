package ua.nure.kolesnikov.module7;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class BufferReaderExmp {

	public static void main2(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(new FileInputStream("input.txt"), "Cp1251"));
		String line = null;
		while ((line = in.readLine()) != null) {
			System.out.println(line);
		}

		in.close();
	}

	public static void main(String[] args) throws IOException {
		try (BufferedReader in = new BufferedReader(
				new InputStreamReader(new FileInputStream("input.txt"), "Cp1251"))) {
			String line = null;
			while ((line = in.readLine()) != null) {
				System.out.println(line);
			}
		}

	}
}
