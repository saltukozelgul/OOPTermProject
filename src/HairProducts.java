
public class HairProducts extends PersonelCareProducts implements Paid {

	// Constructor
	public HairProducts(String weight, float price, String name, String brand, int count, float liter) {
		super(weight, price, name, brand, count, liter);
		// TODO Auto-generated constructor stub
	}
	
	// Functions
	
	public boolean checkStock() {
		return false;
	}
	

	@Override
	public float getPrice(User current_user) {
		
		return super.getPrice();
	}

}
