class Limits {
	public static void main(String[] args) {
		// Byte max and min values
		byte var1 = -1;
		byte var2 = 0;
		while(var1 < var2) {
			var2 = var1;
			var1--;
		} System.out.println("Maximum byte value is " + var1);
		var1 = 0;
		var2 = -1;
		while(var1 > var2) {
			var2 = var1;
			var1++;
		} System.out.println("Minimum byte value is " + var1);
		// Short max and min values
		short var3 = -1;
		short var4 = 0;
		while(var3 < var4) {
			var4 = var3;
			var3--;
		} System.out.println("Maximum short value is " + var3);
		var3 = 0;
		var4 = -1;
		while(var3 > var4) {
			var4 = var3;
			var3++;
		} System.out.println("Minimum short value is " + var3);
		// Int min and max values
		int var5 = -1;
		int var6 = 0;
		while(var5 < var6) {
			var6 = var5;
			var5--;
		} System.out.println("Maximum int value is " + var5);
		var5 = 0;
		var6 = -1;
		while(var5 > var6) {
			var6 = var5;
			var5++;
		} System.out.println("Minimum int value is " + var5);
	}
}