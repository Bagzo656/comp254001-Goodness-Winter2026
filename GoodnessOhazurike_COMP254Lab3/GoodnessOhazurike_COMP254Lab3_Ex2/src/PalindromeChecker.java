import java.util.Scanner;

/**
 * Name: Goodness Ohazurike 
 * ID: 301468840
 * Lab 3 - Exercise 2: Recursive Palindrome
 */
public class PalindromeChecker {
    public static boolean isPalindrome(String s) {
        // Clean the string: remove spaces and make lowercase for better testing
        s = s.toLowerCase().replaceAll("\\s+", "");
        
        // Base cases
        if (s.length() <= 1) return true;

        // Recursive logic: check first and last characters
        if (s.charAt(0) == s.charAt(s.length() - 1)) {
            return isPalindrome(s.substring(1, s.length() - 1));
        }
        return false;
    }

    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Enter a string to check for palindrome: ");
        String userString = input.nextLine();
        
        System.out.println("Is it a palindrome? " + isPalindrome(userString));
        input.close();
    }
}