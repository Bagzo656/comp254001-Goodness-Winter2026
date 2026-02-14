package exercises;

/**
 * COMP-254: Lab #2 - Algorithm Analysis
 * Student: Goodness Ohazurike
 * 
 * This class contains five example methods demonstrating different 
 * Big-Oh run-time complexities.
 */
public class Exercises {

    /**
     * a) Characterization: O(n)
     * Explanation: I've identified this as linear time. The method contains 
     * a single loop that iterates exactly 'n' times (once for every element). 
     * As the array grows, the time taken grows in a direct, straight-line 
     * relationship.
     */
    public static int example1(int[] inputData) {
        int dataSize = inputData.length;
        int runningSum = 0;
        for (int idx = 0; idx < dataSize; idx++) {
            runningSum += inputData[idx];
        }
        return runningSum;
    }

    /**
     * b) Characterization: O(n)
     * Explanation: Even though the loop increments by 2 (skipping half the 
     * indices), the running time is still O(n). In Big-Oh, we ignore 
     * constants like 1/2. Because the number of operations is still 
     * proportional to the input size, it remains linear.
     */
    public static int example2(int[] inputData) {
        int dataSize = inputData.length;
        int runningSum = 0;
        for (int idx = 0; idx < dataSize; idx += 2) {
            runningSum += inputData[idx];
        }
        return runningSum;
    }

    /**
     * c) Characterization: O(n^2)
     * Explanation: This is a quadratic growth pattern. I noticed that for 
     * every iteration of the outer loop, the inner loop runs 'j' times. 
     * This creates a triangular series of operations (1+2+3...+n), which 
     * mathematically simplifies to roughly (n^2)/2. We drop the constant 
     * to get O(n^2).
     */
    public static int example3(int[] inputData) {
        int dataSize = inputData.length;
        int runningSum = 0;
        for (int outerIdx = 0; outerIdx < dataSize; outerIdx++) {
            for (int innerIdx = 0; innerIdx <= outerIdx; innerIdx++) {
                runningSum += inputData[outerIdx];
            }
        }
        return runningSum;
    }

    /**
     * d) Characterization: O(n)
     * Explanation: Interestingly, this does similar work to example3 
     * (calculating prefix sums) but it is much more efficient. By 
     * maintaining a 'runningPrefix' variable, I only need one loop to 
     * visit each element once. This demonstrates how a better algorithm 
     * can reduce O(n^2) down to O(n).
     */
    public static int example4(int[] inputData) {
        int dataSize = inputData.length;
        int runningPrefix = 0;
        int runningSum = 0;
        for (int idx = 0; idx < dataSize; idx++) {
            runningPrefix += inputData[idx];
            runningSum += runningPrefix;
        }
        return runningSum;
    }

    /**
     * e) Characterization: O(n^3)
     * Explanation: This is a cubic characterization. I see three nested 
     * loops here. The i-loop runs n times, the j-loop runs n times, and 
     * the k-loop runs up to n times. Multiplying these together (n*n*n) 
     * gives O(n^3). I noticed that this code is extremely inefficient because 
     * it recalculates the sum from scratch inside a double loop.
     */
    public static int example5(int[] firstArray, int[] secondArray) {
        int dataSize = firstArray.length;
        int matchCount = 0;
        for (int outerIdx = 0; outerIdx < dataSize; outerIdx++) {
            int currentTotal = 0;
            for (int middleIdx = 0; middleIdx < dataSize; middleIdx++) {
                for (int innerIdx = 0; innerIdx <= middleIdx; innerIdx++) {
                    currentTotal += firstArray[innerIdx];
                }
            }
            if (secondArray[outerIdx] == currentTotal) {
                matchCount++;
            }
        }
        return matchCount;
    }
}