class Program07 {
	public static void main(String[] args) {
		byte secondsPerMinute = 60;
		byte minutesPerHour = 60;
		int secondsPerHour = secondsPerMinute * minutesPerHour;
		short hoursPerDay = 24;
		int secondsPerDay = secondsPerHour * hoursPerDay;
		short daysPerYear = 365;
		int secondsPerYear = secondsPerDay * daysPerYear;
		
		System.out.println("There are " + secondsPerYear + " seconds in a year.");
		double PI = 3.14159265;
		double approx = PI * 1e7;
		System.out.println("A good approximation is " + approx);
	}
}