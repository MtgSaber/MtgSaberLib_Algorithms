package net.mtgsaber.lib.algorithms.trees.linkedlist;

public class DoubleLinkNode<T> {
    private DoubleLinkNode<T> prev, next;
    private final T DATA;

    public DoubleLinkNode( T data, DoubleLinkNode<T> prev, DoubleLinkNode<T> next) {
        this.DATA = data;
        this.prev = prev;
        this.next = next;
    }

    public DoubleLinkNode<T> getPrev() {
        return prev;
    }

    public DoubleLinkNode<T> getNext() {
        return next;
    }

    public T getData() {
        return DATA;
    }

    public void setPrev(DoubleLinkNode<T> prev) {
        this.prev = prev;
    }

    public void setNext(DoubleLinkNode<T> next) {
        this.next = next;
    }
}
