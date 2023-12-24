/** A class demonstrating the Collatz Conjecture, for integers 1-200
**/
class Collatz {
	public static void main(String[] args) {
		for(int i = 1; i <= 200; i++) {
			System.out.println(process(i)); // Calling method 200 times
		}
	}
	public static String process(int x) {
		int original = x; // Saving original number before change
		int steps = 0;
		while(x != 1) {
			if(x % 2 == 0) { // if even, divide by 2
				x = x/2;
				steps++;
			} else {  // if its not even its odd, 3x+1
				x = 3*x+1; 
				steps++;
			}
		} return original + " " + steps;
	}
}