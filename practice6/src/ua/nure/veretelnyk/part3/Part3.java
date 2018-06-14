package ua.nure.veretelnyk.part3;

public class Part3 {
	
	public static void main(String[] args) {
        Parking p = new Parking(10);
        System.out.println("Init: \n" + p + "\n");

        p.arrive(new Object());
        System.out.println("One car to the start: \n" + p + "\n");

        p.arrive(new Object());
        p.arrive(new Object());
        p.arrive(new Object());
        System.out.println("Three cars to ther start: \n" + p + "\n");

        Object car = new Object();
        p.arrive(car, 6);
        p.arrive(new Object(), 6);
        System.out.println("Two cars from spot 6: \n" + p + "\n");

        p.depart(car);
        System.out.println("Remove car: \n" + p + "\n");

        p.arrive(new Object(), 7);
        p.arrive(new Object(), 7);
        p.arrive(new Object(), 7);
        System.out.println("Three more cars to spot 7: \n"+ p + "\n");

        System.out.println(p.arrive(null));
        System.out.println("Add null: \n" + p + "\n");
    }
}
