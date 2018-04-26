package ua.nure.kolesnikov.module4;

public class EnumsExmp {
	public static void main2(String[] args_mama_myla_ramu) {
		SeasonClassOvr s = SeasonClassOvr.AUTUMN;
		System.out.println(s.getT());
		System.out.println(s);

		System.out.println("~~~");
		s = SeasonClassOvr.SPRING;
		System.out.println(s.getT());
		System.out.println(s);
	}

	public static void main(String[] args) {
		Class<SeasonOvr> c = SeasonOvr.class;
		System.out.println(c.getSuperclass());
		
		
	}
}

enum Season {
	SPRING(12), SUMMER(24), AUTUMN(8), WINTER;

	Season() {
		this(0);
	}

	Season(int t) {
		this.t = t;
	}

	private int t;

	public int getT() {
		return t;
	}

	public String toString() {
		return "SeasonOvr, t=" + t;
	}

}

class SeasonClass {

	public static final SeasonClass SPRING = new SeasonClass(12) {
		public String toString() {
			return super.toString() + " !!!!!!!!";
		}
	};
	public static final SeasonClass SUMMER = new SeasonClass(24);
	public static final SeasonClass AUTUMN = new SeasonClass(8);
	public static final SeasonClass WINTER = new SeasonClass();

	private SeasonClass() {
		this(0);
	}

	private SeasonClass(int t) {
		this.t = t;
	}

	private int t;

	public int getT() {
		return t;
	}

	public String toString() {
		return "SeasonOvr, t=" + t;
	}
}