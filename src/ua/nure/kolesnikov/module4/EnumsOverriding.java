package ua.nure.kolesnikov.module4;

public class EnumsOverriding{

	public static void main(String[] args) {
		SeasonOvr s2 = Enum.valueOf(SeasonOvr.class, "SPRING");
		System.out.println(s2); 
		
		SeasonOvr s3 = SeasonOvr.valueOf("WINTER");
		System.out.println(s3);
		System.out.println("~~~");
		
		for (SeasonOvr s : SeasonOvr.values()) {
			System.out.println(s);
		}
	}



}

enum SeasonOvr {
	SPRING(12) {
		public String toString() {
			return super.toString() + " !!!!!!!!";
		}
	}
	, SUMMER(24), AUTUMN(8), WINTER;

	SeasonOvr() {
		this(0);
	}

	SeasonOvr(int t) {
		this.t = t;
	}

	private int t;

	public int getT() {
		return t;
	}

	public String toString() {
		return super.toString() + " t=" + t;
	}

}

class SeasonClassOvr {

	public static final SeasonClassOvr SPRING = new SeasonClassOvr(12) {
		public String toString() {
			return super.toString() + " !!!!!!!!";
		}
	};
	public static final SeasonClassOvr SUMMER = new SeasonClassOvr(24);
	public static final SeasonClassOvr AUTUMN = new SeasonClassOvr(8);
	public static final SeasonClassOvr WINTER = new SeasonClassOvr();

	private SeasonClassOvr() {
		this(0);
	}

	private SeasonClassOvr(int t) {
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