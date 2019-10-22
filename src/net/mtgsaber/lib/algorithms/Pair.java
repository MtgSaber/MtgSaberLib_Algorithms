package net.mtgsaber.lib.algorithms;

/**
 * Simple 2-element tuple.
 * @param <K>
 * @param <V>
 */
public class Pair<K, V> {
    public final K KEY;
    public final V VAL;

    public Pair(K KEY, V VAL) {
        this.KEY = KEY;
        this.VAL = VAL;
    }
}
