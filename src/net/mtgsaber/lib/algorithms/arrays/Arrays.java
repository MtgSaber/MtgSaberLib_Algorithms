package net.mtgsaber.lib.algorithms.arrays;

import net.mtgsaber.lib.algorithms.Pair;

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
     * Time complexity is in Theta(n + m), where
     * n is source.length,
     * m is the range of values.
     * This sorting algorithm is stable.
     *
     * @param source the array to be sorted.
     * @param comparator comparator for T. MUST PROVIDE OFFSET VALUE.
     * @param nonDecreasing whether this should sort in non-decreasing or non-increasing.
     * @param <T> the type of the array.
     */
    public static <T> void distCountingSort(T[] source, Comparator<T> comparator, boolean nonDecreasing) {
        // arrays of less than two elements are always sorted.
        if (source == null || source.length < 2)
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
     * Used for radixCountingSort().
     * @param <T> Type that contains a sequence of <code>D</code>.
     * @param <D> A "digit" of <code>T</code>.<br/>
     */
    public interface DigitProvider<T, D> {
        /**
         * If <code>displacement</code> is out of range, this method MUST return<br/>
         * a minimum value local to the dataset to be sorted.<br/>
         * e.g. given that the smallest digit value of the dataset for this sorting call is 1: (234, 3) -> 1
         *
         * Note: for lexicographically sorting Strings containing "normal" text, this can simply be:<br/>
         * <code>source.length > displacement ? source.charAt(displacement) : '\t'</code>
         * @param source Object of T from which a "digit" is to be extracted.
         * @param displacement Displacement from the least significant digit position. e.g. ("abcd", 0) -> 'd'
         * @return source[ source.length - displacement - 1], if source were to be viewed as an array of D.
         */
        D extract(T source, int displacement);
    }

    /**
     * Used for radixCountingSort().
     * @param <T> Type for which to find the cardinality, in terms of quantity of "digits".
     */
    public interface CardinalityProvider<T> {
        int measure(T o);
    }

    /**
     * A modified implementation of distCountingSort() to be used with radixCountingSort()
     */
    private static <T, D> void radixCountingSortHelper(
            T[] source, Comparator<D> comparator,
            DigitProvider<T, D> digitProvider, int displacement,
            boolean nonDecreasing) {
        // arrays of less than two elements are always sorted.
        if (source.length < 2)
            return;

        T min = source[0], max = source[0];

        // locate minimum
        for (T t : source) {
            if (comparator.compare(
                    digitProvider.extract(t, displacement),
                    digitProvider.extract(min, displacement)
            ) < 0)
                min = t;
            else if (comparator.compare(
                    digitProvider.extract(max, displacement),
                    digitProvider.extract(t, displacement)
            ) < 0)
                max = t;
        }

        // create frequency & distribution array
        int distance = comparator.compare(
                digitProvider.extract(max, displacement),
                digitProvider.extract(min, displacement)
        );
        int[] distros = new int[distance + 1];

        // count frequencies
        for (T t : source)
            distros[comparator.compare(
                    digitProvider.extract(t, displacement),
                    digitProvider.extract(min, displacement)
            )]++;

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
            source[
                    --distros[ comparator.compare(
                            digitProvider.extract(temp[i], displacement),
                            digitProvider.extract(min, displacement)
                    )]
                    ] = temp[i];
    }

    /**
     * Sorts elements according to the radix sorting algorithm.
     * This algorithm is applicable to any array of T such that T may be treated as a sequence of D,<br/>
     * so long as an arbitrary array of D meets the preconditions for distCountingSort().
     *
     * Time complexity is O(k * (b + n)), with
     * k being the largest cardinality of D among source,
     * b being the cardinality of the set of possible values for D,
     * n being the length of source.
     * This complexity is considered to be linear.
     *
     * This sorting algorithm is stable, but certainly not in-place.
     * It creates and destroys k arrays of maximum length b.
     * However, only one such array "exists" at any point in time, assuming that the garbage collector is perfect.
     *
     * @param source the array to sort.
     * @param digitComparator
     * @param digitProvider
     * @param ruler
     * @param nonDecreasing
     * @param <T>
     * @param <D>
     */
    public static <T, D> void radixCountingSort(
            T[] source, Comparator<D> digitComparator,
            DigitProvider<T, D> digitProvider, CardinalityProvider<T> ruler,
            boolean nonDecreasing
    ) {
        // calculate largest cardinality.
        int maxLen = 0, len;
        for (T t : source) {
            len = ruler.measure(t);
            if (len > maxLen)
                maxLen = len;
        }

        for (int displacement = 0; displacement < maxLen; displacement++) {
            radixCountingSortHelper(source, digitComparator, digitProvider, displacement, nonDecreasing);
        }
    }

    /**
     * Exhaustive approach to finding the common elements of two arrays.
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

    /**
     *
     * @param l1 List 1, SORTED
     * @param l2 List 2, SORTED
     * @param comparator Comparator for T. Used only for equality tests.
     * @param <T> Type to be compared. Can be anything.
     * @return List of values unique to list 1 and list 2, respectively.
     */
    public static <T> Pair<T[], T[]> listDifference(T[] l1, T[] l2, Comparator<T> comparator) {
        final ArrayList<T> u1 = new ArrayList<>(l1.length), u2 = new ArrayList<>(l2.length);
        int i = 0, j = 0, comp;

        while (true) {
            // if end of l1 is reached, add remaining elements of l2 to its unique value list.
            if (i == l1.length) {
                for (; j < l2.length; j++)
                    u2.add(l2[j]);
                break;
            }

            // if end of l2 is reached, add remaining elements of l1 to its unique value list.
            if (j == l2.length) {
                for (; i < l1.length; i++)
                    u1.add(l1[i]);
                break;
            }

            comp = comparator.compare(l1[i], l2[j]);
            if (comp == 0) {     // elements are the same, and therefore not unique.
                i++;
                j++;
            } else if (comp > 0) // the l1 element is larger, therefore the l2 element cannot possibly exist in l1
                u2.add(l2[j++]);
            else                 // the l1 element is larger, therefore the l2 element cannot possibly exist in l2
                u1.add(l1[i++]);
        }

        return new Pair<>(
                u1.toArray(java.util.Arrays.copyOf(l1, 0)),
                u2.toArray(java.util.Arrays.copyOf(l2, 0))
        );
    }
}
