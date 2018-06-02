package ua.nure.veretelnyk.practice9.part2_2;

import java.util.ArrayList;
import java.util.List;

public class SportRow {
    private String sport;
    private int count=0;
    private List<String> names = new ArrayList<>();

    public SportRow(String sport) {
        this.sport = sport;
    }

    public String getSport() {
        return sport;
    }

    public void insertName(String name){
        names.add(name);
        count++;
    }

    @Override
    public String toString() {
        return "SportRow{" +
                "sport='" + sport + '\'' +
                ", count=" + count +
                ", names=" + names +
                '}' + '\n';
    }

    public int getCount() {
        return count;
    }

    public List<String> getNames() {
        return names;
    }
}
