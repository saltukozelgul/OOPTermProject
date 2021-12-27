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

import javax.swing.*;

public class BasketPanel {
	
	// Defining GUI Objects
	private Gradient panel = new Gradient(); // creates(calls) gradient panel
	private JFrame frame = new JFrame("CheApp");
	private JButton button_Return = new JButton();
	private JButton button_ClearAll = new JButton();
	private ArrayList<JButton> button_DeleteProduct = new ArrayList<JButton>();
	private ArrayList<JLabel> label_ProductInformation = new ArrayList<>();
	
	// Global variables and objects\
	private ArrayList<ArrayList<String>> productInformations = new ArrayList<ArrayList<String>>();
	private User current_user;
	
	
	public BasketPanel(User user) {
		
		// Assigning of user class
		this.current_user = user;
		
		// File variables
		int index_f_email = user.getEmail().indexOf(".");
		String file_name = ".\\users\\" + user.getEmail().substring(0, index_f_email) + ".txt";
		
		
		// Calls frame settings
		setFrameSettings();
		
		int product_count = productCount();
		
		
		
		// if user clicks to the return button
		button_Return.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				MainPanel mp = new MainPanel(user);
				frame.dispose();
				
			}
			
		});
		
		// if user clicks to the Clear All button
		button_ClearAll.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				clear_All(user);
				BasketPanel bp = new BasketPanel(user);
				frame.dispose();
				
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
		int x_coordinate = 280, y_coordinate = 90;
		
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
		button_ClearAll.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		button_ClearAll.setHorizontalTextPosition(SwingConstants.CENTER);
		button_ClearAll.setBounds(10, 440, 100, 30);
		button_ClearAll.setBackground(new Color(134,151,129));
	
	}
	
	// Label settings
	public void setLabelSettings() {
		int product_count = productCount();
		
		int x_coordinate = 5, y_coordinate = 90; // coordinates
			
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
				}
				else if(line.contains("Product Name: ")) {
					information = information + line.replace("Product Name: ", "") + " ";
				}
				else if(line.contains("Product Price: ")) {
					information = information + line.replace("Product Price: ", "") + "tl";
				}
				else if(line.contains("Product No: ")); // just go
				else{
					if(information != "") {
						informations.add(information);
					}
					information = ""; // clears information string
				}
			}
			read.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println(informations);
		
		for(int i=0; i<product_count; i++) { // creates labels for every different product
			
			label_ProductInformation.add(new JLabel()); // creates new label
			
			label_ProductInformation.get(i).setText(informations.get(i));
			label_ProductInformation.get(i).setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
			label_ProductInformation.get(i).setBounds(x_coordinate, y_coordinate, 270, 30);
			label_ProductInformation.get(i).setForeground(Color.WHITE);
			
			panel.add(label_ProductInformation.get(i)); // adds label's to the gradient_panel
			
			y_coordinate = y_coordinate  + 50;
			
		}

	}
	
	// Finds how many product that user has on his/her basket
	public int productCount() {
		
		// File variables
		int index_f_email = this.current_user.getEmail().indexOf("."), product_count = 0;;
		String file_name = ".\\users\\" + this.current_user.getEmail().substring(0, index_f_email) + ".txt", line = "";
		
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
	
	// clears all items on basket
	public void clear_All(User user) {
		
		int index_f_email = this.current_user.getEmail().indexOf(".");
		String file_name = ".\\users\\" + this.current_user.getEmail().substring(0, index_f_email) + ".txt", line = "", information = "";
		String new_content = "";
		try {
			new_content = "E-mail: " + user.getEmail() + "\nPassowrd: " + user.getPassoword() + "\n";
			
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
	
} // end of BasketPanel class
