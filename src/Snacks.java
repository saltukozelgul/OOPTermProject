
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

	@Override
	public float getPrice(User current_user) {
		
		return super.getPrice();
	}
	

}
