package net.mtgsaber.lib.algorithms.graphs;

import net.mtgsaber.lib.algorithms.Pair;

public interface Graph<V, E> {
    E[] getEdges();
    V[] getVertices();
    E[] getEdges(V vertex);
    Pair<V, V> getVertices(E edge);
    String toString();
}
