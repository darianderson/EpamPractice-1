package ua.nure.kolesnikov.module9;

public class GInterface {
	
	public static void main(String[] args) {

	}

}


@FunctionalInterface
interface G {
	void m();
	boolean equals(Object o);
	int hashCode();
}