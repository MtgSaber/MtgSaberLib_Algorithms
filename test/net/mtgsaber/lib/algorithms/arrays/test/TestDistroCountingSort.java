package net.mtgsaber.lib.algorithms.arrays.test;

import net.mtgsaber.lib.algorithms.arrays.Arrays;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Random;
import java.util.Scanner;

public class TestDistroCountingSort {
    public static void main(String[] args) {
        System.out.println("=== Begin Test 1 ===");
        if (test1()) {
            System.out.println("=== Test 1 Passed ===");
        } else {
            System.out.println("=== Test 1 Failed ===");
        }

        System.out.println("=== Begin Test 2 ===");
        if (test2())
            System.out.println("=== Test 2 Passed ===");
        else
            System.out.println("=== Test 2 Failed ===");

        /*
        System.out.println("Sorted array: [");
        for (int i = 0; i < arrSize-1; i++)
            System.out.print("" + testArr[i] + ", ");
        System.out.println("" + testArr[arrSize - 1] + "]");
        */
    }

    private static boolean test1() {
        final Random rng = new Random();
        final Scanner sc = new Scanner(System.in);

        System.out.print("Enter size of testing array: ");
        final int arrSize = sc.nextInt();
        System.out.print("Enter min value: ");
        final int min = sc.nextInt();
        System.out.print("Enter max value: ");
        final int bound = sc.nextInt() - min;


        final Integer[] testArr = new Integer[arrSize];

        for (int i = 0; i < arrSize; i++)
            testArr[i] = rng.nextInt(bound) - min;

        /*
        System.out.print("Testing array: [");
        for (int i = 0; i < arrSize-1; i++)
            System.out.print("" + testArr[i] + ", ");
        System.out.println("" + testArr[arrSize - 1] + "]");
         */

        long startTime, endTime;

        Comparator<Integer> comparator = (o1, o2) -> o1 - o2;
        startTime = System.currentTimeMillis();
        Arrays.distCountingSort(testArr, comparator, true);
        endTime = System.currentTimeMillis();
        System.out.println("Finished in " + (endTime - startTime) + "ms.");

        return isSorted(testArr, comparator, true);

    }

    private static <T> boolean isSorted(T[] source, Comparator<T> comparator, boolean nonDecreasing) {
        boolean sorted = true;
        for (int i = 0; i < source.length - 1; i++) {
            if (nonDecreasing && comparator.compare(source[i], source[i + 1]) > 0)
                sorted = false;
            else if (!nonDecreasing && comparator.compare(source[i], source[i + 1]) < 0)
                sorted = false;
        }

        return sorted;
    }


    private static boolean test2() {
        Integer[] testArr = new Integer[] {
                1, 2, 3, 4, 5, 6, 32, 33, 34, 35, 36, 45, 46, 47, 48, 999
        };
        Comparator<Integer> comparator = (o1, o2) -> o1 - o2;
        Arrays.distCountingSort(testArr, comparator, false);
        return isSorted(testArr, comparator, false);
    }
}
