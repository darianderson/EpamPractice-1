package ua.nure.kolesnikov.module2;

public class Anonymous {

	public static void main(String[] args) {
		A a = new A(5778) {
			void m() {
				System.out.println("Anonymous#m");
			}

			void m2() {
				System.out.println("Anonymous#m2");
			}
		};
		
		
		G g = new G() {
			public void n() {System.out.println("Anonymous#n");}
		};
		g.n();
	}

}

interface G {
	void n();
}

class A {
	A() {System.out.println("constr A");}
	A(int x) {System.out.println("constr A(int)");}
	
	void m() {
		System.out.println("A#m");
	}
}
