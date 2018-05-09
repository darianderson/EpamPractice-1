package ua.nure.veretelnyk.practice6.part2;

import java.util.*;

public class Part2 {

    private static int n;
    private static int k;

	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		long start, end;
        System.out.print("Enter number of people: ");
		n = in.nextInt();
        System.out.print("Enter frequency: ");
        k = in.nextInt();
        if (k<0 && k>n)
            return;

        System.out.println();

        ArrayList<Object> arrayList = new ArrayList<>();
        LinkedList<Object> linkedList = new LinkedList<>();

        // adding objects to lists
        intit(arrayList, linkedList);

        start = System.nanoTime();
        processIndexed(arrayList);
        end = System.nanoTime();
        System.out.printf("Indexed. ArrayList. %.2f %n", (end-start)*Math.pow(10, -9) );

        start = System.nanoTime();
        processIndexed(linkedList);
        end = System.nanoTime();
        System.out.printf("Indexed. LinkedList. %.2f %n", (end-start)*Math.pow(10, -9) );


        arrayList.clear();
        linkedList.clear();
        intit(arrayList, linkedList);
        System.out.println();


        start = System.nanoTime();
        processIterable(arrayList);
        end = System.nanoTime();
        System.out.printf("Iterable. ArrayList. %.2f %n", (end-start)*Math.pow(10, -9) );

        start = System.nanoTime();
        processIterable(linkedList);
        end = System.nanoTime();
        System.out.printf("Iterable. LinkedList. %.2f %n", (end-start)*Math.pow(10, -9) );

//        List<Object> list = new ArrayList<>();
//        for(int i=0; i<10; ++i)
//            list.add(i);
//        System.out.println(list);
//        processIterable(list);

	}

	private static void intit(ArrayList<Object> arrayList, LinkedList<Object> linkedList){
        for(int i=0; i<n; ++i) {
            arrayList.add(new Object());
            linkedList.add(new Object());
        }
    }

	private static void processIndexed(List<Object> list){
	    int carriage = 0;
        while (list.size() != 1){
            carriage += (k-1);
            carriage %= list.size();

            list.remove(carriage);
        }
    }

    private static void processIterable(List<Object> list){
        Iterator  it = list.iterator();
        while (list.size() != 1){

            for (int i=0; i<k; ++i) {
                if (!it.hasNext())
                    it = list.iterator();

                it.next();
            }

            it.remove();
        }
    }
}

// разрешен и запрещен индексированный доступ

// удаление на контейнере разрещено
// удаление на контейнере запрещено, только на итераторе


//        [0, 1, 2, 3, 4, 5, 6, 7, 8, 9]
//        [0, 1, 3, 4, 5, 6, 7, 8, 9]
//        [0, 1, 3, 4, 6, 7, 8, 9]
//        [0, 1, 3, 4, 6, 7, 9]
//        [0, 3, 4, 6, 7, 9]
//        [0, 3, 4, 7, 9]
//        [3, 4, 7, 9]
//        [3, 4, 9]
//        [3, 9]
//        [3]


//        Enter number of people: 100000
//        Enter frequency: 3
//
//        Indexed. ArrayList. 0.43
//        Indexed. LinkedList. 5.75
//
//        Iterable. ArrayList. 0.42
//        Iterable. LinkedList. 0.01