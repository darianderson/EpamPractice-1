package ua.nure.veretelnyk.practice6.part5;

public class Tree<E extends Comparable<E>> {
	
	private static final String INDENT = "   ";

	private Node<E> root = null;
	
	public boolean remove(E element) {
	    root = remove(element, root);
	    return true;
    }
    private Node remove(E e, Node node){
	    if (node == null) return node;

	    int result = e.compareTo((E)node.value);
	    if (result<0)
	        node.left = remove(e, node.left);
	    else if (result>0)
	        node.right = remove(e, node.right);
	    else{
	        if(node.left == null)
	            return node.right;
	        else if (node.right == null)
	            return node.left;

	        node.value = minValue(node.right);
	        node.right = remove((E) node.value, node.right);
        }
        return node;
    }

    @Override
    public String toString() {
        return toString(root);
    }

    private String toString(Node root)
    {
        StringBuilder builder = new StringBuilder();
        if (root == null)
            return "";
        builder.append(toString(root.left));
        builder.append(toString(root.right));
        return builder.append(root.value.toString()).toString();
    }

    private E minValue(Node node){
	    E min = (E) node.value;
	    while (node.left!=null){
	        min= (E) node.left.value;
	        node = node.left;
        }
        return min;
    }

	private Node findWithParrent(E e, Node parrent){
	    Node current = root;
	    parrent = null;

	    while (current!=null){
	        int result = e.compareTo((E) current.value);
	        if(result<0){
	            parrent = current;
	            current = current.left;
            }
            else if (result>0){
	            parrent = current;
	            current = current.right;
            }
            else break;
        }
        return current;
    }
	public void add(E[] elements) {
	    for(E e : elements)
	        add(e);
	}
	
	public boolean add(E e) {
	    if (root == null) {
            root = new Node<>(e);
            return true;
        }
		return add(e, root);
	}
	private boolean add(E e, Node n){
	    int compareResult = e.compareTo((E) n.value);
	    if(compareResult > 0){ // e less that n.value
            if (n.left == null) {
                n.left = new Node<>(e);
                return true;
            }
            else
                return add(e, n.left);
        }
        else{
	        if (n.right == null) {
                n.right = new Node<>(e);
                return true;
            }
	        else
                return add(e, n.right);
        }
    }

//    public void print() {
//
//    }
	
	private static class Node<E> {
		E value;
		Node<E> left;
		Node<E> right;
		public Node(E value) {
		    this.value = value;
		    left=null;
		    right=null;
		}
    }

}
