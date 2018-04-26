package ua.nure.kolesnikov.module2;

public class LocalInterface {
	
	public static void main(String[] args) {
		H h = "asdf"::charAt;
		System.out.println(h.m(2));
	}

}

// String "asdfasdfasdf".charAt(int)
interface H {
	char m(int x); 
}
