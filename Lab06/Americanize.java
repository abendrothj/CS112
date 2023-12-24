/** Program to change all instances of tea to coffee
 * @author abendrothj
 */

class WordConvert {
    public String convert(String str) {
        // Checks if string is at least 3 chars, and that the following chars are not letters or numbers.
        if(str.length() > 3 && str.substring(3, 4).matches("[^a-zA-Z0-9]")) {
            if (str.substring(0, 3).equals("tea")) { // checks the first 3 letters for "tea"
                str = "coffee" + str.substring(3);
            }
            if (str.substring(0, 3).equals("Tea")) { // checks first 3 letters for "Tea"
                str = "Coffee" + str.substring(3);
            }
        } else if(str.length() < 4) {
			if(str.equals("tea")) { str = "coffee"; }
			if(str.equals("Tea")) { str = "Coffee"; }
		}
		return str;
    }
}
class Americanize {
    public static void main(String[] args) {
        String[] arr = args[0].trim().split(" "); // Split by spaces
        WordConvert word = new WordConvert();
        String result = "";
        for(int i = 0; i < arr.length; i++) { // concatenate result after conversion
            result += word.convert(arr[i]) + " ";
        } System.out.println(result.trim()); // print result
    }
}