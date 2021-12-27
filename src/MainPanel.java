import java.awt.*;
import java.awt.event.*;

import javax.swing.*;

public class MainPanel {
	
	JFrame frame = new JFrame();
	
	JButton button_Return = new JButton();
	JButton button_Basket = new JButton();
	JButton button_Barcode = new JButton();
	
	JLabel label_cheAPP = new JLabel("cheAPP", JLabel.CENTER);
	JLabel label_Welcome = new JLabel("Welcome!", JLabel.CENTER);
	JLabel label_Logo = new JLabel(new ImageIcon(".\\resources\\smallLogo.png"));
	
	Icon icon_Return = new ImageIcon("C:\\Users\\mert7\\Desktop\\image.png"); // saves icon
	
	public MainPanel(User current_user) {
		setFrameSettings();
		
		button_Return.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				LogInSignUp LINS = new LogInSignUp();
				frame.dispose();
			}
		});
		
		button_Basket.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BasketPanel BP = new BasketPanel(current_user);
				frame.dispose();
			}
		});
		
		button_Barcode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				frame.dispose();
				BarcodeReader BR = new BarcodeReader(current_user);
				// Bu kodun devamý BarcodeReader içinde çalýþacak.
			}
		});
	}
	
	private void setFrameSettings()
	{
		Gradient panel = new Gradient();
		Image icon_IMG = Toolkit.getDefaultToolkit().getImage("C:\\Users\\mert7\\Desktop\\image.png"); // saves icon
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
				
		frame.add(panel); // adds panel to frame	
				
		panel.setLayout(null);
		//add labels
		panel.add(label_cheAPP); panel.add(label_Welcome);
				
		//add buttons
		panel.add(button_Return); panel.add(button_Basket); panel.add(button_Barcode); 
				
		panel.add(label_Logo);
				
		frame.setVisible(true); // makes frame can be seen
	}


	private void setLabelSettings() {
		//label of cheAPP
		label_Logo.setLocation(10, 90);
		label_Logo.setSize(300,85);
	}

	private void setButtonSettings() 
	{
		//return button 
		Icon icon_Return = new ImageIcon(".\\resources\\returnIcon.png"); // return icon
		Icon icon_Basket = new ImageIcon(".\\resources\\basketButton.png");
		Icon icon_Barcode = new ImageIcon(".\\resources\\barcodeButton.png");
		// Return button
		button_Return.setIcon(icon_Return);
		button_Return.setBounds(8, 8, 30, 30);
		button_Return.setOpaque(false);
		button_Return.setContentAreaFilled(false);
		button_Return.setBorderPainted(false);
		
		//basket button
		button_Basket.setIcon(icon_Basket);
		button_Basket.setHorizontalTextPosition(SwingConstants.CENTER);
		button_Basket.setBounds(10, 320, 300, 64);
		button_Basket.setOpaque(false);
		button_Basket.setContentAreaFilled(false);
		button_Basket.setBorderPainted(false);
		
		//barcode button
		button_Barcode.setIcon(icon_Barcode);
		button_Barcode.setHorizontalTextPosition(SwingConstants.CENTER);
		button_Barcode.setBounds(10, 400, 300, 64);
		button_Barcode.setOpaque(false);
		button_Barcode.setContentAreaFilled(false);
		button_Barcode.setBorderPainted(false);
	}
}

