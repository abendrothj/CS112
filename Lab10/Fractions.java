/**
 * Fraction to decimal converter, accepts whole numbers, fractions, mixed numbers
 * @author abendrothj
 */
public class Fractions {
    public static void main(String[] args) {
        String line = args[0].trim(); // Removes spaces at start and end of string
        double val = 0.0;
        double denomLoc = Double.parseDouble(line.substring(line.indexOf("/") + 1)); // Location of denom

        if(line.contains("/") && !line.contains(" ")) { // If fraction
            double numer = Double.parseDouble(line.substring(0, line.indexOf("/"))); // Set numerator
            val = numer / denomLoc; // calculate decimal value
        }
        else if(line.contains("/") && line.contains(" ")) { // If mixed number
            int whole = Integer.parseInt(line.substring(0, line.indexOf(" "))); // collect whole number & set numerator
            double numer = Double.parseDouble(line.substring(line.lastIndexOf(" ")+1, line.indexOf("/")));
            if(whole < 0) { numer *= -1; } // If whole num is negative, fraction should be too
            val = whole + (numer / denomLoc); // calculate decimal value
        }
        else if(!line.contains("/") && !line.contains(" ")) { // If whole number
            val = Double.parseDouble(line); // parses into double
        } System.out.println(val); // Printing result
    }
}