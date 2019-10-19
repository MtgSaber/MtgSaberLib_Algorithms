package net.mtgsaber.lib.algorithms.arrays;

import java.util.Comparator;

public class Arrays {

    /**
     * Sorts an array by using each element's relative offset from the minimum element and its frequency.
     *
     * This algorithm works on types that can be enumerated and mapped to integers,
     * such that the exact quantity of values that can exist between a and b can be determined
     * by a single comparison of a to b. Integers and integral types are a prime example.
     *
     * Time complexity is in Theta(n), where n is source.length.
     *
     * @param source the array to be sorted.
     * @param comparator comparator for T. MUST PROVIDE OFFSET VALUE.
     * @param nonDecreasing whether this should sort in non-decreasing or non-increasing.
     * @param <T> the type of the array.
     */
    public static <T> void distCountingSort(T[] source, Comparator<T> comparator, boolean nonDecreasing) {
        // arrays of less than two elements are always sorted.
        if (source.length < 2)
            return;

        T min = source[0], max = source[0];

        // locate minimum
        for (T t : source) {
            if (comparator.compare(t, min) < 0)
                min = t;
            else if (comparator.compare(max, t) < 0)
                max = t;
        }

        // create frequency & distribution array
        int distance = comparator.compare(max, min);
        int[] distros = new int[distance + 1];

        // count frequencies
        for (T t : source)
            distros[comparator.compare(t, min)]++;

        // find target locations of rightmost occurrences of each offset value based on their frequency.
        if (nonDecreasing)
            for (int i = 1; i < distros.length; i++)
                distros[i] += distros[i - 1];
        else {
            distros[0] = distros.length;
            for (int i = 1; i < distros.length; i++)
                distros[i] = distros[i - 1] - distros[i];
        }

        // copy into temporary array.
        T[] temp = (T[]) new Object[source.length];
        for (int i = 0; i < source.length; i++)
            temp[i] = source[i];

        // place elements into the next right-most position in their class's partition.
        for (int i = source.length - 1; i != -1; i--)
            source[ --distros[ comparator.compare(temp[i], min) ] ] = temp[i];
    }
}
