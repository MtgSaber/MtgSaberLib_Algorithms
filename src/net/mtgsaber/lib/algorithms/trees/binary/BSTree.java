package net.mtgsaber.lib.algorithms.trees.binary;

import java.util.Collection;
import java.util.Comparator;

/**
 *  NYI.
 *
 * @param <T>
 */
public class BSTree<T> extends BinaryTree<T> {
    private Comparator<T> comparator;
    private int count, height;

    /**
     * Stub, NYI.
     * @return
     */
    public BSTree(Comparator<T> comparator, Collection<T> initialValues) {
        // TODO: sort values, then add in a binary-search fashion.
    }

    /**
     * Stub, NYI.
     * @return
     */
    public BSTree(Comparator<T> comparator, T[] initialValues) {

    }

    /**
     * Stub, NYI.
     * @return
     */
    public BSTree(Comparator<T> comparator) {

    }

    /**
     * Stub, NYI.
     * @return
     */
    public void add(T val) {

    }

    /**
     * Stub, NYI.
     * @return
     */
    public boolean remove(T val) {
        return false;
    }

    /**
     * Stub, NYI.
     * @return
     */
    public boolean search(T val) {
        return false;
    }
}
