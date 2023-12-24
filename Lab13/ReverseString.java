// This program inputs a string from args[0], reverses it,
// and prints out the result.

class ReverseString {
    // Reverse the chars in 'input' and return the result.
    static String StringToCharsReverse(String input) {
        char[] output = new char[input.length()];
        int i = input.length();
        int j = 0;
        String res = "";
        while (i > 0) {
            output[j] = input.charAt(--i);
            j++;
        } for(char e : output) {
            res += e;
        } return res;
    }


    public static void main(String[] args) {
        if (args.length < 1) {
            System.err.println("ERROR: need input arg");
            return;
        }
        String input = args[0];
        System.out.println(StringToCharsReverse(input));
    }
}