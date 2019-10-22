package net.mtgsaber.lib.algorithms.trees.binary;

import net.mtgsaber.lib.algorithms.trees.Node;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Structure implemented. Algorithms NYI.
 * @param <T>
 */
public class BinaryTreeNode<T> implements Node<T> {
    private T val;
    private BinaryTreeNode<T> left, right;

    public BinaryTreeNode(T val, BinaryTreeNode<T> left, BinaryTreeNode<T> right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public BinaryTreeNode(T val) {
        this.val = val;
    }

    public BinaryTreeNode<T> getLeft() {
        return left;
    }

    public void setLeft(BinaryTreeNode<T> left) {
        this.left = left;
    }

    public BinaryTreeNode<T> getRight() {
        return right;
    }

    public void setRight(BinaryTreeNode<T> right) {
        this.right = right;
    }

    @Override
    public T getVal() {
        return val;
    }

    @Override
    public void setVal(T val) {
        this.val = val;
    }

    @Override
    public List<Node<T>> getChildren() {
        return new ArrayList<>(Arrays.asList(left, right));
    }
}
