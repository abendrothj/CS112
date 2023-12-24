/** program to fix capitalization
 * @author abendrothj
 */
class FixCapitalization {
	public static void main(String[] args) {
		String input = args[0].toLowerCase(); // Set whole string to lowercase
		input = input.substring(0, 1).toUpperCase() + input.substring(1); // Capitalize first letter
		for(int i = 0; i < input.length(); i++) { // Sets character 2 indices after punctuation to capital
			if(i+3 < input.length()) {
				if (input.charAt(i) == '.' || input.charAt(i) == '?' || input.charAt(i) == '!') {
					input = (input.substring(0, i + 2) + input.substring(i + 2, i + 3).toUpperCase() + input.substring(i + 3));
				}
			}
		}System.out.println(input); // Prints result
	}
}