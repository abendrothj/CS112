/**
 * Program that implements the Euclid algorithm to find the GCF of numbers A and B.
 * (Numbers given through cmd line input, args[0] & args[1])
 * @author abendrothj
 */
public class Euclid {
    public static void main(String[] args) {
        // If 2 arguments present, try to convert to integers
        if(args.length == 2) {
            try {
                int a = Integer.parseInt(args[0]);
                int b = Integer.parseInt(args[1]);
                System.out.println(GCF(a, b));
            } // If no conversion possible, ERROR
            catch(Exception e) {
                System.err.println("ERROR: non-integer input");
            }
        } else { // If exactly 2 arguments are not present, ERROR
            System.err.println("ERROR: not enough inputs");
        }
    }

    /**
     * Euclid's algorithm
     * @param a First integer
     * @param b Second integer
     * @return Greatest Common Factor of A and B
     */
    public static int GCF(int a, int b) {
        if(b==0) {
            return a;
        } else { // Recursion
            return GCF(b, a % b);
        }
    }
}
