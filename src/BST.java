// @file: BST.java
// @description: implements BST class with necessary methods
// @author: Adam Barrow
// @date: October 24, 2024

import java.util.Iterator;
import java.util.Stack;


// main class to create BST
public class BST<T extends Comparable<T>> implements Iterable<T>{

    // establishing an object of the Node class as the root
    private Node<T> root;

    // Implement the constructor
    public BST() {
        root = null;
    }

    // Implement the clear method
    public void clear(){
        root = null;
    }

    // tests if BST is empty
    public boolean isEmpty(){
        return root == null;
    }


    // Implement the size method
    public int size(){
        int count = 0;
        Iterator<T> iterator = this.iterator();
        while(iterator.hasNext()){
            iterator.next();
            count++;
        }
        return count;
    }


    // Implement the insert method
    public void insert(T data) {
        root = insertHelp(root, data);
    }

    // helper for insert method
    private Node<T> insertHelp(Node<T> node, T data) {

        if (search(data)){
            return node;
        }
        else {
            if (node == null) {
                return new Node<T>(data);
            }
            if (node.getElement().compareTo(data) > 0) {
                node.setLeft(insertHelp(node.getLeft(), data));
            }
            else {
                node.setRight(insertHelp(node.getRight(), data));
            }
            return node;
        }
    }


    // Implement the remove method

    public Node<T> remove (T data) {

        if (!search(data)){
            return null;
        }
        else {
            root = removeHelp(root, data);
        }
        return root;
    }

    // helper for remove method
    private Node<T> removeHelp(Node<T> node, T data) {
        if (node == null) {
            return null;
        }

        if (node.getElement().compareTo(data) > 0) {
            node.setLeft(removeHelp(node.getLeft(), data));
        }
        else if (node.getElement().compareTo(data) < 0) {
            node.setRight(removeHelp(node.getRight(), data));
        }
        // one child
        else {
            if (node.getElement() == null){
                return node.getRight();
            }
            else if (node.getRight() == null) {
                return node.getLeft();
            }
            // two children
            Node<T> successor = getMin(node.getRight());
            node.setElement(successor.getElement());
            node.setRight(removeHelp(node.getRight(), successor.getElement()));
        }
        return node;
    }

    // helper method to get minimum for replacement on removal
    private Node<T> getMin(Node<T> node) {
        while (node.getLeft() != null) {
            node = node.getLeft();
        }
        return node;
    }





    // Implement the search method
//    public Node<T> search(T value) {
//        Iterator<T> iterator = this.iterator();
//        while (iterator.hasNext()) {
//            T current = iterator.next();
//            if (current.equals(value)) {
//                return new Node(iterator.next());
//            }
//        }
//        return null;
//    }

    // helper method for search, compares values and moves through tree accordingly
    private boolean search(T x, Node<T> t) {
        if (x == null || t == null) {
            return false;
        }
        int compareResult = x.compareTo(t.getElement());
        if (compareResult < 0) {
            return search(x, t.getLeft());
        }
        else if (compareResult > 0) {
            return search(x, t.getRight());
        }
        else {
            return true;
        }
    }
    // public method for search with one data argument
    public boolean search(T data) {
        return search(data, root);
    }

    // implement the print method
    public String print(){
        StringBuilder result = new StringBuilder();
        for (T t : this) {
            result.append(t).append(" ");
        }
        return result.toString().trim();
    }


    // Implement the iterator method
    @Override
    public Iterator<T> iterator() {
        return new BSTIterator<>(root);
    }


    // Implement the BSTIterator class
    private static class BSTIterator<T extends Comparable<T>> implements Iterator<T>{
        private Stack<Node<T>> stack = new Stack<>();

        public BSTIterator(Node<T> root) {
            pushLeft(root);
        }

        private void pushLeft(Node<T> node) {
            while(node != null) {
                stack.push(node);
                node = node.getLeft();
            }
        }
        // returns true if there is a next element
        @Override
        public boolean hasNext() {
            return !stack.isEmpty();
        }

        // finds next element
        @Override
        public T next() {
            Node<T> node = stack.pop();
            if (node.getRight() != null) {
                pushLeft(node.getRight());
            }
            return node.getElement();
        }
    }
}
