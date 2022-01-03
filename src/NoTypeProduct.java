
public class NoTypeProduct extends Product implements Paid{

	private float liter;
	
	// Constructor 1
	public NoTypeProduct(String weight, float price, String name, String brand) {
		super(weight, price, name, brand);
		// TODO Auto-generated constructor stub
	}

	// Constructor 2
	public NoTypeProduct(String weight, float price, String name, String brand, float liter) {
		super(weight, price, name, brand);
		setLiter(liter);
	}

	// Getters and Setters
	public float getLiter() {
		return liter;
	}

	public void setLiter(float liter) {
		this.liter = liter;
	}
	
	@Override
	public float getPrice(User current_user) {
		return liter;
		
	}
}
