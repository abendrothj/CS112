class Program02 {
	public static void main(String[] args) {
		double tip = 0.20; // percentage
		double pizzaPrice = 24.50 * tip; // dollars
		double taxRate = 0.08625; // percentage
		double total = pizzaPrice + pizzaPrice*taxRate; // Add in the tip!
		System.out.println("Total price of a $" + pizzaPrice + " pizza is $" + total);
	}
}