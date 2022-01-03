
public class Breakfast extends Eatables implements Paid{

	
	// Constructor
	public Breakfast(String weight, float price, String name, String brand, int count) {
		super(weight, price, name, brand, count);
	}
	
	// Functions
	
	public boolean checkStock() {
		return true;
	}
	

	@Override
	public float getPrice(User current_user) {
		return super.getPrice();
	}

}
