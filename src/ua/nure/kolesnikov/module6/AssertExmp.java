package ua.nure.kolesnikov.module6;

public class AssertExmp {
	
	public static void main(String[] args) {
		assert  args.length != 0 : "CL arguments cannot be empty!";
		System.out.println(args.length);
		
		System.out.println("ok");
	}

}
