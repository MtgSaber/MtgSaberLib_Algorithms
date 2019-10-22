package net.mtgsaber.lib.algorithms.trees.binary;

import net.mtgsaber.lib.algorithms.trees.Node;
import net.mtgsaber.lib.algorithms.trees.Tree;

public class BinaryTree<T> implements Tree<T> {
    protected BinaryTreeNode<T> root;

    @Override
    public Node<T> getRoot() {
        return null;
    }

    /**
     * Stub, NYI.
     * @return
     */
    @Override
    public int getHeight() {
        return 0;
    }

    /**
     * Stub, NYI
     * @return count
     */
    @Override
    public int getSize() {
        return 0;
    }

    /**
     * Stub, NYI.
     * @return
     */
    public T[] traverseInOrder() {
        return null;
    }

    /**
     * Stub, NYI.
     * @return
     */
    public T[] traversePreorder() {
        return null;
    }

    /**
     * Stub, NYI.
     * @return
     */
    public T[] traversePostorder() {
        return null;
    }
}
