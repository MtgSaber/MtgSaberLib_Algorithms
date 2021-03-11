package net.mtgsaber.lib.algorithms.graphs;

import net.mtgsaber.lib.algorithms.Pair;

public interface Graph<E, V> {
    Edge<E, V>[] getEdges();
    V[] getVertices();
    Edge<E, V>[] getIncidentEdges(V vertex);
    V[] getAdjacentVertices(V vertex);
    Pair<V, V> getVertices(Edge<E, V> edge);
    String toString();
}
