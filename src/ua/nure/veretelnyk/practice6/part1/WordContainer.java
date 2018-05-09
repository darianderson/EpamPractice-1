package ua.nure.veretelnyk.practice6.part1;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;

// you can extend this class from one of the core container
// or aggregate it inside of class 
public class WordContainer extends ArrayList<Word> {

    @Override
    public boolean add(Word insert) {

        for (Word word : this){
            if (word.equals(insert)) {
                word.increase();
                return true;
            }
        }
        super.add(insert);

        return true;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (Object w : this)
            sb.append(w).append(System.lineSeparator());

        return sb.toString();
    }

}