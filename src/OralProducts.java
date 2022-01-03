
public class OralProducts extends PersonelCareProducts implements Paid {

	// Constructor
	public OralProducts(String weight, float price, String name, String brand, int count, float liter) {
		super(weight, price, name, brand, count, liter);
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
