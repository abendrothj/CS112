import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/** Program that reads exactly 8 lines and splits every word in each line into its own line
 * @author abendrothj
 */
public class EightLines {

    private static String result = "";
    public static void main(String[] args) {
        for(int i = 0; i < 8; i++) { // Eight lines of input
            Scanner in = new Scanner(System.in);
            String line = in.nextLine(); // Reads next line
            String[] words = line.split(" "); // adds each split word to array
            for(String e : words) { // for every element in array, if not empty add to result string
                if(!e.isEmpty()) {
                    result += e+"\n";
                }
            }
        } System.out.print(result); // print result string
    }
}
