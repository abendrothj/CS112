/** Shows data conversion between larger to smaller data types
 */
class DataConvert {
	public static void main(String[] args) {
		// Float to int conversions
		float var = 2.5f;
		int val = (int) var;
		System.out.println("2.5 cast to int gives "+val);
		var = -4.5f;
		val = (int) var;
		System.out.println("-4.5 cast to int gives "+val);
		// Double to Float conversion
		double a = (1.0/3.0);
		var = (float) a;
		System.out.println("double 1/3 = "+a+" but float 1/3 = "+var);
		// Int to byte conversions
		val = 256;
		byte b = (byte) val;
		System.out.println("byte value of 256 is "+b);
		val = 257;
		b = (byte) val;
		System.out.println("byte value of 257 is "+b);
		val = 258;
		b = (byte) val;
		System.out.println("byte value of 258 is "+b);
		val = 511;
		b = (byte) val;
		System.out.println("byte value of 511 is "+b);
	}
}