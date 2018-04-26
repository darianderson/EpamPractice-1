package ua.nure.kolesnikov.module2;

public class EqualsAndCache {
	
	public static void main(String[] args) {
		Integer x = 225;
		Integer y = 225;
		System.out.println(x == y);  // false
		System.out.println(x.equals(y)); //true

		System.out.println(System.getProperty("java.lang.Integer.IntegerCache.hig")); // null
	}

}

