package ua.nure.kolesnikov.module9.comprasion;

import java.util.ArrayList;
import java.util.List;

public class ArrayListAdd {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("A");
		list.add("B");
		list.add("C");
		list.add("D");
		list.add("E");
		list.add("F");
		
		for (String el : list) { // iterator
			if (el == "F") {
				System.out.println("ok");
				list.remove(el);
			}
		}
	}

}

