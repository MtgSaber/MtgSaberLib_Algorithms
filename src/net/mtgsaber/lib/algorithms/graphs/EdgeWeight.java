package net.mtgsaber.lib.algorithms.graphs;

/**
 * A simple, generic wrapper for an edge weight.
 * @param <E>
 */
public interface EdgeWeight<E, V> extends Edge<E, V> {
    double getWeight();
}
