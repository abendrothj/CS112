/** Program to solve equations in format ax^2 bx c with quadratic formula.
 *  @author abendrothj
 */

public class Quadratic {
    public static void main(String[] args) {
        double a = 0; // create 3 empty doubles
        double b = 0;
        double c = 0;
        if(args.length == 3) {
            // Parses doubles, and if not possible, exception is caught
            try { a = Double.parseDouble(args[0]); } catch(Exception e) { System.out.println("ERROR"); }
            try { b = Double.parseDouble(args[1]); } catch(Exception e) { System.out.println("ERROR"); }
            try { c = Double.parseDouble(args[2]); } catch(Exception e) { System.out.println("ERROR"); }
            double disc = (b * b) - 4 * a * c;
            if (disc < 0 || a == 0) { // if discriminant of equation is negative, or a = 0, not possible
                System.out.println("ERROR");
            } else { // Solves equation for both versions
                double sqrt = Math.sqrt(disc);
                double x1 = (-b + sqrt) / 2 * a;
                double x2 = (-b - sqrt) / 2 * a;
                System.out.println("x1 = " + x1);
                System.out.println("x2 = " + x2);
            }
        }
    }
}
