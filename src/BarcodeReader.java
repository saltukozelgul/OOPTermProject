import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import javax.swing.*;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;


public class BarcodeReader {
	
	// GUI OBjects
	JFrame frame = new JFrame("CheApp");
	JLabel label_BarcodeNumber =  new JLabel();
	JTextField textField_BarcodeNumber = new JTextField();
	JButton button_Search = new JButton();
	JButton button_Return = new JButton();
	
	
	// Variables
	private int isFound = 0;
	public String barcode = null;
	
	// getBarcode method
	public String getBarcode(Webcam webcam) {
		try {
			if (webcam.isOpen()) {
				BufferedImage bf = null;
				
				if ((bf = webcam.getImage()) == null) {
					// resim alýnmadýysa.
				}
				else {
					BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bf)));
					Result result = new MultiFormatReader().decode(bitmap);
					barcode = result.getText();
					// Bu barkodu kullanarak fiyatlar bulunup en ucuz fiyat ve market texte yazýlacak.

					isFound = 1;
					return barcode;
				}
			}

			
		} catch(Exception e) {
			System.out.println("Error while reading barcode " + e.getMessage());
		}
		return null;
	}
	
	// Constructor
	public BarcodeReader() {
		// Webcam settings
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());

		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(false);
		panel.setDisplayDebugInfo(false);
		panel.setImageSizeDisplayed(false);
		panel.setMirrored(false);
		
		// calls frame
		setFrameSettings(panel);
				
		Thread t1 = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	String barcode = null;
				while (isFound == 0) {
					barcode = getBarcode(webcam);
				}
				System.out.println(barcode);
				priceTaker.a101(barcode);
				frame.dispose();
				webcam.close();
				
		    }
		});  
		t1.start();
		
	} // end of constructor
	
	// Frame settings
	public void setFrameSettings(WebcamPanel panel) {
	
		Image icon_IMG = Toolkit.getDefaultToolkit().getImage(".\\\\resources\\\\Logo.jpeg"); // saves icon
		
		// Calls gradient
		Gradient gradient_panel = new Gradient(); // creates(calls) gradient panel
	
		// Frame settings
		JFrame frame = new JFrame();
		frame.setTitle("cheAPP");
		frame.setIconImage(icon_IMG); // changes icon
		frame.setSize(335, 525);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // exit when press to close button
		frame.setResizable(false); // closes size icon
		frame.setLocationRelativeTo(null); // opens the panel middle of the screen
		frame.getContentPane().setLayout(new GridLayout(0, 1));
		
		// Calls other GUI settings
		setLabelSettings();
		setButtonSettings();
		setTextFieldSettings();
		
		frame.add(gradient_panel); // adding gradient to the frame
		frame.add(panel);  // adding webcam to the frame
		
		// Adding GUI items to the gradient_panel
		gradient_panel.setLayout(null); // important!
		
		gradient_panel.add(label_BarcodeNumber);
		gradient_panel.add(textField_BarcodeNumber);
		gradient_panel.add(button_Return); gradient_panel.add(button_Search);
		
		
		frame.setVisible(true);

	}
	
	// Button settings
	public void setButtonSettings() {
		
		// Return button
		button_Return.setText("x");
		button_Return.setFont(new Font(Font.DIALOG, Font.PLAIN, 9));
		button_Return.setHorizontalTextPosition(SwingConstants.CENTER);
		button_Return.setBounds(10, 5, 35, 35);
		button_Return.setBackground(new Color(134,151,129));
		
		// Search button
		button_Search.setText("Search");
		button_Search.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
		button_Search.setHorizontalAlignment(SwingConstants.CENTER);
		button_Search.setBounds(240, 78, 70, 25);
		button_Search.setBackground(new Color(134, 151, 129));
	}
	
	// TextField settings
	public void setTextFieldSettings() {
		
		// Barcode number
		textField_BarcodeNumber.setText("Enter barcode number...");
		textField_BarcodeNumber.setFont(new Font(Font.DIALOG, Font.ITALIC, 11));
		textField_BarcodeNumber.setBounds(110, 48, 200, 25);
	}
	
	// Label settings
	public void setLabelSettings() {
		
		// Barcode number
		label_BarcodeNumber.setText("Enter barcode:");
		label_BarcodeNumber.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
		label_BarcodeNumber.setBounds(10, 50, 100, 20);
		label_BarcodeNumber.setForeground(Color.WHITE);
	}
	
} // end of BarcodeReader class

