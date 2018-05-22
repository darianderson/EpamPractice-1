package ua.nure.veretelnyk.practice6.part7;

import java.util.Iterator;
import java.lang.Integer;

public class Range implements Iterable {
    private Integer n;
    private Integer m;
    private boolean reverse;
    private Integer current;
    private RangeIterator iterator;

    Range(int n, int m) { this(n,m,false); }

    Range(int n, int m, boolean reverse) {
        if (n>m)
            throw new IllegalArgumentException();
        this.n = n;
        this.m = m;
        this.reverse = reverse;

        current = reverse ? m+1 : n-1;
        iterator = new RangeIterator();
    }

    private class RangeIterator implements Iterator{

        @Override
        public boolean hasNext() {
            if (reverse)
                return current-1 >= n;
            else
                return current+1 <= m;
        }

        // TODO rewrite with Integer or something

        @Override
        public Integer next() {
            if (reverse)
                return --current;
            else
                return ++current;
        }
    }

    @Override
    public Iterator iterator() {
        return iterator;
    }
}
