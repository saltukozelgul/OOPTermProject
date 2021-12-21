import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;

import javax.swing.*;

public class LogInSignUp {
	// Defining objects
	private JFrame frame = new JFrame();
	
	private JLabel label_Email = new JLabel("Email: ", JLabel.LEFT);
	private JLabel label_Password = new JLabel("Password: ", JLabel.LEFT);
	private JLabel label_Information1 = new JLabel("", JLabel.LEFT);
	private JLabel label_Information2 = new JLabel("", JLabel.LEFT);
	private JLabel label_RoboKod = new JLabel("", JLabel.LEFT);
	private JTextField textField_Email = new JTextField("Enter your e-mail address");
	private JTextField textField_RoboKod = new JTextField("");
	private JPasswordField passwordField_Password = new JPasswordField("******");
	private JCheckBox checkBox_Information = new JCheckBox();
	private JButton button_Password = new JButton();
	private JButton button_Next = new JButton();
	private JButton button_Log_Sign = new JButton();
	
	Icon icon_OpenEye = new ImageIcon("C:\\Users\\mert7\\Desktop\\OpenEye.png");
	Icon icon_CloseEye = new ImageIcon("C:\\Users\\mert7\\Desktop\\CloseEye.png");
	
	
	// Defining variables
	public String hold_RandomValue;
	private int password_Count = 0;
	private int checkBox_Count = 0;
	public boolean is_information_click = false;
	
	public LogInSignUp() { 
		setFrameSettings();
		
		// Listener of Information
		checkBox_Information.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(checkBox_Count % 2 == 0) {
					is_information_click = true;
				}
				else {
					is_information_click = false;
				}
				checkBox_Count++;
			}
		});
		
		// Listener of password
		button_Password.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(password_Count % 2 == 0) {
					button_Password.setIcon(icon_OpenEye); // changes icon
					passwordField_Password.setEchoChar((char)0); // shows input
				}
				else {
					button_Password.setIcon(icon_CloseEye); // changes icon
					passwordField_Password.setEchoChar('*'); // hides input
				}
				password_Count++;
			}
		});
		
		// Listener of Next button
		button_Next.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField_Email.getText().length() != 0 && isEmailCorrectForm() == true && passwordField_Password.getPassword().length != 0 &&
						is_information_click == true) { // i'll develop the email and password check statements
					
					button_Next.setVisible(false);
					button_Log_Sign.setVisible(true);
					textField_RoboKod.setVisible(true);
					label_RoboKod.setVisible(true);
					
					// creates new roboCode and puts the roboCode to RoboKod label
					hold_RandomValue = createRoboCode();
					label_RoboKod.setText(hold_RandomValue);
				}
				else {
					JOptionPane.showMessageDialog(frame, "E-mail or password do not provide conditions!");
					textField_Email.setText(""); // clears textField and password
					passwordField_Password.setText("");

				}
			}
		});
		
		// Listener of LogInSignUp button
		button_Log_Sign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(hold_RandomValue.equals(textField_RoboKod.getText()) == true) {
					MainPanel mp = new MainPanel();
					frame.dispose();
				}
				else {
					JOptionPane.showMessageDialog(frame, "Wrong robo code!!!");
					// Closes robo code objects
					textField_RoboKod.setVisible(false);
					label_RoboKod.setVisible(false);
					button_Log_Sign.setVisible(false);
					
					// opens the next button
					button_Next.setVisible(true);
					
					textField_Email.setText(""); // clears textField and password
					passwordField_Password.setText("");
				}
				
			}
		});
		
	}
	
	private void setFrameSettings() {
		
		Image icon_IMG = Toolkit.getDefaultToolkit().getImage("C:\\Users\\mert7\\Desktop\\image.png"); // saves icon
		Gradient panel = new Gradient(); // creates(calls) gradient panel
		
		// Frame settings
		frame.setTitle("CheaApp");
		frame.setIconImage(icon_IMG); // changes icon
		frame.setSize(335, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit when press to close button
		frame.setResizable(false); // closes size icon
		frame.setLocationRelativeTo(null); // opens the panel middle of the screen
		frame.getContentPane().setLayout(new GridLayout(0, 1));
		
		
		// Calls other settings
		setLabelSettings();
		setTextFieldSettings();
		setButtonSettings();
		setCheckBoxSettings();
		
		frame.add(panel); // adds panel to frame
		
		
		panel.setLayout(null); // important!
		panel.add(label_Email); panel.add(label_Password); panel.add(label_Information1); panel.add(label_Information2); panel.add(label_RoboKod); 
		panel.add(textField_Email); panel.add(passwordField_Password); panel.add(textField_RoboKod);
		panel.add(checkBox_Information);
		panel.add(button_Password); panel.add(button_Next); panel.add(button_Log_Sign);
		
		
		frame.setVisible(true); // makes frame can be seen
	}
	private void setCheckBoxSettings() {
		
		// Information
		checkBox_Information.setBounds(275, 250, 25, 25);
		checkBox_Information.setOpaque(false);
	}
	
	private void setButtonSettings() { // default settings of password
		
		// Password
		button_Password.setIcon(icon_CloseEye);
		button_Password.setBounds(270, 210, 25, 25);
		
		// Next
		button_Next.setText("Next...");
		button_Next.setFont(new Font("Serif", Font.PLAIN, 13));
		button_Next.setHorizontalTextPosition(SwingConstants.CENTER);
		button_Next.setBounds(225, 290, 70, 30);
		
		// Log in Sign up 
		button_Log_Sign.setText("Log in/ Sign up");
		button_Log_Sign.setHorizontalAlignment(SwingConstants.CENTER);
		button_Log_Sign.setFont(new Font("Serif", Font.PLAIN, 13));
		button_Log_Sign.setBounds(180, 350, 115, 35);
		button_Log_Sign.setVisible(false);
		
	}
	
	private void setTextFieldSettings() { // text field and password settings
		
		// ID
		textField_Email.setFont(new Font("Serif", Font.ITALIC, 13));
		textField_Email.setBounds(25, 155, 270, 25);
		textField_Email.setOpaque(false); // change background color to transparent
		textField_Email.setForeground(Color.BLACK); // changes color to black
		
		// Password
		passwordField_Password.setFont(new Font("Serif", Font.ITALIC, 13));
		passwordField_Password.setBounds(25, 210, 240, 25);
		passwordField_Password.setOpaque(false); // change background color to transparent
		passwordField_Password.setForeground(Color.BLACK);
		
		// RoboKod
		textField_RoboKod.setFont(new Font("Serif", Font.PLAIN, 13));
		textField_RoboKod.setBounds(180, 300, 115, 25);
		textField_RoboKod.setOpaque(false);
		textField_RoboKod.setVisible(false);
		
	}
	
	private void setLabelSettings() {
		
		// ID
		label_Email.setText("Email: ");
		label_Email.setFont(new Font("Serif", Font.PLAIN, 18));
		label_Email.setBounds(25, 115, 100, 50); // x coordinate, y coordinate, width, height
		label_Email.setForeground(Color.WHITE);
		
		// Password
		label_Password.setText("Password: ");
		label_Password.setFont(new Font("Serif", Font.PLAIN, 18));
		label_Password.setBounds(25, 170, 100, 45); // x coordinate, y coordinate, width, height
		label_Password.setForeground(Color.WHITE);
		
		// Information1 and Information2
		label_Information1.setText("I accept the proccessing of my personel data");
		label_Information1.setFont(new Font("Serif", Font.PLAIN, 13));
		label_Information1.setBounds(25, 245, 300, 25); // x coordinate, y coordinate, width, height
		label_Information1.setForeground(Color.WHITE);
		
		label_Information2.setText("for commercial purposes. ");
		label_Information2.setFont(new Font("Serif", Font.PLAIN, 13));
		label_Information2.setBounds(25, 260, 300, 20); // x coordinate, y coordinate, width, height
		label_Information2.setForeground(Color.WHITE);
		
		// RoboCode
		label_RoboKod.setText("");
		label_RoboKod.setFont(new Font("Serif", Font.PLAIN, 13));
		label_RoboKod.setBounds(25, 300, 115, 25);
		label_RoboKod.setVisible(false);
		
	}
	
	public String createRoboCode() {
		Random random = new Random();
		String roboCode = "";
		int random_number;
		for(int i=0; i<6; i++) {
			random_number = random.nextInt(2);
			
			if(random_number == 0) {
				roboCode = roboCode + String.valueOf(random.nextInt(10));
			}
			else {
				roboCode = roboCode + (char)(random.nextInt(26) + 'a');
			}
		}
		return roboCode;	
	}

	public boolean isEmailCorrectForm() {
		if(textField_Email.getText().contains("@") && (textField_Email.getText().contains("hotmail") || textField_Email.getText().contains("gmail") || 
				textField_Email.getText().contains("outlook")) && textField_Email.getText().contains(".com")) {
			return true;
		}
		else {
			return false;
		}
	}
}
