import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.*;
import java.awt.geom.*;
import java.util.Random;

import javax.swing.*;
import javax.swing.border.Border;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import java.io.File; // for file operations
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;


public class LogInSignUp {
	// Defining objects
	private JFrame frame = new JFrame();
	
	private JLabel label_Email = new JLabel("Email: ", JLabel.LEFT);
	private JLabel label_Password = new JLabel("Password: ", JLabel.LEFT);
	private JLabel label_Information1 = new JLabel("", JLabel.LEFT);
	private JLabel label_Information2 = new JLabel("", JLabel.LEFT);
	private JLabel label_cheAPP = new JLabel("cheAPP", JLabel.CENTER);
	private JTextField textField_Email = new JTextField("Enter your e-mail address");
	private JTextField textField_RoboKod = new JTextField("");
	private JPasswordField passwordField_Password = new JPasswordField("******");
	private JCheckBox checkBox_Information = new JCheckBox();
	private JButton button_Password = new JButton();
	private JButton button_Next = new JButton();
	private JButton button_Log_Sign = new JButton();
	private JButton button_IForgotMyPassword =  new JButton();
	
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
				int index_f_email = 0, c=0, count = 0;
				String file_name = "";
				String password = "";
				
				// creating file name
				index_f_email = textField_Email.getText().indexOf('.');
				file_name = "C:\\Users\\mert7\\Desktop\\Users\\" + textField_Email.getText().substring(0, index_f_email) + ".txt";
				
				try {
					FileReader read_file = new FileReader(file_name);
					while((c = read_file.read()) != -1) {
						if((char)c == ' ') {
							count++;
						}
						if(count == 4 && (char)c != '\n') {
							password = password  + (char)c;
						}
					}
					System.out.println(password);
					read_file.close();
					
				} catch (FileNotFoundException e2) { // file does not exist
					// TODO Auto-generated catch block
					e2.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				
				if(textField_Email.getText().length() != 0 && isEmailCorrectForm() == true && passwordField_Password.getPassword().length != 0 &&
						is_information_click == true && passwordField_Password.getPassword().length >= 5) { // i'll develop the email and password check statements
					try {
						if(isUserExists() == true) { // if is user has been signed up, directly sign in
							if(password == passwordField_Password.getPassword().toString()) { // already user
								MainPanel mp = new MainPanel();
								frame.dispose();
							}
							else {
								button_Next.setVisible(false);
								button_Log_Sign.setVisible(true);
								textField_RoboKod.setVisible(true);
								
								// creates new roboCode and puts the roboCode to RoboKod label
								hold_RandomValue = createRoboCode();
								
								// Sends email
								sendEmail();
							}
						}
						else {
							button_Next.setVisible(false);
							button_Log_Sign.setVisible(true);
							textField_RoboKod.setVisible(true);
							
							// creates new roboCode and puts the roboCode to RoboKod label
							hold_RandomValue = createRoboCode();
							
							// Sends email
							sendEmail();
							
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(frame, "E-mail or password do not provide conditions!");
					textField_Email.setText(""); // clears textField and password
					passwordField_Password.setText("");
					button_IForgotMyPassword.setVisible(true);

				}
			}
		});
		
		// Listener of LogInSignUp button
		button_Log_Sign.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				if(hold_RandomValue.equals(textField_RoboKod.getText()) == true) {
					try {
						if(isUserExists()) { // user comes from forgot password
							JOptionPane.showMessageDialog(frame, "Password has been changed succesfully.");
							
						}
						else { // user comes for creating account
							createAccount();
							MainPanel mp = new MainPanel();
							frame.dispose();
						}
					} catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				else {
					JOptionPane.showMessageDialog(frame, "Wrong robo code!!!");
					// Closes robo code objects
					textField_RoboKod.setVisible(false);
					button_Log_Sign.setVisible(false);
					
					// opens the next button and next button settings
					button_Next.setVisible(true);
					button_Next.setText("Next");
					button_Next.setBounds(225, 320, 70, 30);
					
					// opens the i forgot my password button
					button_IForgotMyPassword.setVisible(true);
					
					textField_Email.setText(""); // clears textField and password
					passwordField_Password.setText("");
					
				}
			}
		});
		
		// Hover of IForgotMyPassword
		button_IForgotMyPassword.addMouseListener(new java.awt.event.MouseAdapter() {
		    public void mouseEntered(java.awt.event.MouseEvent evt) {
		    	button_IForgotMyPassword.setForeground(new Color(209, 51, 215));
		    }
		    public void mouseExited(java.awt.event.MouseEvent evt) {
		    	button_IForgotMyPassword.setForeground(new Color(142, 7, 146));
		    }
		});
		
		// Listener of IForgotMyPassword
		button_IForgotMyPassword.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				passwordField_Password.setText("Enter your new password...");
				passwordField_Password.setEchoChar((char)0); // shows input
				password_Count++;
				
				// Changes the name of button_Next
				button_Next.setText("Change");
				button_Next.setBounds(215, 320, 80, 30);
				
				// Closes extra things
				button_IForgotMyPassword.setVisible(false);
			}	
		});
	}
	
	// Frame settings, adds gradient, adds all other objects
	private void setFrameSettings() {
		
		Image icon_IMG = Toolkit.getDefaultToolkit().getImage("C:\\Users\\mert7\\Desktop\\image.png"); // saves icon
		Gradient panel = new Gradient(); // creates(calls) gradient panel
		
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
		setTextFieldSettings();
		setButtonSettings();
		setCheckBoxSettings();
		
		frame.add(panel); // adds panel to frame
		
		
		panel.setLayout(null); // important!
		panel.add(label_cheAPP);panel.add(label_Email); panel.add(label_Password); panel.add(label_Information1); panel.add(label_Information2);
		panel.add(textField_Email); panel.add(passwordField_Password); panel.add(textField_RoboKod);
		panel.add(checkBox_Information);
		panel.add(button_IForgotMyPassword); panel.add(button_Password); panel.add(button_Next); panel.add(button_Log_Sign);
		
		
		frame.setVisible(true); // makes frame can be seen
	}
	
	// CheckBox settings
	private void setCheckBoxSettings() {
		
		// Information
		checkBox_Information.setBounds(275, 277, 25, 25);
		checkBox_Information.setOpaque(false);
	}
	
	// Button settings
	private void setButtonSettings() { // default settings of password
		
		// Password
		button_Password.setIcon(icon_CloseEye);
		button_Password.setBounds(270, 230, 25, 25);
		
		// Next
		button_Next.setText("Next");
		button_Next.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
		button_Next.setHorizontalTextPosition(SwingConstants.CENTER);
		button_Next.setBounds(225, 320, 70, 30);
		button_Next.setBackground(new Color(134,151,129));
		
		// Log in Sign up 
		button_Log_Sign.setText("Log in / Sign up");
		button_Log_Sign.setHorizontalAlignment(SwingConstants.CENTER);
		button_Log_Sign.setFont(new Font(Font.DIALOG , Font.PLAIN, 11));
		button_Log_Sign.setBounds(110, 385, 190, 35); // i'll change width 
		button_Log_Sign.setVisible(false);
		button_Log_Sign.setBackground(new Color(134,151,129));
		
		// I forgot my password
		button_IForgotMyPassword.setText("Forgot password?");
		button_IForgotMyPassword.setFont(new Font(Font.DIALOG, Font.ITALIC, 11));
		button_IForgotMyPassword.setBounds(175, 255, 150, 25);
		button_IForgotMyPassword.setForeground(new Color(142, 7, 146));
		
		// makes button transparent
		button_IForgotMyPassword.setOpaque(false);
		button_IForgotMyPassword.setContentAreaFilled(false);
		button_IForgotMyPassword.setBorderPainted(false);
	}
	
	// TextField and PasswordField settings
	private void setTextFieldSettings() { // text field and password settings
		
		// ID
		textField_Email.setFont(new Font(Font.DIALOG, Font.ITALIC, 13));
		textField_Email.setBounds(25, 175, 270, 25);
		textField_Email.setOpaque(false); // change background color to transparent
		textField_Email.setForeground(Color.BLACK); // changes color to black
		
		// Password
		passwordField_Password.setFont(new Font(Font.DIALOG, Font.ITALIC, 13));
		passwordField_Password.setBounds(25, 230, 240, 25);
		passwordField_Password.setOpaque(false); // change background color to transparent
		passwordField_Password.setForeground(Color.BLACK);
		passwordField_Password.setToolTipText("Enter more than five character");
		
		// RoboKod
		textField_RoboKod.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
		textField_RoboKod.setBounds(180, 320, 115, 25);
		textField_RoboKod.setOpaque(false);
		textField_RoboKod.setVisible(false);
		
	}
	
	// label settings
	private void setLabelSettings() {
		
		//brand 
		label_cheAPP.setText("cheAPP");
		label_cheAPP.setFont(new Font(Font.MONOSPACED , Font.BOLD, 60));
		label_cheAPP.setBounds(-40, 50, 400, 50);
		label_cheAPP.setForeground(new Color(134,151,129));
		
		// ID
		label_Email.setText("Email: ");
		label_Email.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		label_Email.setBounds(25, 135, 100, 50); // x coordinate, y coordinate, width, height
		label_Email.setForeground(Color.WHITE);
		
		// Password
		label_Password.setText("Password: ");
		label_Password.setFont(new Font(Font.DIALOG, Font.PLAIN, 16));
		label_Password.setBounds(25, 190, 100, 45); // x coordinate, y coordinate, width, height
		label_Password.setForeground(Color.WHITE);
		
		// Information1 and Information2
		label_Information1.setText("I accept the proccessing of my personel data");
		label_Information1.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
		label_Information1.setBounds(25, 275, 300, 25); // x coordinate, y coordinate, width, height
		label_Information1.setForeground(Color.WHITE);
		
		label_Information2.setText("for commercial purposes. ");
		label_Information2.setFont(new Font(Font.DIALOG, Font.PLAIN, 12));
		label_Information2.setBounds(25, 295, 300, 20); // x coordinate, y coordinate, width, height
		label_Information2.setForeground(Color.WHITE);
		
	}
	
	// creates roboCode for adding new account
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
	
	// checks the email, is email on correct form
	public boolean isEmailCorrectForm() {
		if(textField_Email.getText().contains("@") && (textField_Email.getText().contains("hotmail") || textField_Email.getText().contains("gmail") || 
				textField_Email.getText().contains("outlook")) && textField_Email.getText().contains(".com")) {
			return true;
		}
		else {
			return false;
		}
	}
	
	// sends email
	public void sendEmail() {
			String to = textField_Email.getText();
			String from = "ooprojectproject@gmail.com";
			String host = "smtp.gmail.com";
			
			// HOST SETTINGS
			Properties properties = System.getProperties(); 
			properties.put("mail.smtp.host", host);
	        properties.put("mail.smtp.port", "465");
	        properties.put("mail.smtp.ssl.enable", "true");
	        properties.put("mail.smtp.auth", "true");
	        
			// Opens main mail account
	        Session session = Session.getInstance(properties, new javax.mail.Authenticator() {

	            protected PasswordAuthentication getPasswordAuthentication() {

	                return new PasswordAuthentication("ooprojectproject@gmail.com", "ooproject");

	            }

	        });

	        // Sending e-mail
	        try {
	        	String text = "Vertification code : " + this.hold_RandomValue;
	            MimeMessage message = new MimeMessage(session);
	            message.setFrom(new InternetAddress(from));

	            message.addRecipient(Message.RecipientType.TO, new InternetAddress(to));

	            message.setSubject("CheApp");
	            message.setText(text);
	            
	            Transport.send(message);
	        } catch (MessagingException mex) {
	            mex.printStackTrace();
	        }

	}
	
	// check is there user with that e-mail, create new file
	private void createAccount() {
		int index_f_email = 0;
		String file_name = "";
		
		
		// creating file name
		index_f_email = textField_Email.getText().indexOf('.');
		file_name = "C:\\Users\\mert7\\Desktop\\Users\\" + textField_Email.getText().substring(0, index_f_email) + ".txt";
		
		// File operations
		File new_file = new File(file_name);
		if(new_file.exists()) { // there is file , i ll change password 
			writeToFile(file_name);
		}
		else { // there is no file with that name, i am gonna create new file
			writeToFile(file_name);
		}		
	}
	
	// checks the user for forgot_password section
	private boolean isUserExists() throws IOException {
		int index_f_email = 0;
		String file_name = "";
		
		// creating file name
		index_f_email = textField_Email.getText().indexOf('.');
		file_name = "C:\\Users\\mert7\\Desktop\\Users\\" + textField_Email.getText().substring(0, index_f_email) + ".txt";
		
		File new_file = new File(file_name);
		if(new_file.exists()) { // there is file 
			return true; 
		}
		else {
			return false; // there is no file with this name
		}			
	}
	
	private void writeToFile(String file_name) {
		// Creating content
		String content = textField_Email.getText() + "    " + passwordField_Password.getPassword().toString() + "\n";
		try {
			FileWriter write_t_file = new FileWriter(file_name);
			write_t_file.write(content);
			write_t_file.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
