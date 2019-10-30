package net.mtgsaber.lib.algorithms.trees.linkedlist;

import java.util.Iterator;

public class SLLIterator<T> implements Iterator<T> {
    private SingleLinkNode<T> cur;

    public SLLIterator(SingleLinkNode<T> head) {
        this.cur = head;
    }

    @Override
    public boolean hasNext() {
        return cur != null;
    }

    @Override
    public T next() {
        T ret = cur.DATA;
        cur = cur.next;
        return ret;
    }
}
