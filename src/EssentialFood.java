
public class EssentialFood extends Eatables {
	
	// Constructor
	public EssentialFood(String weight, float price, String name, String brand, int count) {
		super(weight, price, name, brand, count);

	}
	
	// Functions
	
	public boolean checkStock() {
		return true;
	}
	
	public float getPrice() {
		return super.getPrice();
	}
}
