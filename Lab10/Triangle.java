/**
 * Nested for() loops to make a number triangle up to 15
 * @author abendrothj
 */
public class Triangle {
    public static void main(String[] args) {
        for(int i = 1; i < 16; i++) { // 15 Iterations
            System.out.print(i+" "); // print first number, i
            int num = i;
            for(int j = 1; j < i; j++) { // adds i to num, i times
                num += i;
                System.out.print(num+" ");
            } System.out.println(); // Next line
        }
    }
}
