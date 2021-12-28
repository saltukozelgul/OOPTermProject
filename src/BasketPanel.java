import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;

import javax.swing.*;
import javax.swing.border.Border;

public class BasketPanel {
	
	// Defining GUI Objects
	private Gradient panel = new Gradient(); // creates(calls) gradient panel
	private JFrame frame = new JFrame("CheApp");
	private JButton button_Return = new JButton();
	private JButton button_ClearAll = new JButton();
	private JButton button_CouponAdd = new JButton();
	private ArrayList<JButton> button_DeleteProduct = new ArrayList<JButton>();
	private ArrayList<JLabel> label_ProductInformation = new ArrayList<>();
	private JLabel label_TotalPrice = new JLabel();
	private JTextField textField_Coupon = new JTextField();
	
	// Global variables and objects\
	private ArrayList<String> productInformations = new ArrayList<String>();
	private User current_user;
	private Basket basket = new Basket();
	private ArrayList<Product> products = new ArrayList<Product>();
	
	// Constructor
	public BasketPanel(User current_user) {
		
		// Assigning of user class
		this.current_user = current_user;
		
		// File variables
		int index_f_email = current_user.getEmail().indexOf(".");
		String file_name = ".\\users\\" + current_user.getEmail().substring(0, index_f_email) + ".txt";
		
		
		// Calls frame settings
		setFrameSettings();
		
		int product_count = productCount();	
		
		// if user clicks to the return button MainPanel opens
		button_Return.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel mp = new MainPanel(current_user);
				frame.dispose();
				
			}
			
		});
		
		// if user clicks to the remove all products on the user basket 
		button_ClearAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				
				// Calls clear function on basket class
				basket.clear(current_user);
				
				
				BasketPanel bp = new BasketPanel(current_user);
				frame.dispose();
				
			}
			
		});
		
		// Add Coupon
		button_CouponAdd.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				float result = basket.addCoupon(textField_Coupon.getText(), current_user);
				if( result != 0) {
					label_TotalPrice.setText("Total price: " + String.valueOf(result) + "tl");
				}
				else {
					JOptionPane.showMessageDialog(frame, "Unvalid Coupon");

				}	
			}
			
		});
	} // end of constructor
	
	// Frame settings
	public void setFrameSettings() {
		Image icon_IMG = Toolkit.getDefaultToolkit().getImage(".\\\\resources\\\\Logo.jpeg"); // saves icon
		
		// Frame settings
		frame.setTitle("cheAPP");
		frame.setIconImage(icon_IMG); // changes icon
		frame.setSize(335, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit when press to close button
		frame.setResizable(false); // closes size icon
		frame.setLocationRelativeTo(null); // opens the panel middle of the screen
		frame.getContentPane().setLayout(new GridLayout(0, 1));
		
		
		// Calls other settings
		setLabelSettings();
		setButtonSettings();
		setTextFieldSettings();
		
		// Adding gradient objects into the frame
		frame.add(panel);
		
		// Adding objects into the gradient panel
		panel.setLayout(null); // !important
		panel.add(button_Return); panel.add(button_ClearAll);
		
		frame.setVisible(true);

	}
	
	// Button settings
	public void setButtonSettings() {
		
		int product_count = productCount(); // 
		
		Icon icon_Return = new ImageIcon(".\\resources\\returnIcon.png"); // return icon
		Icon icon_Delete = new ImageIcon(".\\resources\\closeButton.png");
		
		// Return button
		button_Return.setIcon(icon_Return);
		button_Return.setFont(new Font(Font.DIALOG, Font.PLAIN, 9));
		button_Return.setHorizontalTextPosition(SwingConstants.CENTER);
		button_Return.setBounds(8, 8, 30, 30);
		button_Return.setBackground(new Color(134,151,129));
		button_Return.setOpaque(false);
		button_Return.setContentAreaFilled(false);
		button_Return.setBorderPainted(false);
		
		
		// Defining first coordinates of first deleteProduct button
		int x_coordinate = 280, y_coordinate = 60;
		
		for(int i=0; i<product_count; i++) {
	
			button_DeleteProduct.add(new JButton());
			
			button_DeleteProduct.get(i).setIcon(icon_Delete);
			button_DeleteProduct.get(i).setFont(new Font(Font.DIALOG, Font.PLAIN, 9));
			button_DeleteProduct.get(i).setHorizontalTextPosition(SwingConstants.CENTER);
			button_DeleteProduct.get(i).setBounds(x_coordinate, y_coordinate, 30, 30);
			button_DeleteProduct.get(i).setBackground(new Color(134,151,129));
			button_DeleteProduct.get(i).setOpaque(false);
			button_DeleteProduct.get(i).setContentAreaFilled(false);
			button_DeleteProduct.get(i).setBorderPainted(false);	
			
			y_coordinate = y_coordinate + 50;
			
			// Adds to the panel
			panel.add(button_DeleteProduct.get(i));
		}
		
		// Clear All button
		button_ClearAll.setText("Clear All");
		button_ClearAll.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
		button_ClearAll.setHorizontalTextPosition(SwingConstants.CENTER);
		button_ClearAll.setBounds(10, 440, 100, 25);
		button_ClearAll.setBackground(new Color(134,151,129));
		
		// Add Coupon button
		button_CouponAdd.setText("Add Coupon");
		button_CouponAdd.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
		button_CouponAdd.setHorizontalTextPosition(SwingConstants.CENTER);
		button_CouponAdd.setBounds(180, 400, 130, 25);
		button_CouponAdd.setBackground(new Color(134,151,129));
		
		panel.add(button_CouponAdd);
	}
	
	// TextField Settings
	public void setTextFieldSettings() {
		Border border = BorderFactory.createLineBorder(new Color(198,23,157));
		
		// Coupon
		textField_Coupon.setText("Enter Coupon Code");
		textField_Coupon.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
		textField_Coupon.setBounds(10, 400, 130, 25);
		textField_Coupon.setForeground(Color.white);
		textField_Coupon.setOpaque(false); // change background color to transparent
		textField_Coupon.setBorder(border);


		
		panel.add(textField_Coupon);
	}
	
	//For choosing right class by type of the product
	private Product createProductByType(ArrayList<String> productInfo) {
		Product product = new Eatables("", 0, "", "", 0); // Temp product
		String type = productInfo.get(3);
		
		// For creating new Product object
		String marketName = productInfo.get(0);;
		String productName = productInfo.get(1);;
		float price = Float.parseFloat(productInfo.get(2));
		int count = 0;
		
		String[] snacks = {"Bisküvi", "Çikolata", "Kekler", "Sakýzlar", "Þekerleme", "Cips & Çerez"};
		if (Arrays.asList(snacks).contains(type)) {
			product = new Snacks("Weight", price, productName, marketName, count);
			return product;
		}

		
		return product;
	}
	// Label settings and Product, buradan devam etmeliyim
	public void setLabelSettings() {
		
		int product_count = productCount(); 
		
		int x_coordinate = 5, y_coordinate = 60; // coordinates
			
		label_ProductInformation.add(new JLabel());
		
		// File variables
		int index_f_email = this.current_user.getEmail().indexOf(".");
		String file_name = ".\\users\\" + this.current_user.getEmail().substring(0, index_f_email) + ".txt", line = "", information = "";
		ArrayList<String> informations = new ArrayList<String>();
			
		try { // Opening and reading file
			File file = new File(file_name);
			BufferedReader read = new BufferedReader(new FileReader(file_name));
			while((line = read.readLine()) != null) {
				if(line.contains("Market Name: ")) {
					
					line.replace("\n", "");
					information = line.replace("Market Name: ", "") + " ";
					productInformations.add(line.replace("Market Name:", ""));
					
				}
				else if(line.contains("Product Name: ")) {
					
					information = information + line.replace("Product Name: ", "") + " ";
					productInformations.add(line.replace("Product Name: ", ""));
				}
				else if(line.contains("Product Price: ")) {
					information = information + line.replace("Product Price: ", "") + "tl";
					productInformations.add(line.replace("Product Price: ", ""));
				}
				else if(line.contains("Product No: ")); // just go
				else if(line.contains("Product Type: ")) {
					productInformations.add(line.replace("Product Type: ", ""));
				}
				else{
					if(information != "") {
						
						// Buraya urunun turune gore nesne olusturulup baskete gonderilmeli
						// Product product = new Product(" ", Float.valueOf(productInformations.get(2)), productInformations.get(1), productInformations.get(0));
	
						// Choose type and send it to basket;
						Product product = createProductByType(productInformations);
						basket.addProduct(product); // Added product into basket
						
						informations.add(information);
						System.out.println(productInformations);
						productInformations.clear();
					}
					information = ""; // clears information string
				}
			}
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		// Creates product labels which is taking products from basket.
		ArrayList<Product> products = basket.getProducts(); 
		for(int i=0; i<product_count; i++) { // creates labels for every different product
			
			label_ProductInformation.add(new JLabel()); // creates new label
			
			// Temptext for label
			String tempText = products.get(i).getBrand() + " " + products.get(i).getName() + " " + products.get(i).getPrice() + "tl";
			System.out.println(tempText);
			// We use this text for setting label's text
			
 			label_ProductInformation.get(i).setText(tempText);
			label_ProductInformation.get(i).setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
			label_ProductInformation.get(i).setBounds(x_coordinate, y_coordinate, 270, 30);
			label_ProductInformation.get(i).setForeground(Color.WHITE);
			
			panel.add(label_ProductInformation.get(i)); // adds label's to the gradient_panel
			
			y_coordinate = y_coordinate  + 50;
			
		}
		
		// Total price
		label_TotalPrice.setText("Total price: " + String.valueOf(basket.getTotalPrice(current_user) + "tl"));
		label_TotalPrice.setBounds(x_coordinate, y_coordinate + 30, 150, 30);
		label_TotalPrice.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
		label_TotalPrice.setForeground(Color.WHITE);
		
		panel.add(label_TotalPrice);

	}
	
	// Finds how many product that user has on his/her basket
	public int productCount() {
		
		// returns product count
		int productCount = basket.getProductCount(current_user);
		
		return productCount;
	}
	
} // end of BasketPanel class
