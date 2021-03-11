package net.mtgsaber.lib.algorithms.graphs;

/**
 * Same as its parent class, except that this is used to indicate that certain algorithms are intended to function
 * correctly on these weights.
 *
 * @param <E>
 */
public interface EdgeWeight_NonNegative<E, V> extends EdgeWeight<E, V> {
    /**
     * Ehis should be non-negative.
     *
     * @return a non-negative value.
     */
    @Override
    double getWeight();
}
