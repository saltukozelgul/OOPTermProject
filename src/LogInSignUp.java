import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.geom.*;
import javax.swing.*;

public class LogInSignUp {
	// Defining objects
	private JFrame frame = new JFrame();
	
	private JLabel label_Email = new JLabel("Email: ", JLabel.LEFT);
	private JLabel label_Password = new JLabel("Password: ", JLabel.LEFT);
	private JLabel label_Information1 = new JLabel("", JLabel.LEFT);
	private JLabel label_Information2 = new JLabel("", JLabel.LEFT);
	private JTextField textField_Email = new JTextField("Enter your e-mail address");
	private JPasswordField passwordField_Password = new JPasswordField("******");
	private JCheckBox checkBox_Information = new JCheckBox();
	private JButton button_Password = new JButton();
	private JButton button_SignUp = new JButton("Log in/Sign up");
	
	Icon icon_OpenEye = new ImageIcon("C:\\Users\\mert7\\Desktop\\OpenEye.png");
	Icon icon_CloseEye = new ImageIcon("C:\\Users\\mert7\\Desktop\\CloseEye.png");
	
	
	// Defining variables
	private int password_Count = 0;
	private int checkBox_Count = 0;
	private boolean is_information_click = false;
	private String information_text = "";
	
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
		panel.add(label_Email); panel.add(label_Password); panel.add(label_Information1); panel.add(label_Information2); 
		panel.add(textField_Email); panel.add(passwordField_Password);
		panel.add(checkBox_Information);
		panel.add(button_Password);
		
		
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
		
	}
	
}
