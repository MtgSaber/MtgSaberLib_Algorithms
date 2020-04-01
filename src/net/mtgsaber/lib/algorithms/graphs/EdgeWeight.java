package net.mtgsaber.lib.algorithms.graphs;

/**
 * A simple, generic wrapper for an edge weight.
 * @param <T>
 */
public interface EdgeWeight<T> {
    double getWeight();
    T getValue();
}
