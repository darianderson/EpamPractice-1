package ua.nure.kolesnikov.module2;

public class InternalClasses{

	public static void main(String[] args) {
		T.B b = new T.B();
		b.m();

		/////////////
		
		T t = new T();
		T.A a = t.new A();
		a.m();
		System.out.println("~~~");
		System.out.println(t);
	}

}

class T {
	class A {

		void m() {
			System.out.println(this);
			System.out.println(T.this);
		}
	}

	static class B {
		void m() {System.out.println("B#m");}
	}
}
