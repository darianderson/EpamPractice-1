package ua.nure.veretelnyk.practice4;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

public class Part4 implements Iterable {

    private static final String FILE_PATH = "data/practice4/Part4.txt";
    public static void main(String[] args) throws IOException {
        Part4 p = new Part4();
        for (Object aP : p) {
            System.out.println(aP);
        }
    }

    private String[] sentences;
    private Part4() throws IOException {
        BufferedReader in = new BufferedReader(
                new InputStreamReader(new FileInputStream(FILE_PATH), "UTF-8"));
        String line;

        ArrayList<String> list = new ArrayList<>();
        while ( (line = in.readLine()) != null){
            String[] sent = line.split("(?<!\\w\\.\\w.)(?<![A-Z][a-z]\\.)(?<=[.?!])\\s"); // TODO Change symbol
            list.addAll(Arrays.asList(sent));
        }

        sentences = new String[list.size()];
        for(int i=0; i<sentences.length; ++i)
            sentences[i] = list.get(i);

        in.close();
    }

    class IteratorImpl implements Iterator{
        int carriage = -1;
        @Override
        public boolean hasNext() {
            return carriage+1 < sentences.length;
        }

        @Override
        public String next() {
            return sentences[++carriage];
        }

        @Override
        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
    @Override
    public Iterator iterator() {
        return new IteratorImpl();
    }
}
