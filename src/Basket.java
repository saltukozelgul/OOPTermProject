import java.util.ArrayList;

public class Basket {
	
	//  Variables
	private float totalPrice;
	private int productCount;
	private ArrayList<Product> products = new ArrayList<>();
	private int discount_rate;
	// private coupons hash seklinde gelecek
	
	
	// Getters and Setters
	public void setProducts(Product[] product) {
		
	}
	
	public Product getProducts() {
		return products.get(0); // just for now
	}
	
	public float getTotalPrice() {
		return 0;
	}
	
	public  int getProductCount() {
		return 0;
	}
	
	// Functions
	public void clear() {
		
	}
	
	public void addProduct(Product products) {
		
	}
	
	public void removeProduct(String product_name) {
		
	}
	
	public void addCoupon(String coupon_code) {
		
	}

}
