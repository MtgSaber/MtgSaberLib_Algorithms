package net.mtgsaber.lib.algorithms.graphs;

import net.mtgsaber.lib.algorithms.Pair;

/**
 * TODO: Implement
 */
public class GraphAdjacencyList<E, V> implements Graph<E, V> {
    @Override
    public Edge<E, V>[] getEdges() {
        return new Edge[0];
    }

    @Override
    public V[] getVertices() {
        return null;
    }

    @Override
    public Edge<E, V>[] getIncidentEdges(V vertex) {
        return new Edge[0];
    }

    @Override
    public V[] getAdjacentVertices(V vertex) {
        return null;
    }
}
