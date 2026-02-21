import java.io.File;

/**
 * Name: Goodness Ohazurike 
 * ID: 301468840
 * Lab 3 - Exercise 3: Recursive File Search
 */
public class FileFinder {
    public static void find(File path, String filename) {
        if (!path.exists()) return;

        if (path.isDirectory()) {
            File[] entries = path.listFiles();
            if (entries != null) {
                for (File entry : entries) {
                    find(entry, filename); // Recursive step
                }
            }
        } else {
            if (path.getName().equalsIgnoreCase(filename)) {
                System.out.println("Found at: " + path.getAbsolutePath());
            }
        }
    }

    public static void main(String[] args) {
        // This path changes to a real folder on your computer for the demo
        File startDir = new File("C:/Users/Goodness/Desktop"); 
        find(startDir, "findme.txt");
    }
}