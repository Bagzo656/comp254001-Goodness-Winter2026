package experiments;

import java.util.Random;

/**
 * COMP-254: Lab #2 - Exercise 2
 * Student: Goodness Ohazurike
 * 
 * Experimental analysis comparing prefixAverage1 (O(n^2)) and 
 * prefixAverage2 (O(n)). Structure modeled after StringExperiment.java.
 */
public class PrefixAverageExperiment {

    public static void main(String[] args) {
        int n = 10000;      // Starting value for input size
        int trials = 6;     // Number of times we double n
        int startN = n;     // Save original start value to reset for second algorithm

        System.out.println("Experimental Analysis by Goodness Ohazurike");
        System.out.println("==============================================");

        // --- Testing Algorithm 1: prefixAverage1 (O(n^2)) ---
        System.out.println("Testing prefixAverage1 (Quadratic)...");
        for (int t = 0; t < trials; t++) {
            double[] dataSet = createRandomData(n);
            
            long startTime = System.currentTimeMillis();
            calcPrefixAverage1(dataSet);
            long endTime = System.currentTimeMillis();
            
            long elapsed = endTime - startTime;
            System.out.println(String.format("n: %9d took %12d milliseconds", n, elapsed));
            n *= 2; // Double the problem size
        }

        // --- Testing Algorithm 2: prefixAverage2 (O(n)) ---
        System.out.println("\nTesting prefixAverage2 (Linear)...");
        n = startN; // Restore n to the starting value
        for (int t = 0; t < trials; t++) {
            double[] dataSet = createRandomData(n);
            
            long startTime = System.currentTimeMillis();
            calcPrefixAverage2(dataSet);
            long endTime = System.currentTimeMillis();
            
            long elapsed = endTime - startTime;
            System.out.println(String.format("n: %9d took %12d milliseconds", n, elapsed));
            n *= 2; // Double the problem size
        }
        
        System.out.println("\nNote: Use the 'n' and 'milliseconds' values to create the log-log chart.");
    }

    /**
     * Implementation of the O(n^2) prefix average algorithm.
     * Renamed 'x' to 'sourceArray' and 'a' to 'averages' for uniqueness.
     */
    public static double[] calcPrefixAverage1(double[] sourceArray) {
        int length = sourceArray.length;
        double[] averages = new double[length];
        for (int j = 0; j < length; j++) {
            double runningTotal = 0;
            for (int i = 0; i <= j; i++) {
                runningTotal += sourceArray[i];
            }
            averages[j] = runningTotal / (j + 1);
        }
        return averages;
    }

    /**
     * Implementation of the O(n) prefix average algorithm.
     */
    public static double[] calcPrefixAverage2(double[] sourceArray) {
        int length = sourceArray.length;
        double[] averages = new double[length];
        double runningTotal = 0;
        for (int j = 0; j < length; j++) {
            runningTotal += sourceArray[j];
            averages[j] = runningTotal / (j + 1);
        }
        return averages;
    }

    /**
     * Generates a random array of doubles of a given size.
     */
    private static double[] createRandomData(int size) {
        Random rng = new Random();
        double[] arr = new double[size];
        for (int i = 0; i < size; i++) {
            arr[i] = rng.nextDouble() * 100.0;
        }
        return arr;
    }
}