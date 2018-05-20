package ua.nure.veretelnyk.practice6.part5;

public class Tree<E extends Comparable<E>> {
    private Node<E> root = null;
    private static final String TAB = "\t";

    public boolean add(E element){
        if (contains(root, element))
            return false;
        if (root == null) {
            root = new Node<>(null, element);
            return true;
        }
        else
            return add(root, element);
    }
    private boolean contains(Node<E> n, E element) {
//        return findParent(element) != null;
        return n != null &&
                (n.value.equals(element)
                        || contains(n.left, element)
                        || contains(n.right, element));
    }
    private boolean add(Node<E> node, E element){
        if (element.compareTo(node.value) < 0){
            if (node.left == null) {
                node.left = new Node<>(node, element);
                return true;
            }
            else
                add(node.left, element);
        }
        else {
            if (node.right == null){
                node.right = new Node<>(node, element);
                return true;
            }
            else
                add(node.right, element);
        }
        return false;
    }

    public void add(E[] elements){
        for(E element : elements)
            add(element);
    }

    public boolean remove(E element){
        boolean toReturn = false;
        if (contains(root, element))
            toReturn = true;
        root = deleteRec(root, element);
        return toReturn;
    }


    private Node<E> deleteRec(Node<E> node, E element){
        if (node == null) return node;

        int res = element.compareTo(node.value);
        if(res < 0)
            node.left = deleteRec(node.left, element);
        else if (res > 0)
            node.right = deleteRec(node.right, element);
        else {
            if (node.left == null)
                return node.right;
            else if (node.right == null)
                return node.left;

            node.value = minValue(node.right);
            node.right = deleteRec(node.right, node.value);
        }

        return node;
    }

    private E minValue(Node<E> root){
        E minv = root.value;
        while (root.left != null){
            minv = root.left.value;
            root = root.left;
        }
        return minv;
    }

    public void print() {
        if (root == null)
            return;

        if (root.right != null)
            print(root.right, 1);

        if (root != null)
            System.out.println(root.value);

        if (root.left != null)
            print(root.left, 1);

    }

    private void print(Node<E> node, int c) {
        if (node.right == null && node.left == null) { // both child not exists
            for (int i = 0; i < c; i++)
                System.out.print(TAB);

            System.out.println(node.value);
        }
        else if (node.right != null && node.left != null) { // both child exists
            print(node.right, c + 1);
            for (int i = 0; i < c; i++)
                System.out.print(TAB);

            System.out.println(node.value);
            print(node.left, c + 1);
        }
        else if (node.right != null) { // only left exists
            print(node.right, c + 1);
            for (int i = 0; i < c; i++)
                System.out.print(TAB);

            System.out.println(node.value);
        }
        else if (node.left != null) { // only right exists
            for (int i = 0; i < c; i++)
                System.out.print(TAB);

            System.out.println(node.value);
            print(node.left, c + 1);
        }
    }

    private static class Node<E>{
        E value;
        Node<E> parent;
        Node<E> left;
        Node<E> right;

        Node(Node<E> parent, E element){
            this.parent = parent;
            this.value = element;
            left = null;
            right = null;
        }
    }
}
