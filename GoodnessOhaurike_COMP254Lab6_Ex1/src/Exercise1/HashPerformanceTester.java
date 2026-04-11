package Exercise1;

//HashPerformanceTester.java
import java.util.Random;

public class HashPerformanceTester {
 public static void main(String[] args) {
     int testSize = 10000;
     double[] factors = {0.25, 0.5, 0.75, 0.9};

     System.out.println("--- Efficiency Experiment (ChainHashMap) ---");
     for (double f : factors) {
         // Using a unique name for the map instance
         ChainHashMap<Integer, Integer> experimentalMap = new ChainHashMap<>(17, f);
         Random rng = new Random();

         long startTime = System.nanoTime();
         for (int i = 0; i < testSize; i++) {
             experimentalMap.put(rng.nextInt(100000), i);
         }
         long endTime = System.nanoTime();
         
         double durationMs = (endTime - startTime) / 1_000_000.0;
         System.out.printf("Load Factor: %.2f | Time: %.3f ms\n", f, durationMs);
     }
     
     // I struggled with the ProbeHashMap initially because it hits 
     // infinite loops if the load factor is set to 1.0, so I capped the test at 0.9.
 }
}