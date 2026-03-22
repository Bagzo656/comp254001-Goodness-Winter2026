package exercise3;

public class LinkedQueue<E> {
    
    // Internal Node class
    private static class Node<E> {
        E element;
        Node<E> next;
        public Node(E e, Node<E> n) { element = e; next = n; }
    }

    private Node<E> head = null;
    private Node<E> tail = null;
    private int size = 0;

    public void enqueue(E e) {
        Node<E> newest = new Node<>(e, null);
        if (isEmpty()) head = newest;
        else tail.next = newest;
        tail = newest;
        size++;
    }

    public boolean isEmpty() { return size == 0; }

    /**
     * Concatenates Q2 into this queue in O(1) time.
     */
    public void concatenate(LinkedQueue<E> otherQueue) {
        if (otherQueue.isEmpty()) return;

        // The 'Magic' of O(1): Link the tail of this to the head of other
        if (this.isEmpty()) {
            this.head = otherQueue.head;
        } else {
            this.tail.next = otherQueue.head; 
        }

        // Update the tail pointer to the other queue's end
        this.tail = otherQueue.tail;
        this.size += otherQueue.size;

        // Empty the other queue references to fulfill the requirement
        otherQueue.head = null;
        otherQueue.tail = null;
        otherQueue.size = 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder("(");
        Node<E> walk = head;
        while (walk != null) {
            sb.append(walk.element);
            if (walk.next != null) sb.append(", ");
            walk = walk.next;
        }
        sb.append(")");
        return sb.toString();
    }

    public static void main(String[] args) {
        LinkedQueue<String> Q1 = new LinkedQueue<>();
        LinkedQueue<String> Q2 = new LinkedQueue<>();

        Q1.enqueue("A"); Q1.enqueue("B");
        Q2.enqueue("C"); Q2.enqueue("D");

        System.out.println("Q1 before: " + Q1);
        System.out.println("Q2 before: " + Q2);

        Q1.concatenate(Q2);

        System.out.println("Q1 after concatenation: " + Q1);
        System.out.println("Q2 after (should be empty): " + Q2);
    }
}