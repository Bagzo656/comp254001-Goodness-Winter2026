package exercise2;

import java.util.Stack;

public class StackTransfer {

    /**
     * Transfers elements from source to destination.
     * The top of source becomes the bottom of destination.
     */
    public static <E> void transfer(Stack<E> sourceStack, Stack<E> destinationStack) {
        System.out.println("Starting transfer of " + sourceStack.size() + " elements...");
        
        // We continue popping until the source is depleted
        while (!sourceStack.isEmpty()) {
            E movingItem = sourceStack.pop();
            destinationStack.push(movingItem);
        }
        
        System.out.println("Transfer complete.");
    }

    public static void main(String[] args) {
        Stack<Integer> S = new Stack<>();
        Stack<Integer> T = new Stack<>();

        // Setup: [Bottom] 1, 2, 3, 4, 5 [Top]
        for (int i = 1; i <= 5; i++) S.push(i);

        System.out.println("Original S (Bottom to Top): " + S);
        transfer(S, T);
        System.out.println("New T (Bottom to Top): " + T);
        System.out.println("S is now empty: " + S.isEmpty());
    }
}
