import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
public class SortTest {
    public static void main(String[] args) throws FileNotFoundException {
        Scanner in = new Scanner(new File("sort.txt"));
        String[] spl = in.nextLine().split(" ");
        boolean fail = false;
        if(spl.length == 10000) {
            for(int i = 0; i < spl.length; i++) {
                if(i != spl.length-1 && !(Integer.parseInt(spl[i]) <= Integer.parseInt(spl[i+1]))) {
                    System.out.println("FAIL incorrect sort");
                    fail = true;
                    break;
                }
            }
        } else {
            fail = true;
            System.out.println("FAIL incorrect element count");
        } if(!fail) {
            System.out.println("PASS");
        }
    }
}
