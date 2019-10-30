package net.mtgsaber.lib.algorithms.trees.linkedlist;

import java.util.*;

public class DoubleLinkedList<T> implements List<T> {
    private DoubleLinkNode<T> head, tail;
    private int count;

    @Override
    public int size() {
        return count;
    }

    @Override
    public boolean isEmpty() {
        return count <= 0;
    }

    @Override
    public boolean contains(Object o) {
        for (T item : this)
            if (item.equals(o))
                return true;
        return false;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        Object[] ret = new Object[count];
        int i = 0;
        for (T item : this)
            ret[i++] = item;
        return ret;
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        T1[] ret = Arrays.copyOf(a, count);
        int i = 0;
        for (T item : this)
            ret[i++] = ((T1) item);
        return ret;
    }

    @Override
    public boolean add(T t) {
        tail.setNext(new DoubleLinkNode<>(t, tail, null));
        tail = tail.getNext();
        return true;
    }

    @Override
    public boolean remove(Object o) {
        DoubleLinkNode<T> cursor;
        for (cursor = head; cursor != null && !cursor.getData().equals(o); cursor = cursor.getNext());
        if (cursor == null)
            return false;
        cursor.getPrev().setNext(cursor.getNext());
        cursor.getNext().setPrev(cursor.getPrev());
        return true;
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        boolean check;
        for (Object o : c) {
            check = false;
            for (T item : this)
                if (item.equals(o)) {
                    check = true;
                    break;
                }
            if (!check)
                return false;
        }
        return true;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        for (T item : c)
            add(item);
        return true;
    }

    @Override
    @Deprecated
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    @Deprecated
    public boolean removeAll(Collection<?> c) {
        for (Object o : c)
            remove(o);
        return true;
    }

    @Override
    @Deprecated
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {
        head = null;
        tail = null;
    }

    @Override
    public T get(int index) {
        int i = 0;
        DoubleLinkNode<T> cursor;
        for (cursor = head; cursor != null && i++ < index; cursor = cursor.getNext());
        if (cursor == null)
            return null;
        return cursor.getData();
    }

    @Override
    @Deprecated
    public T set(int index, T element) {
        return null;
    }

    @Override
    @Deprecated
    public void add(int index, T element) {

    }

    @Override
    @Deprecated
    public T remove(int index) {
        return null;
    }

    @Override
    public int indexOf(Object o) {
        DoubleLinkNode<T> cursor;
        int i = 0;
        for (cursor = head; cursor != null && !cursor.getData().equals(o); cursor = cursor.getNext())
            i++;
        if (cursor == null)
            return -1;
        return i;
    }

    @Override
    public int lastIndexOf(Object o) {
        DoubleLinkNode<T> cursor;
        int i = count;
        for (cursor = tail; cursor != null && !cursor.getData().equals(o); cursor = cursor.getPrev())
            i--;
        if (cursor == null)
            return -1;
        return i;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null; // TODO: implement
    }

    @Override
    @Deprecated
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null; // TODO: implement
    }
}
