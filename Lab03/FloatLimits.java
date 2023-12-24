/** Finds smallest possible float and double above 0
*/
class FloatLimits {
	public static void main(String[] args) {
		// Dividing by two until indistinguisable.
		float value = 1.0f;
		while(value / 2 > 0) {
			value /= 2;
		} System.out.println("Smallest positive float is "+value);
		double val = 1.0;
		while(val / 2 > 0) {
			val /= 2;
		} System.out.println("Smallest positive double is "+val);
	}
}