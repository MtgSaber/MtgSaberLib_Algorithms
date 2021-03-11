package net.mtgsaber.lib.algorithms.graphs;

import net.mtgsaber.lib.algorithms.Pair;

public interface Edge<E, V> {
    Pair<V, V> getIncidentVertices();
    E getValue();
}
