package net.mtgsaber.lib.algorithms.graphs;

/**
 * Same as its parent class, except that this is used to indicate that certain algorithms are intended to function
 * correctly on these weights.
 *
 * @param <T>
 */
public interface EdgeWeight_NonNegative<T> extends EdgeWeight<T> {
    /**
     * This should be non-negative.
     *
     * @return a non-negative value.
     */
    @Override
    double getWeight();
}
