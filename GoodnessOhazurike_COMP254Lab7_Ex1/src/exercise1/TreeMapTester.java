package exercise1;

public class TreeMapTester {
    public static void main(String[] args) {
        TreeMap<Integer, String> map = new TreeMap<>();
        map.displayInfo();
        
        // Testing the 'get' which triggers our iterative treeSearch
        System.out.println("Searching for key 10: " + map.get(10)); 
    }
}