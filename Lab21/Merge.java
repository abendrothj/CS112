import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 * Program to merge 3 lists in text files, names of files given in cmd args
 * @author abendrothj
 */
public class Merge {
    private static final ArrayList<Integer> combined = new ArrayList<>();
    public static void main(String[] args) {
        // Check for 3 files in arguments
        if(args.length >= 3) {
            ArrayList<Integer> fileContents1 = readToList(args[0]);
            ArrayList<Integer> fileContents2 = readToList(args[1]);
            ArrayList<Integer> fileContents3 = readToList(args[2]);
            combined.addAll(fileContents1);
            combined.addAll(fileContents2);
            combined.addAll(fileContents3);
            // Bubble sort combined array
            for(int i = 0; i < combined.size()-1; i++) {
                for(int j = i+1; j < combined.size(); j++) {
                    if (combined.get(i) > combined.get(j)) {
                        int val = combined.get(i);
                        combined.set(i, combined.get(j));
                        combined.set(j, val);
                    }
                }
            } // Print each element
            for(int e : combined) {
                System.out.println(e);
            }
        } else { // Error if not enough arguments
            System.err.println("ERROR: not enough arguments");
            System.exit(1);
        }
    }

    /**
     * Method to read each integer value from text file and add each to an ArrayList.
     * @param fileName name of the file
     * @return ArrayList of Integers present in text file
     */
    public static ArrayList<Integer> readToList(String fileName) {
        ArrayList<Integer> arr = new ArrayList<>();
        try(Scanner in = new Scanner(new File(fileName))) {
            while(in.hasNextInt()) {
                int val = in.nextInt();
                arr.add(val);
            } return arr;
        } catch(FileNotFoundException e) { // If file specified in cmd args is not valid, ERROR
            System.err.println("ERROR: "+fileName+" not found");
            System.exit(1);
        }
        return arr;
    }
}
