package ua.nure.veretelnyk.practice2;

import java.util.Iterator;

public class MyListImpl implements MyList, ListIterable {

    @Override
    public ListIterator listIterator() { return new ListIteratorImpl(); }

    private class ListIteratorImpl extends IteratorImpl implements ListIterator {

        @Override
        public boolean hasPrevious() {
            return super.carriage>=0 && super.carriage<list.length;
        }

        @Override
        public Object previous() {
            super.lastElementCalled = list[super.carriage--];
            return super.lastElementCalled;
        }

        @Override
        public void set(Object e) {
            if (super.carriage<0 || super.carriage>=list.length)
                throw new IllegalStateException();
            list[super.carriage] = e;
        }
    }

    //  TO READ наследование, абстрактные классы, интерфейсы
    private class IteratorImpl implements Iterator<Object> {

        private int carriage = -1;
        private Object lastElementCalled = -1;

        // returns true if the iteration has more elements
        public boolean hasNext() {
            return carriage<list.length-1;
        }

        // returns the next element in the iteration
        public Object next() {
            lastElementCalled = list[++carriage];
            return lastElementCalled;
        }

        // removes from the underlying collection the last element
        //returned by this iterator
        public void remove() {
            if ( carriage == -1 || carriage >= list.length || !list[carriage].equals(lastElementCalled))
                throw new IllegalStateException();
            else{
                Object[] tmp = new Object[list.length-1];
                System.arraycopy(list,0,tmp,0,carriage);
                System.arraycopy(list,carriage+1,tmp,carriage,list.length - carriage-1);
                list = new Object[list.length-1];
                System.arraycopy(tmp,0,list,0,list.length);
                carriage--;
            }
        }
    }


    public Iterator<Object> iterator() {
        return new IteratorImpl();
    }

    private Object[] list;

    MyListImpl() {
        list = new Object[0];
    }

    public String toString() {
        StringBuilder myResult = new StringBuilder("[ ");
        for (Object o : list) {
            if (o == null)
                myResult.append("null ");
            else {
                String appendStr = o.toString() + " ";
                myResult.append(appendStr);
            }
        }
        myResult.append("]");
        return myResult.toString();
    }

    @Override
    public void add(Object e) {
        Object[] tmp = list;
        list = new Object[list.length + 1];
        System.arraycopy(tmp, 0, list, 0, tmp.length);

        list[tmp.length] = e;
    }

    @Override
    public void clear() {
        list = new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        boolean wasFounded = false;
        for (int i = 0; i < list.length - 1; ++i) {
            if (list[i] == null) {
                if (o == null)
                    wasFounded = true;
            } else if (list[i].equals(o))
                wasFounded = true;
            if (wasFounded) {
                list[i] = list[i + 1];
            }
            //System.out.println(list[i]+" "+wasFounded+" "+o);
        }
        if (list[list.length - 1].equals(o) || wasFounded) {
            Object[] tmp = list;
            list = new Object[list.length - 1];
            System.arraycopy(tmp, 0, list, 0, list.length);
        }
        return wasFounded;
    }
 // 1 2 3 4 5
    @Override
    public Object[] toArray() {
        Object[] tmp = new Object[list.length];
        System.arraycopy(list, 0, tmp, 0, list.length);
        return tmp;
    }

    @Override
    public int size() {
        return list.length;
    }

    @Override
    public boolean contains(Object o) {
        for (Object item : list) {
            if (item == null) {
                if (o == null)
                    return true;
            } else if (item.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(MyList c) {
        Object[] o = c.toArray();
        for (int i = 0; i < c.size(); ++i) {
            if (!contains(o[i]))
                return false;
        }
        return true;
    }
}
