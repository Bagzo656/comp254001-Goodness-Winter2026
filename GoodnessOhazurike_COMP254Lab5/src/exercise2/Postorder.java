package exercise2;

public class Postorder {

    static class Node {
        String data;
        Node left, right;

        Node(String data) { this.data = data; }
    }

    /**
     * Recursive method to calculate height and print data.
     * Returns the height of the current node's subtree.
     */
    public static int printAndComputeHeight(Node p) {
        // Base case: If the node is null, it doesn't contribute to height
        if (p == null) return -1;

        // Recursively find the height of children (Postorder: Left, Right, then Root)
        int leftSubtreeHeight = printAndComputeHeight(p.left);
        int rightSubtreeHeight = printAndComputeHeight(p.right);

        // Height is 1 + the height of the tallest child
        int currentHeight = 1 + Math.max(leftSubtreeHeight, rightSubtreeHeight);

        // Print the element and its height during the visit (Postorder step)
        System.out.println("Element: " + p.data + " | Subtree Height: " + currentHeight);

        return currentHeight;
    }

    public static void main(String[] args) {
        // Building a small test tree
        Node root = new Node("Root");
        root.left = new Node("LeftChild");
        root.right = new Node("RightChild");
        root.left.left = new Node("GrandChild");

        System.out.println("Calculating heights (Postorder):");
        printAndComputeHeight(root);
    }
}