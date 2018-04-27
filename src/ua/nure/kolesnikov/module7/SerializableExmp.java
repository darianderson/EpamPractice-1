package ua.nure.kolesnikov.module7;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class SerializableExmp{

	public static void main(String[] args) throws Exception {
		
		A a = new A(777);
		ObjectOutputStream oos = new ObjectOutputStream(
				new FileOutputStream("a.obj"));
		
		oos.writeObject(a);
		oos.close();
		
		System.out.println("~~~");
		
		ObjectInputStream ois = new ObjectInputStream(
				new FileInputStream("a.obj"));
		Object o = ois.readObject();
		System.out.println(o);
		ois.close();
		
	}

}

class A implements Serializable {
	
	private static final long serialVersionUID = -1L;
	
	private transient int x;

	public A(int x) {
		this.x = x;
		System.out.println("A#constr(" + x + ")");
	}

	public String toString() {
		return "A [x=" + x + "]";
	}

}
