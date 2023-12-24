/** Class to find the sqrt without using Math, only works with some numbers
 * @author abendrothj
 */
class Root {
	public static void main(String[] args) {
		double num = 0.0;
		if(args.length > 0) { // parses argument
			try { num = Double.parseDouble(args[0]); } catch(Exception e) { System.out.println("ERROR"); }
			if(num < 0) { System.out.println("ERROR"); }
		} else { System.out.println("ERROR"); }
		double low = 0.0;
		double high = num;
		double avg = (low+high)/2; // root algorithm
		while(high-low > 0.01*low) {
			if(avg*avg > num) { 
				high = avg;
				avg = (low+high)/2;
			} if(avg*avg < num) {
				low = avg;
				avg = (low+high)/2;
			}
		} System.out.println(avg);
	}
}