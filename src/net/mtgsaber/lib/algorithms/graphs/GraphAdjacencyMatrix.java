package net.mtgsaber.lib.algorithms.graphs;

import net.mtgsaber.lib.algorithms.Pair;

import java.util.*;

/**
 * TODO: NYI
 */
public class GraphAdjacencyMatrix <E, V> implements Graph<E, V> {
    private final ArrayList<V> VERTICES;
    private final Map<V, Integer> VERTEX_TO_INDEX;
    private final ArrayList<ArrayList<Edge<E, V>>> MATRIX;
    private final Set<Edge<E, V>> EDGES;

    /**
     * TODO: document
     * @param vertices
     * @param edges
     */
    public GraphAdjacencyMatrix(Set<V> vertices, Set<Edge<E, V>> edges) {
        VERTICES = new ArrayList<>(vertices.size());
        VERTEX_TO_INDEX = new HashMap<>(vertices.size());
        MATRIX = new ArrayList<>(vertices.size());
        EDGES = new HashSet<>(edges);

        int i = 0;
        for (V vertex : vertices) {
            VERTICES.add(vertex);
            VERTEX_TO_INDEX.put(vertex, i);
            MATRIX.add(new ArrayList<>(vertices.size()));

            i++;
        }

        V source, target;
        for (Edge<E, V> edge : edges) {
            source = edge.getIncidentVertices().KEY;
            target = edge.getIncidentVertices().VAL;
            MATRIX.get(VERTEX_TO_INDEX.get(source)).set(VERTEX_TO_INDEX.get(target), edge);
        }
    }

    /* TODO: implement these
    public GraphAdjacencyMatrix(Graph<E, V> sourceToCopy) {

    }

    public GraphAdjacencyMatrix(int initialCapacity) {

    }

    public GraphAdjacencyMatrix() {

    }
    */

    /**
     * TODO: document
     * @return
     */
    @Override
    public Edge<E, V>[] getEdges() {
        return ((Edge<E, V>[]) EDGES.toArray());
    }

    /**
     * TODO: document
     * @return
     */
    @Override
    public V[] getVertices() {
        return ((V[]) VERTICES.toArray());
    }

    /**
     * TODO: NYI
     * @param vertex
     * @return
     */
    @Override
    public Edge<E, V>[] getIncidentEdges(V vertex) {
        List<Edge<E, V>> edges = new ArrayList<>(VERTICES.size());
        return null;
    }

    /**
     * TODO: NYI
     * @param vertex
     * @return
     */
    @Override
    public V[] getAdjacentVertices(V vertex) {
        return null;
    }

    /**
     * TODO: NYI
     * @param edge
     * @return
     */
    @Override
    public Pair<V, V> getVertices(Edge<E, V> edge) {
        return null;
    }
}
