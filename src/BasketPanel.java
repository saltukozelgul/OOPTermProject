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
import java.io.IOException;

import javax.swing.*;

public class BasketPanel {
	
	// Defining GUI Objects
	private Gradient panel = new Gradient(); // creates(calls) gradient panel
	private JFrame frame = new JFrame("CheApp");
	private JButton button_Return = new JButton();
	private JButton button_ClearAll = new JButton();
	
	// Global variables and objects\
	User current_user;
	
	
	public BasketPanel(User user) {
		
		// Assigning of user class
		this.current_user = user;
		
		// File variables
		int index_f_email = user.getEmail().indexOf(".");
		String file_name = ".\\users\\" + user.getEmail().substring(0, index_f_email) + ".txt";
		
		
		// Calls frame settings
		setFrameSettings();
		
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
		
		// Return button
		button_Return.setIcon(icon_Return);
		button_Return.setFont(new Font(Font.DIALOG, Font.PLAIN, 9));
		button_Return.setHorizontalTextPosition(SwingConstants.CENTER);
		button_Return.setBounds(8, 8, 30, 30);
		button_Return.setBackground(new Color(134,151,129));
		button_Return.setOpaque(false);
		button_Return.setContentAreaFilled(false);
		button_Return.setBorderPainted(false);
		
		// Clear All button
		button_ClearAll.setIcon(icon_Return);
		button_ClearAll.setFont(new Font(Font.DIALOG, Font.PLAIN, 9));
		button_ClearAll.setHorizontalTextPosition(SwingConstants.CENTER);
		button_ClearAll.setBounds(8, 8, 30, 30);
		button_ClearAll.setBackground(new Color(134,151,129));
		button_ClearAll.setOpaque(false);
		button_ClearAll.setContentAreaFilled(false);
		button_ClearAll.setBorderPainted(false);
		
		
		JButton buttons_DeleteProduct[] =  new JButton[product_count + 1];
		for(int i=0; i<=product_count; i++) {
			buttons_DeleteProduct[i] = new JButton();
			buttons_DeleteProduct[i].setIcon(icon_Return);
			buttons_DeleteProduct[i].setFont(new Font(Font.DIALOG, Font.PLAIN, 9));
			buttons_DeleteProduct[i].setHorizontalTextPosition(SwingConstants.CENTER);
			buttons_DeleteProduct[i].setBounds(200, 100, 30, 30);
			buttons_DeleteProduct[i].setBackground(new Color(134,151,129));
			buttons_DeleteProduct[i].setOpaque(false);
			buttons_DeleteProduct[i].setContentAreaFilled(false);
			buttons_DeleteProduct[i].setBorderPainted(false);	
			panel.add(buttons_DeleteProduct[i]);
		}
		
	}
	
	// Label settings
	public void setLabelSettings() {
		
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
				if(line.equals("Market Name: ")) {
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
	
	
} // end of BasketPanel class
