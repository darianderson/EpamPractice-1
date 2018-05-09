package ua.nure.veretelnyk.practice6.part3;

import java.util.ArrayList;

public class Parking {
    private ArrayList<Object> list = new ArrayList<>();

    public Parking(int n){
        for(int i=0; i<n; ++i)
            list.add(null);
    }


    public boolean arrive(Object car) { return arrive(car, 0); }
    public boolean arrive(Object car, int spot){
        if (car == null)
            return false;
        // check if there is no such car
       if (list.contains(car) || spot >= list.size())
            return false;


       while ( spot < list.size() && list.get(spot) != null)
           spot++;

       // if we get to the last one, but it is still occupied
       if ( spot >= list.size())
           return false;


       //System.out.printf("%s: %s%n",spot,list.size());
       list.set(spot, car);
        return true;
    }

    public boolean depart(Object car){
        if (car == null)
            return false;

        int index = list.indexOf(car);
        if (index != -1) {
            list.set(index, null);
            return true;
        }

        return false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(Object o : list)
            sb.append(o==null ? 0 : 1).append(" ");
        return sb.toString();
    }
}
