package exercise1;

public class InorderNext {
    
    // Simple Node class to represent the tree structure
    static class Node {
        int element;
        Node left, right, parent;

        Node(int element, Node parent) {
            this.element = element;
            this.parent = parent;
        }
    }

    /**
     * Logic: If right child exists, go right once then all the way left.
     * If no right child, climb up until we are the left child of a parent.
     */
    public static Node inorderNext(Node currentNode) {
        if (currentNode == null) return null;

        // Case 1: If there is a right subtree, the successor is the leftmost node there
        if (currentNode.right != null) {
            Node walkDown = currentNode.right;
            while (walkDown.left != null) {
                walkDown = walkDown.left;
            }
            return walkDown;
        } 
        
        // Case 2: No right subtree. Go up to the parent.
        Node ancestor = currentNode.parent;
        Node child = currentNode;
        
        // Keep climbing as long as we are the "right" child of the ancestor
        while (ancestor != null && child == ancestor.right) {
            child = ancestor;
            ancestor = ancestor.parent;
        }
        
        return ancestor; // This will be null if currentNode was the last node
    }

    public static void main(String[] args) {
        // Simple test setup: A small tree
        Node root = new Node(10, null);
        root.left = new Node(5, root);
        root.right = new Node(15, root);
        root.left.right = new Node(7, root.left);

        Node startNode = root.left.right; // Node 7
        Node next = inorderNext(startNode);
        
        if (next != null) {
            System.out.println("The node after " + startNode.element + " is: " + next.element);
        } else {
            System.out.println("No next node found (last node).");
        }
    }
}