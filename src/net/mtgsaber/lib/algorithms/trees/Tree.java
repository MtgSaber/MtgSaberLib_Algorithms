package net.mtgsaber.lib.algorithms.trees;

public interface Tree<T> {
    Node<T> getRoot();
    int getHeight();
    int getSize();
}
