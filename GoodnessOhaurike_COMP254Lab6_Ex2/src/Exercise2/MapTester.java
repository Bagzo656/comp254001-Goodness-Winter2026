package Exercise2;

//MapTester.java
public class MapTester {
 public static void main(String[] args) {
     SortedTableMap<String, Integer> studentGrades = new SortedTableMap<>();
     
     // Scenario: A student is in the system but has no grade yet (null)
     studentGrades.put("Goodness", null);
     
     System.out.println("Testing SortedTableMap Ambiguity:");
     
     // Using get() returns null, which is confusing
     System.out.println("Value for 'Goodness' via get(): " + studentGrades.get("Goodness"));
     System.out.println("Value for 'Unknown' via get(): " + studentGrades.get("Unknown"));
     
     // containsKey resolves this
     System.out.println("Does 'Goodness' exist? " + studentGrades.containsKey("Goodness")); // True
     System.out.println("Does 'Unknown' exist? " + studentGrades.containsKey("Unknown"));   // False
     
     /* 
      * Reflection: By using findIndex, we perform an O(log n) search.
      * If the key exists, even if its value is null, containsKey will 
      * return true, solving the professor's requirement.
      */
 }
}