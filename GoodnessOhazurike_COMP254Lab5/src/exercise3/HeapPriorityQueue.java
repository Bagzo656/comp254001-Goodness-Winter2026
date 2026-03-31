package exercise3;

import java.util.ArrayList;

public class HeapPriorityQueue<K extends Comparable<K>> {
    
    // Using an ArrayList to represent the heap internally
    private ArrayList<K> heap = new ArrayList<>();

    /**
     * Recursive implementation of upheap.
     * Swaps the element at 'childIdx' with its parent if it's smaller.
     */
    protected void recursiveUpheap(int childIdx) {
        // Stop if we hit the root
        if (childIdx <= 0) return;

        int parentIdx = (childIdx - 1) / 2;
        
        // If child is smaller than parent (Min-Heap logic)
        if (heap.get(childIdx).compareTo(heap.get(parentIdx)) < 0) {
            // Perform one swap
            swapElements(childIdx, parentIdx);
            
            // Recurse upwards from the new parent position
            recursiveUpheap(parentIdx);
        }
    }

    private void swapElements(int i, int j) {
        K temp = heap.get(i);
        heap.set(i, heap.get(j));
        heap.set(j, temp);
    }

    public void add(K key) {
        heap.add(key);
        recursiveUpheap(heap.size() - 1);
    }

    public void display() {
        System.out.println("Current Heap: " + heap);
    }

    public static void main(String[] args) {
    	HeapPriorityQueue<Integer> myPQ = new HeapPriorityQueue<>();
        myPQ.add(20);
        myPQ.add(15);
        myPQ.add(10);
        myPQ.add(5);
        
        myPQ.display();
    }
}
