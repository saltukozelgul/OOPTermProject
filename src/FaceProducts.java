
public class FaceProducts extends PersonelCareProducts implements Paid{

	// Constructor
	public FaceProducts(String weight, float price, String name, String brand, int count, float liter) {
		super(weight, price, name, brand, count, liter);
		// TODO Auto-generated constructor stub
	}
	
	// Functions
	
	public boolean checkStock() {
		return false;
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
