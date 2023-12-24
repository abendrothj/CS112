class Program05 {
	public static void main(String[] args) {
		float radius = 5;
		double height = 10.0;
		double baseArea = Math.PI*(radius * radius);
		double coneVolume = (1.0/3.0) * baseArea * height;
		System.out.println("The volume of our cone is " + coneVolume);
	}
}