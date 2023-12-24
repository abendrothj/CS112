import java.util.Scanner;

/**
 * Program to calculate functions of inputted number
 * Accepts sine, cosine, tangent, and logarithm
 * @author abendrothj
 */
public class Calculator {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.println("Enter a number"); // Prompt for number
        double num = in.nextDouble(); // collect number
        System.out.println("Enter a math function"); // Prompt for function
        String func = in.next(); // collect function
        double answer = 0.0;
        switch(func.substring(0, 3)) { // Switch statement for the first 3 chars of function
            case "Sin": // Intentional switch fall-through for less case-sensitivity
            case "sin":
                answer = Math.sin(num);
                break;
            case "Cos":
            case "cos":
                answer = Math.cos(num);
                break;
            case "Tan":
            case "tan":
                answer = Math.tan(num);
                break;
            case "Log":
            case "log":
                answer = Math.log(num);
                break;
            default:
                System.err.print("ERROR: unrecognized function");
                break;
        } System.out.println(answer); // Print answer after calculation
    }
}
