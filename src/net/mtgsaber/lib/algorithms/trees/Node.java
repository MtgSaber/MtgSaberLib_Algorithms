package net.mtgsaber.lib.algorithms.trees;

import java.util.List;

public interface Node<T> {
    T getVal();
    void setVal(T val);
    List<Node<T>> getChildren();
}
