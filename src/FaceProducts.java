
public class FaceProducts extends PersonelCareProducts implements Paid{

	// Constructor
	public FaceProducts(String weight, float price, String name, String brand, int count, float liter) {
		super(weight, price, name, brand, count, liter);

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
