package net.mtgsaber.lib.algorithms.test;

import net.mtgsaber.lib.algorithms.arrays.Arrays;

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

        Comparator<Integer> comparator = (i1, i2) -> i1 - i2;
        startTime = System.currentTimeMillis();
        Arrays.distCountingSort(testArr, comparator, true);
        endTime = System.currentTimeMillis();
        System.out.println("Finished in " + (endTime - startTime) + "ms.");

        boolean sorted = true;
        for (int i = 0; i < arrSize - 1; i++)
            if (testArr[i + 1] < testArr[i])
                sorted = false;

        return sorted;
    }

    private static boolean test2() {
        return false;
    }
}
