package exercise2;

import java.util.LinkedList;
import java.util.Queue;

/**
 * Lab 7 - Exercise 2
 * Name: Goodness Ohazurike
 * Student ID: 301468840
 */
public class QueueMergeSort {

    public static <E extends Comparable<E>> void mergeSort(Queue<E> S) {
        int n = S.size();
        if (n < 2) return;

        // Step 1: Place each item in its own queue, and these into a master queue
        Queue<Queue<E>> masterQueue = new LinkedList<>();
        while (!S.isEmpty()) {
            Queue<E> singleton = new LinkedList<>();
            singleton.add(S.poll());
            masterQueue.add(singleton);
        }

        // Step 2: Repeatedly merge pairs of queues until only one remains
        while (masterQueue.size() > 1) {
            Queue<E> q1 = masterQueue.poll();
            Queue<E> q2 = masterQueue.poll();
            masterQueue.add(merge(q1, q2));
        }

        // Step 3: Move sorted elements back to original queue
        Queue<E> result = masterQueue.poll();
        while (!result.isEmpty()) {
            S.add(result.poll());
        }
    }

    // Helper method to merge two sorted queues into one sorted queue
    private static <E extends Comparable<E>> Queue<E> merge(Queue<E> q1, Queue<E> q2) {
        Queue<E> merged = new LinkedList<>();
        while (!q1.isEmpty() && !q2.isEmpty()) {
            if (q1.peek().compareTo(q2.peek()) < 0)
                merged.add(q1.poll());
            else
                merged.add(q2.poll());
        }
        while (!q1.isEmpty()) merged.add(q1.poll());
        while (!q2.isEmpty()) merged.add(q2.poll());
        return merged;
    }

    public static void main(String[] args) {
        System.out.println("Lab 7 - Exercise 2");
        System.out.println("Name: Goodness Ohazurike | ID: 301468840");
        
        Queue<Integer> data = new LinkedList<>();
        int[] unsorted = {34, 10, 56, 1, 99, 12, 4, 25};
        
        System.out.print("Unsorted Data: ");
        for (int i : unsorted) {
            System.out.print(i + " ");
            data.add(i);
        }
        
        mergeSort(data);
        
        System.out.print("\nSorted Data (Bottom-Up Queue Merge): ");
        System.out.println(data);
    }
}