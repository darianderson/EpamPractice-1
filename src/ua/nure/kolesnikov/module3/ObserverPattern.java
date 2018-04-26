package ua.nure.kolesnikov.module3;

import java.util.ArrayList;
		import java.util.List;

public class ObserverPattern {

	public static void main(String[] args) {
		Observable source = new ConcreteObservable();
		source.add(new ObserverA());
		source.add(new ObserverB());

		source.add(new Observer() {
			public void handleEvent() {
				System.out.println("Also has been notified");
			}
		});


		source.fireEvent();
	}

}

interface Observable {
	void add(Observer o);
	void fireEvent();
}

class ConcreteObservable implements Observable {

	private List<Observer> observers;

	public ConcreteObservable() {
		observers = new ArrayList<>();
	}

	public void add(Observer o) {
		observers.add(o);
	}

	public void fireEvent() {
		for (Observer o : observers) {
			o.handleEvent();
		}
	}

}


interface Observer {
	void handleEvent();
}

class ObserverA implements Observer {
	public void handleEvent() {
		System.out.println("ObserverA has been notifed");
	}
}

class ObserverB implements Observer {
	public void handleEvent() {
		System.out.println("ObserverB has been notifed");
	}
}
