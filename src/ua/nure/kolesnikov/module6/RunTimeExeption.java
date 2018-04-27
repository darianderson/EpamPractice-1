package ua.nure.kolesnikov.module6;

import java.io.IOException;

public class RunTimeExeption {

	public static void main(String[] args) {
		new RunTimeExeption();
	}
	public RunTimeExeption(){
		A a = new B();
		try {
			a.m();
		} catch (RuntimeException ex) {
			Throwable t = ex.getCause();
			t.printStackTrace();
		}
	}

	class A {
		void m() {

		}
	}

	class B extends A {
		void m() {
			try {
				throw new IOException("asdf");
			} catch (IOException ex) {
				throw new RuntimeException(ex);
			}
		}
	}
}

