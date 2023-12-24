class Pizza {
	private double radius;
	public Pizza(double r) { // Constructor to reduce code
		this.radius = r;
	}
	public void setRadius(double r) { // Sets pizza radius
		this.radius = r;
	}
	public double basePrice() { // Calculates base price with area
		return 0.15*(Math.PI*this.radius*this.radius);
	}
	public double tax() { // Calculates the 10% tax
		return this.basePrice()*0.10;
	}
	public double tip() { // 15% tip 
		return this.basePrice()*0.15;
	}
	public double total() { // Adds it all together
		return this.basePrice()+this.tax()+this.tip();
	}
	public String toString() { // Creating toString to reduce code
		return "A pizza of radius " + this.radius + " has base price " + this.basePrice() + " and total price " + this.total();
	}
}
class PizzaInfo {
	public static void main(String[] args) {
		Pizza p1 = new Pizza(0.0); // Creating pizzas of each radius
		Pizza p2 = new Pizza(5.0);
		Pizza p3 = new Pizza(8.0);
		System.out.println(p1.toString()); // Printing pizza basePrice, total, and radius
		System.out.println(p2.toString());
		System.out.println(p3.toString());
	}
}
