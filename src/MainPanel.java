import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.Border;

class RoundBtn implements Border //we need this class for make rounded buttons
{
    private int r;
    RoundBtn(int r) {
        this.r = r;
    }
    public Insets getBorderInsets(Component c) {
        return new Insets(this.r+1, this.r+1, this.r+2, this.r);
    }
    public boolean isBorderOpaque() {
        return true;
    }
    public void paintBorder(Component c, Graphics g, int x, int y, 
    int width, int height) {
        g.drawRoundRect(x, y, width-1, height-1, r, r);
    }
}

public class MainPanel {
	
	JFrame frame = new JFrame();
	
	JButton button_Return = new JButton();
	JButton button_Basket = new JButton();
	JButton button_Barcode = new JButton();
	
	JLabel label_cheAPP = new JLabel("cheAPP", JLabel.CENTER);
	JLabel label_Welcome = new JLabel("Welcome!", JLabel.CENTER);
	
	Icon icon_Return = new ImageIcon("C:\\Users\\mert7\\Desktop\\image.png"); // saves icon
	
	public MainPanel() {
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
				Basket basket = new Basket();
				frame.dispose();
			}
		});
		
		button_Barcode.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				BarcodeReader barcode = new BarcodeReader();
				frame.dispose();
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
				
				frame.setVisible(true); // makes frame can be seen
	}

	private void setLabelSettings() {
		//label of cheAPP
		label_cheAPP.setText("cheAPP");
		label_cheAPP.setFont(new Font(Font.MONOSPACED , Font.BOLD, 60));
		label_cheAPP.setBounds(-40, 75, 400, 50);
		label_cheAPP.setForeground(new Color(134,151,129));
		
		// label of Welcome
		//label_Welcome.setText("Welcome");
		//label_Welcome.setFont(new Font(Font.MONOSPACED , Font.BOLD, 20));
		//label_Welcome.setBounds(100, 100, 100, 50);
	}

	private void setButtonSettings() 
	{
		//return button 
		button_Return.setText("x");
		button_Return.setFont(new Font(Font.DIALOG, Font.PLAIN, 8));
		button_Return.setHorizontalTextPosition(SwingConstants.CENTER);
		button_Return.setBounds(10, 10, 40, 40);
		button_Return.setBorder(new RoundBtn(360));
		button_Return.setBackground(new Color(134,151,129));
		button_Return.setOpaque(false);
		
		//basket button
		button_Basket.setText("BASKET");
		button_Basket.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
		button_Basket.setHorizontalTextPosition(SwingConstants.CENTER);
		button_Basket.setBounds(90, 250, 150, 60);
		button_Basket.setBorder(new RoundBtn(20));
		button_Basket.setBackground(new Color(134,151,129));
		button_Basket.setOpaque(false);
		
		//barcode button
		button_Barcode.setText("BARCODE");
		button_Barcode.setFont(new Font(Font.DIALOG, Font.PLAIN, 10));
		button_Barcode.setHorizontalTextPosition(SwingConstants.CENTER);
		button_Barcode.setBounds(100, 400, 140, 70);
		button_Barcode.setBorder(new RoundBtn(40));
		button_Barcode.setBackground(new Color(134,151,129));
		button_Barcode.setOpaque(false);
	}
}

