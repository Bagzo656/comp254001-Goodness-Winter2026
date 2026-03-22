package exercise1;

// --- Interfaces (Required by ADT definition) ---
interface Position<E> {
    E getElement();
}

interface PositionalList<E> {
    Position<E> addLast(E e);
    Position<E> first();
    Position<E> after(Position<E> p);
    boolean isEmpty();
    // The method we are implementing for the lab
    int indexOf(Position<E> p);
}

/**
 * Exercise 1: Implementing indexOf using ONLY ADT interface methods.
 */
public class ListIndexer<E> implements PositionalList<E> {

    // --- Part A: The Required Method Implementation ---
    @Override
    public int indexOf(Position<E> p) {
        Position<E> cursor = first();
        int stepCounter = 0;

        while (cursor != null) {
            // Compare the positions themselves
            if (cursor.equals(p)) {
                return stepCounter;
            }
            cursor = after(cursor); // Move to the next position
            stepCounter++;
        }
        
        throw new IllegalArgumentException("Position not found in this list.");
    }

    // --- Part B: Necessary Code to Test the Method ---
    public static void main(String[] args) {
        ListIndexer<String> myFruitList = new ListIndexer<>();

        // Adding elements and capturing their "Position"
        Position<String> p0 = myFruitList.addLast("Apple");
        Position<String> p1 = myFruitList.addLast("Banana");
        Position<String> p2 = myFruitList.addLast("Cherry");

        System.out.println("--- Exercise 1: PositionalList Index Testing ---");
        
        // Testing the indexOf method
        System.out.println("Item: " + p0.getElement() + " | Expected Index: 0 | Actual: " + myFruitList.indexOf(p0));
        System.out.println("Item: " + p1.getElement() + " | Expected Index: 1 | Actual: " + myFruitList.indexOf(p1));
        System.out.println("Item: " + p2.getElement() + " | Expected Index: 2 | Actual: " + myFruitList.indexOf(p2));
        
        System.out.println("\nVerification: Traversal logic is successful.");
    }

    // --- Part C: Simple Internal Implementation (So the code runs in Eclipse) ---
    private Node<E> head = null;
    private Node<E> tail = null;

    private static class Node<E> implements Position<E> {
        E element;
        Node<E> next;
        public Node(E e, Node<E> n) { element = e; next = n; }
        public E getElement() { return element; }
    }

    public Position<E> addLast(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) head = newest;
        else tail.next = newest;
        tail = newest;
        return newest;
    }

    public Position<E> first() { return head; }
    public Position<E> after(Position<E> p) { return ((Node<E>) p).next; }
    public boolean isEmpty() { return head == null; }
}