import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
	
	public  int getProductCount(User current_user) {
		// File variables
		int index_f_email = current_user.getEmail().indexOf("."), product_count = 0;;
		String file_name = ".\\users\\" + current_user.getEmail().substring(0, index_f_email) + ".txt", line = "";
		try { // Opening and reading file
			File file = new File(file_name);
			BufferedReader read = new BufferedReader(new FileReader(file_name));
			while((line = read.readLine()) != null) {
				if(line.contains("Market Name: ")) {
					product_count = product_count + 1; // with this loop we will find how many product we have
				}
			}
			read.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		return product_count;
	}
	
	// Functions
	public void clear(User current_user) {
		int index_f_email = current_user.getEmail().indexOf(".");
		String file_name = ".\\users\\" + current_user.getEmail().substring(0, index_f_email) + ".txt", line = "", information = "";
		String new_content = "";
		try {
			new_content = "E-mail: " + current_user.getEmail() + "\nPassowrd: " + current_user.getPassoword() + "\n";
			
			System.out.println(new_content);
			FileWriter write_t_file = new FileWriter(file_name);
			PrintWriter printWriter = new PrintWriter(write_t_file);
			
			printWriter.printf(new_content);
			
			write_t_file.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void addProduct(Product products) {
		
	}
	
	public void removeProduct(String product_name) {
		
	}
	
	public void addCoupon(String coupon_code) {
		
	}


}
