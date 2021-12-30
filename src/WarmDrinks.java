
public class WarmDrinks extends Drinks implements Paid{

	
	// Constructor
	public WarmDrinks(String weight, float price, String name, String brand, float liter, int count) {
		super(weight, price, name, brand, liter, count);
		
	}
	
	// Functions
	
	public boolean checkStock() {
		return true;
	}
	
	public float getPrice() {
		return 0;
	}

	@Override
	public float getPrice(User current_user) {
		// TODO Auto-generated method stub
		return 0;
	}

}
