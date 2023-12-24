/** Tripler program to triple any double, integer, or string input through cmd line
 * @author abendrothj
 */
class Tripler { // Overloaded methods for different types
	public int triple(int input) {
		return input*3;
	}
	public double triple(double input) {
		return input*3;
	}
	public String triple(String input) {
		return input+input+input;
	}
}
class MyTripler {
	public static boolean isInteger(String str) { // Tests if string input is an integer
		if(str.matches("^[0-9]+$")) {
			return true;
		} else {
			return false;
		}
	}
	public static boolean isDouble(String str) { // Tests if string input is a double
		int count = 0;
		int numCount = 0;
		for(int i = 0; i < str.length(); i++) {
			if(str.charAt(i) == '.') {
				count++;
			}
			String ch = str.charAt(i)+"";
			if(ch.matches("[0-9]")) {
				numCount++;
			}
		} if(count+numCount == str.length() && count == 1) {
			return true;
		} else { return false; }
	}
	public static void main(String[] args) { // Tests arg against each boolean method, then triples accordingly
		Tripler t = new Tripler();
		if(args.length > 0) {
			if(isInteger(args[0])) {
				int parsed = Integer.parseInt(args[0]);
				System.out.println(t.triple(parsed));
			} else if(isDouble(args[0])) {
				double parsed = Double.parseDouble(args[0]);
				System.out.println(t.triple(parsed));
			} else {
				System.out.println(t.triple(args[0]));
			}
		} else { System.out.println("ERROR"); } // If no case matches, user error.
	}
}