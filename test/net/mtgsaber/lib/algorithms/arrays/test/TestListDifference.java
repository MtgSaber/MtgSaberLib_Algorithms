package net.mtgsaber.lib.algorithms.arrays.test;

import net.mtgsaber.lib.algorithms.Pair;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

public class TestListDifference {
    private static final Comparator<Integer> INTEGER_COMPARATOR = (o1, o2) -> o1 - o2;

    public static void main(String[] args) {
        System.out.println("==== STARTING CORRECTNESS TEST ====");
        correctnessTest();
        System.out.println("==== WARMING UP JIT FOR TIME EFFICIENCY TEST ====");
        for (int i = 0; i < 1000; i++) {
            timeEfficiencyTest();
        }
        System.out.println("==== STARTING TIME EFFICIENCY TEST, POST-WARM-UP");
        final TimeEfficiencyTestResults results = timeEfficiencyTest();
        for (int i = 0; i < results.TIMES.length; i++) {
            System.out.println("Time for two arrays of size " + results.SIZES[i] + ": " + results.TIMES[i] + "ns");
        }
        System.out.println("==== FINISHED TESTING ====");

    }

    private static void correctnessTest() {
        final Integer[] l1 = new Integer[] {
                1, 1, 5, 7, 10, 10, 10, 10, 10, 10, 560, 602, 4500, 4501, 4555,
        };
        final Integer[] l2 = new Integer[] {
                4, 5, 6, 7, 9, 10, 10, 10, 11, 560, 580, 4501, 5600
        };

        System.out.println("Array 1: " + Arrays.toString(l1));
        System.out.println("Array 2: " + Arrays.toString(l2));

        final Pair<Integer[], Integer[]> result = net.mtgsaber.lib.algorithms.arrays.Arrays.listDifference(
                l1, l2, Integer::compareTo
        );

        System.out.println("Unique values in l1: " + Arrays.toString(result.KEY));
        System.out.println("Unique values in l2: " + Arrays.toString(result.VAL));
    }

    private static class TimeEfficiencyTestResults {
        final int[] SIZES;
        final long[] TIMES;
        final Pair<Integer[], Integer[]>[] DATA, RESULTS;

        public TimeEfficiencyTestResults(int[] SIZES, long[] TIMES, Pair<Integer[], Integer[]>[] DATA, Pair<Integer[], Integer[]>[] RESULTS) {
            this.SIZES = SIZES;
            this.TIMES = TIMES;
            this.DATA = DATA;
            this.RESULTS = RESULTS;
        }
    }
    private static TimeEfficiencyTestResults timeEfficiencyTest() {
        final int[] sizes = new int[10];
        for (int i = 0; i < 10; i++)
            sizes[i] = ((int) Math.pow(2, i+8));

        final Random rng = new Random();
        final Pair<Integer[], Integer[]>[] data = ((Pair<Integer[], Integer[]>[]) new Pair[sizes.length]);
        for (int i = 0; i < sizes.length; i++) {
            data[i] = new Pair<>(new Integer[sizes[i]], new Integer[sizes[i]]);
            for (int j = 0; j < sizes[i]; j++) {
                data[i].KEY[j] = rng.nextInt(sizes[i] / 2);
                data[i].VAL[j] = rng.nextInt(sizes[i] / 2);
            }
            net.mtgsaber.lib.algorithms.arrays.Arrays.distCountingSort(
                    data[i].KEY, INTEGER_COMPARATOR, true
            );
            net.mtgsaber.lib.algorithms.arrays.Arrays.distCountingSort(
                    data[i].VAL, INTEGER_COMPARATOR, true
            );
        }


        final Pair<Integer[], Integer[]>[] results = ((Pair<Integer[], Integer[]>[]) new Pair[sizes.length]);
        final long[] times = new long[sizes.length];
        long startTime, endTime;
        for (int i = 0; i < sizes.length; i++) {
            startTime = System.nanoTime();
            results[i] = net.mtgsaber.lib.algorithms.arrays.Arrays.listDifference(
                data[i].KEY, data[i].VAL, INTEGER_COMPARATOR
            );
            endTime = System.nanoTime();
            times[i] = endTime - startTime;
        }

        return new TimeEfficiencyTestResults(sizes, times, data, results);
    }
}
