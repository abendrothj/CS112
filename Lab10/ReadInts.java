import java.util.ArrayList;
import java.util.Scanner;

/** Reading and processing integers separated by spaces and/or lines
 *  returns minimum, maximum, and average values
 *  @author abendrothj
 */
public class ReadInts {
    public static void main(String[] args) {
        System.out.println("Enter integers: ");
        ArrayList<Integer> arr = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        ArrayList<String> lineArray = new ArrayList<>();
        int min = Integer.MAX_VALUE; // setting min and max farthest away as possible
        int max = Integer.MIN_VALUE;
        int i = 0;
        do {                    //  Beautiful do-while loop where it belongs
            lineArray.add(in.nextLine()); // add nextLine to array
            i++;
        } while(!lineArray.get(i-1).isEmpty()); // while the last line captured isn't empty
        for(String str : lineArray) { // for each line in lineArray
            String[] sp = str.split(" "); // split by spaces into array of words
            for(String s : sp) { // for each word
                try {
                    arr.add(Integer.parseInt(s)); // Parse int and add to integer array if success
                } catch (Exception e) { /* Ignore if exception occurs */ }
            }
        }
        for(int x : arr) { // Finding minimum and maximum values in integer array
            if(x < min) { min = x; }
            if(x > max) { max = x; }
        }
        int sum = 0;
        for(int j : arr) { // Finding sum of all values in integer array
            sum += j;
        } double avg = (double) sum / arr.size(); // Calculating average with sum
        System.out.println("Minimum "+min); // Printing results
        System.out.println("Maximum "+max);
        System.out.printf("Average %.2f", avg);
    }
}
