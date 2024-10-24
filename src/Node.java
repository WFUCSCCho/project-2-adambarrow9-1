// @file: Node.java
// @description: implements a Node class for the purpose of creating Node objects
// @author: Adam Barrow
// @date: September 23, 2024


public class Node<T extends Comparable<T>> {

    // establish elements of node
    private T value;
    private Node<T> left;
    private Node<T> right;

    // Implement the constructor
    public Node(T value) {
        this.value = value;
        this.left = null;
        this.right = null;
    }

    // Implement the setElement method
    public void setElement(T value) {
        this.value = value;
    }

    // Implement the setLeft method
    public void setLeft(Node<T> left){
        this.left = left;
    }

    // Implement the setRight method
    public void setRight(Node<T> right){
        this.right = right;
    }

    // Implement the getLeft method
    public Node<T> getLeft(){
        return this.left;
    }

    // Implement the getRight method
    public Node<T> getRight(){
        return this.right;
    }

    // Implement the getElement method
    public T getElement(){
        return this.value;
    }

    // Implement the isLeaf method
    public boolean isLeaf(){
        return this.left == null && this.right == null;
    }
}
