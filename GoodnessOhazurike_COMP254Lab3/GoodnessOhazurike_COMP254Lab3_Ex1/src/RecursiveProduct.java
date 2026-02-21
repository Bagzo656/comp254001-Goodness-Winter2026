import java.util.Scanner;

/**
 * Name: Goodness Ohazurike 
 * ID: 301468840
 */
public class RecursiveProduct {
    public static int multiply(int m, int n) {
        if (m == 0 || n == 0) return 0; // Edge case: zero
        if (m == 1) return n;           // Base case: stop here
        
        return n + multiply(m - 1, n);  // Recursive step
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.println("--- Recursive Multiplication ---");
        System.out.print("Enter first positive integer (m): ");
        int m = scanner.nextInt();
        System.out.print("Enter second positive integer (n): ");
        int n = scanner.nextInt();
        
        int result = multiply(m, n);
        System.out.println("The product of " + m + " and " + n + " is: " + result);
        
        scanner.close();
    }
}