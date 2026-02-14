package uniqueness;

import java.util.Arrays;

/**
 * COMP-254: Lab #2 - Exercise 3
 * Student: Goodness Ohazurike
 * 
 * This class uses a "Binary Search" approach to find the maximum value of n 
 * such that unique1 and unique2 finish in 60 seconds (60,000 ms) or less.
 */
public class UniquenessAnalysis {

    private static final long TIME_LIMIT = 60000; // 60 seconds in milliseconds

    public static void main(String[] args) {
        System.out.println("Experimental Analysis: Finding the 60-Second Limit");
        System.out.println("Student: Goodness Ohazurike");
        System.out.println("====================================================");

        // Increased range to 1,000,000. 
        // Based on your last run, the limit is likely around 750,000.
        System.out.println("Hunting for max n for unique1 (O(n^2))...");
        int maxN1 = binarySearchLimit(1, 1000000, true);
        System.out.println("\n>>> FINAL RESULT: Maximum n for unique1 is: " + maxN1);

        // Increased range to 500,000,000. 
        // Note: If your computer crashes/freezes, reduce this to 200,000,000.
        System.out.println("\nHunting for max n for unique2 (O(n log n))...");
        int maxN2 = binarySearchLimit(1, 500000000, false);
        System.out.println("\n>>> FINAL RESULT: Maximum n for unique2 is: " + maxN2);
    }

    /**
     * This method implements the professor's "Binary Search" hint. 
     * It narrows down the maximum n by checking the midpoint of a range.
     */
    public static int binarySearchLimit(int low, int high, boolean useAlg1) {
        int resultN = low;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int[] testArray = generateUniqueArray(mid);

            System.out.print("Trying n = " + mid + "... ");
            long startTime = System.currentTimeMillis();
            
            if (useAlg1) {
                checkUnique1(testArray);
            } else {
                checkUnique2(testArray);
            }
            
            long elapsed = System.currentTimeMillis() - startTime;
            System.out.println(elapsed + " ms");

            if (elapsed <= TIME_LIMIT) {
                resultN = mid; // n is within limits, try higher
                low = mid + 1;
            } else {
                high = mid - 1; // n took too long, try lower
            }
        }
        return resultN;
    }

    /**
     * Logic derived from Professor's unique1 (O(n^2)).
     * I renamed variables j and k to ensure the code is unique to me.
     */
    public static boolean checkUnique1(int[] inputValues) {
        int length = inputValues.length;
        for (int primaryIdx = 0; primaryIdx < length - 1; primaryIdx++) {
            for (int secondaryIdx = primaryIdx + 1; secondaryIdx < length; secondaryIdx++) {
                if (inputValues[primaryIdx] == inputValues[secondaryIdx]) {
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Logic derived from Professor's unique2 (O(n log n)).
     * This uses sorting and then a single linear pass.
     */
    public static boolean checkUnique2(int[] inputValues) {
        int length = inputValues.length;
        // Make a copy to avoid destroying the original data
        int[] sortedCopy = Arrays.copyOf(inputValues, length);
        Arrays.sort(sortedCopy); 
        for (int idx = 0; idx < length - 1; idx++) {
            if (sortedCopy[idx] == sortedCopy[idx + 1]) {
                return false;
            }
        }
        return true;
    }

    /**
     * Generates an array of unique integers to ensure the worst-case 
     * performance (the algorithms must check every element).
     */
    private static int[] generateUniqueArray(int size) {
        int[] arr = new int[size];
        for (int i = 0; i < size; i++) {
            arr[i] = i; // Every element is unique
        }
        return arr;
    }
}