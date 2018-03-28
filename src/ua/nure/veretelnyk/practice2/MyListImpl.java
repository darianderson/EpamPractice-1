package ua.nure.veretelnyk.practice2;

public class MyListImpl implements MyList {

    //  TO READ наследование, абстрактные классы, интерфейсы

    private Object[] list;

    MyListImpl() {
        list = new Object[0];
    }

    public String toString() {
        StringBuilder myResult= new StringBuilder("[ ");
        for(Object o : list){
            if (o == null)
                myResult.append("null ");
            else{
                String appendStr =o.toString() +" ";
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
        System.arraycopy(tmp,0,list,0,tmp.length);

        list[tmp.length] = e;
    }

    @Override
    public void clear() {
        list = new Object[0];
    }

    @Override
    public boolean remove(Object o) {
        boolean wasFounded = false;
        for(int  i = 0; i<list.length-1; ++i){
            if(list[i] == null){
                if(o == null)
                    wasFounded = true;
            }
            else if (list[i].equals(o))
                wasFounded = true;
            if(wasFounded){
                list[i] = list[i+1];
            }
            //System.out.println(list[i]+" "+wasFounded+" "+o);
        }
        if(list[list.length-1].equals(o) || wasFounded){
            Object[] tmp = list;
            list= new Object[list.length-1];
            System.arraycopy(tmp,0,list,0,list.length);
        }
        return wasFounded;
    }

    @Override
    public Object[] toArray() {
        Object[] tmp = new Object[list.length];
        System.arraycopy(list,0,tmp,0,list.length);
        return tmp;
    }

    @Override
    public int size() {
        return list.length;
    }

    @Override
    public boolean contains(Object o) {
        for (Object item : list){
            if(item == null){
                if(o == null)
                    return true;
            }
            else if (item.equals(o))
                return true;
        }
        return false;
    }

    @Override
    public boolean containsAll(MyList c) {
        Object[] o = c.toArray();
        for(int i =0;i<c.size(); ++i){
            if(!contains(o[i]))
                return false;
        }
        return true;
    }
}
