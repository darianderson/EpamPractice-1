package ua.nure.kolesnikov.module9.Containers;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class GoOnForMap{

	public static void main(String[] args) {
		Map<Integer, String> map = new HashMap<>();
		map.put(1, "A");
		map.put(3, "C");
		map.put(2, "B");

		System.out.println(map);

		// (1)
		for (Map.Entry<Integer, String> pair : map.entrySet()) {
			System.out.printf("<%s,%s> ", pair.getKey(), pair.getValue());
		}
		System.out.println();

		// (2)
		Iterator<Map.Entry<Integer, String>> it = map.entrySet().iterator();
		while (it.hasNext()) {
			Map.Entry<Integer, String> pair = it.next();
			System.out.printf("<%s,%s> ", pair.getKey(), pair.getValue());
		}
		System.out.println();

		// (3)
		Set<Integer> keys = map.keySet();
		for (Integer key : keys) {
			System.out.printf("<%s,%s> ", key, map.get(key));
		}
		System.out.println();
	}

}
