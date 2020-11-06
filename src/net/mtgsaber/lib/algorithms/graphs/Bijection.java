package net.mtgsaber.lib.algorithms.graphs;

import net.mtgsaber.lib.algorithms.Pair;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Bijection<O, A extends O, B extends O> {
    private final Map<A, B> A_TO_B;
    private final Map<B, A> B_TO_A;

    public Bijection() {
        A_TO_B = new HashMap<>();
        B_TO_A = new HashMap<>();
    }

    public Pair<A, B> put(A a, B b) {
        return new Pair<>(B_TO_A.put(b, a), A_TO_B.put(a, b));
    }

    public B removeA(A a) {
        B b = A_TO_B.remove(a);
        B_TO_A.remove(b);
        return b;
    }

    public A removeB(B b) {
        A a = B_TO_A.remove(b);
        A_TO_B.remove(a);
        return a;
    }

    public B getB(A a) { return A_TO_B.get(a); }

    public A getA(B b) { return B_TO_A.get(b); }

    public boolean contains(O o) { return A_TO_B.containsKey(o) || A_TO_B.containsValue(o); }

    public int size() { return A_TO_B.size(); }

    public Set<Pair<A, B>> getEntries() {
        Set<Pair<A, B>> set = new HashSet<>();
        for (Map.Entry<A, B> entry : A_TO_B.entrySet())
            set.add(new Pair<>(entry.getKey(), entry.getValue()));
        return set;
    }

    public boolean isEmpty() { return A_TO_B.isEmpty(); }
}
