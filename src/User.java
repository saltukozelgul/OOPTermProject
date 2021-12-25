public class User {
	// Variables
	
	private String e_mail;
	private String password;
	private Basket basket;
	
	
	// Getters and Setters
	
	public String getEmail() {
		return e_mail;
	}
	
	public void setEmail(String name) {
		this.e_mail = name;
	}
	
	public String getPassoword() {
		return password;
	}
	
	public void setPassword(String identity_number) {
		this.password = identity_number;
	}
	
	public Basket getBasket() {
		return this.basket;
		
	}
	
	// Functions
	
	public void logOut() {
		
	}
}
