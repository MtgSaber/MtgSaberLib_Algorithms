package net.mtgsaber.lib.algorithms.arrays;

import java.util.ArrayList;
import java.util.Comparator;

public class Arrays {
    /**
     * Sorts an array by using each element's relative offset from the minimum element and its frequency.
     *
     * This algorithm works on types that can be enumerated and mapped to integers,
     * such that the exact quantity of values that can exist between a and b can be determined
     * by a single comparison of a to b. I.E. no floating-point values.
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
            if (comparator.compare(t, min) < 0) // if a < b, neg. if a > b, pos. if a == b, 0.
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
        else
            for (int i = distros.length - 2; i > -1; i--)
                distros[i] += distros[i + 1];


        // copy into temporary array.
        T[] temp = (T[]) new Object[source.length];
        for (int i = 0; i < source.length; i++)
            temp[i] = source[i];

        // place elements into the next right-most position in their class's partition.
        for (int i = source.length - 1; i > -1; i--)
            source[ --distros[ comparator.compare(temp[i], min) ] ] = temp[i];
    }

    /**
     * Used for radixCountingSort.
     * @param <T> Type that contains a sequence of <code>D</code>.
     * @param <D> A "digit" of <code>T</code>.
     */
    public interface DigitProvider<T, D> { D extract(T source, int position); }

    /**
     * Sorts an array by applying distCountingSort to individual digits of the elements.
     *
     * @param source
     * @param digitComparator
     * @param digitProvider
     * @param nonDecreasing
     * @param <T>
     * @param <D>
     */
    public static <T, D> void radixCountingSort(
            T[] source, Comparator<D> digitComparator, DigitProvider<T, D> digitProvider, boolean nonDecreasing) {
        // TODO: implement
    }

    /**
     * exhaustive approach to finding the common elements of two arrays.
     * Duplicates are treated as "unique" elements, such that each instance of a duplicate
     * value is counted separately.
     * Time Complexity, m = smaller array length, n = larger array length:
     *  - Theta(mn)
     * @param l1 is to be an array in non-decreasing order
     * @param l2 is to be an array in non-decreasing order
     * @param comparator to compare l1 against l2
     * @param <T> the type of the arrays
     * @return all common elements. ex.: ({1, 2, 3, 3, 3}, {0, 2, 3, 3, 5}) -> {2, 3, 3}
     */
    public static <T> T[] intersection(T[] l1, T[] l2, Comparator<T> comparator) {
        // take care of all trivial cases
        if (l1 == null || l2 == null) // null -> null
            return null;
        if (l1.length == 0 || l2.length == 0) // empty -> empty
            return java.util.Arrays.copyOf(l1, 0);
        if (l1.length == 1) { // trivial
            if (java.util.Arrays.binarySearch(l2, l1[0]) > -1)
                return java.util.Arrays.copyOf(l1, 1);
            else
                return null;
        }
        if (l2.length == 1) { // trivial
            if (java.util.Arrays.binarySearch(l1, l2[0]) > -1)
                return java.util.Arrays.copyOf(l2, 1);
            else
                return null;
        }

        /* *** GENERAL CASE *** */
        // decide which array is bigger
        int count = 0;
        T[] a, b;
        if (l1.length >= l2.length) {
            a = l1;
            b = l2;
        } else {
            a = l2;
            b = l1;
        }

        // flag the positions of all members of the intersection
        boolean[] flags = new boolean[b.length];
        for (T o : a)
            for (int j=0; j < b.length; j++) {
                if (comparator.compare(o, b[j]) < 0) // leave early
                    break;
                if (comparator.compare(o, b[j]) == 0 && !flags[j]) {
                    flags[j] = true;
                    count++;
                    break;
                }
            }

        // use flags to copy over members of the intersection
        ArrayList<T> retTemp = new ArrayList<>(count);
        for (int i=0; i < flags.length; i++)
            if (flags[i])
                retTemp.add(b[i]);

        // convert back to array
        return retTemp.toArray(java.util.Arrays.copyOf(b, 0));
    }
}
