
public class Snacks extends Eatables implements Paid {

	// Constructor
	public Snacks(String weight, float price, String name, String brand, int count) {
		super(weight, price, name, brand, count);
		// TODO Auto-generated constructor stub
	}
	
	// Functions
	
	public boolean checkStock() {
		return true;
	}
	
	public float getPrice() {
		return 0;
	}

}
