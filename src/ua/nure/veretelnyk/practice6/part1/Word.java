package ua.nure.veretelnyk.practice6.part1;

public class Word implements Comparable {
	
	private String word;
	
	private int frequency;

	public Word(String word) {
		this.word = word;
		frequency = 1;
	}

	public void increase(){ frequency++; }

	@Override
	public boolean equals(Object obj) {
	    if (obj instanceof Word){
            Word w = (Word) obj;
            return word.equals(w.word);
	    }
	    else
	        return super.equals(obj);
	}

    @Override
    public String toString() {
        return word+": "+frequency;
    }

    @Override
    public int compareTo(Object o) {
        Word w = (Word) o;
        if (w.frequency != frequency)
            return w.frequency - frequency;
        else
            return word.compareTo(w.word);
    }

    public int frequency() { return frequency; }

}
