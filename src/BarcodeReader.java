import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.util.ArrayList;

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
	
	private ArrayList<ArrayList<String>> infos = new ArrayList<ArrayList<String>>();
	private ArrayList<String> minProduct;
	
	// GUI OBjects
	private JFrame frame = new JFrame("CheApp");
	private JLabel label_BarcodeNumber =  new JLabel();
	private JTextField textField_BarcodeNumber = new JTextField();
	private JButton button_Search = new JButton();
	private JButton button_Return = new JButton();
	
	private JButton button_add = new JButton();
	private JButton button_dontadd = new JButton();
	private JLabel label_product = new JLabel();
	private JLabel label_product1 =  new JLabel();
	private JLabel location_product = new JLabel();
	
	JLabel label_Separator = new JLabel();
	
	// Variables
	private int isFound = 0;
	public String barcode = null;
	
	// getBarcode method
	public String getBarcode(Webcam webcam) {
		try {
			if (webcam.isOpen()) {
				BufferedImage bf = null;
				
				if ((bf = webcam.getImage()) == null) {
					// resim al�nmad�ysa.
				}
				else {
					BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(new BufferedImageLuminanceSource(bf)));
					Result result = new MultiFormatReader().decode(bitmap);
					barcode = result.getText();
					// Bu barkodu kullanarak fiyatlar bulunup en ucuz fiyat ve market texte yaz�lacak.
					isFound = 1;
					return barcode;
				}
			}

			
		} 
		catch(Exception e) {
			System.out.println("Error while reading barcode " + e.getMessage());
		}
		return null;
	}
	
	public void getMinumum() {
		
		float minPrice = 100000;
		for (ArrayList<String> price : infos) {
			if (Float.parseFloat(price.get(2)) < minPrice) {
				minPrice = Float.parseFloat(price.get(2));
				minProduct = price;
			}
 			
		}
		
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
				infos.add(priceTaker.a101(barcode));
				infos.add(priceTaker.carrefour(barcode));
				
				String type = infos.get(0).get(3);
				System.out.println(type);
				
				getMinumum();
				
				System.out.println(minProduct);
				location_product.setText(minProduct.get(0));
				label_product.setText(minProduct.get(1) + " -> " + minProduct.get(2));
				label_product1.setText("Would you add it to basket?");
				button_add.setVisible(true); button_dontadd.setVisible(true); label_product.setVisible(true); label_product1.setVisible(true); location_product.setVisible(true);
				webcam.close();
				
		    }
		});  
		t1.start();
		
		button_Return.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				t1.interrupt();
				webcam.close();
				MainPanel MP = new MainPanel();
				frame.dispose();
			}
		});
		
		button_add.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				//ve ekleme i�lemleri
				// Burada txt yazma i�lemleri yap�lacak
				button_add.setVisible(false); button_dontadd.setVisible(false); label_product.setVisible(false); label_product1.setVisible(false);
				frame.dispose();
				BarcodeReader BR = new BarcodeReader();
			}
		});
		
		button_dontadd.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				button_add.setVisible(false); button_dontadd.setVisible(false); label_product.setVisible(false); label_product1.setVisible(false);
				frame.dispose();
				BarcodeReader BR = new BarcodeReader();
			}
		});
		
		button_Search.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if(textField_BarcodeNumber.getText() != null) {
					webcam.close();
					String barcode = textField_BarcodeNumber.getText();
					
					System.out.println(barcode);
					infos.add(priceTaker.a101(barcode));
					infos.add(priceTaker.carrefour(barcode));
					
					String type = infos.get(0).get(3);
					System.out.println(type);
					getMinumum();
					
					System.out.println(minProduct);
					location_product.setText(minProduct.get(0));
					label_product.setText(minProduct.get(1) + " -> " + minProduct.get(2));
					label_product1.setText("Would you add it to basket?");
					button_add.setVisible(true); button_dontadd.setVisible(true); label_product.setVisible(true); label_product1.setVisible(true); location_product.setVisible(true);
				}
			}
		});
		
	} // end of constructor
	
	// Frame settings
	public void setFrameSettings(WebcamPanel panel) {
	
		Image icon_IMG = Toolkit.getDefaultToolkit().getImage(".\\resources\\Logo.jpeg"); // saves icon
		
		
		// Calls gradient
		Gradient gradient_panel = new Gradient(); // creates(calls) gradient panel
	
		// Frame settings
		//JFrame frame = new JFrame();
		frame.setTitle("cheAPP");
		frame.setIconImage(icon_IMG); // changes icon
		frame.setSize(335, 525);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // exit when press to close button
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
		gradient_panel.add(label_Separator);
		gradient_panel.add(button_add); gradient_panel.add(button_dontadd); gradient_panel.add(label_product); gradient_panel.add(label_product1);
		gradient_panel.add(location_product);
	
		
		button_add.setVisible(false); button_dontadd.setVisible(false); label_product.setVisible(false); label_product.setVisible(false); ; location_product.setVisible(false);
		
		
		frame.setVisible(true);

	}
	
	// Button settings
	public void setButtonSettings() {
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
		
		// Search button
		button_Search.setText("Search");
		button_Search.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
		button_Search.setHorizontalAlignment(SwingConstants.CENTER);
		button_Search.setBounds(240, 78, 70, 25);
		button_Search.setBackground(new Color(134, 151, 129));
		
		//add button
		button_add.setText("Add to Basket");
		button_add.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
		button_add.setHorizontalAlignment(SwingConstants.CENTER);
		button_add.setBounds(4, 200, 150, 25);
		button_add.setBackground(new Color(134, 151, 129));
		
		//don't add button
		button_dontadd.setText("Scan Again");
		button_dontadd.setFont(new Font(Font.DIALOG, Font.PLAIN, 11));
		button_dontadd.setHorizontalAlignment(SwingConstants.CENTER);
		button_dontadd.setBounds(165, 200, 150, 25);
		button_dontadd.setBackground(new Color(134, 151, 129));
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
		label_BarcodeNumber.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
		label_BarcodeNumber.setBounds(10, 50, 100, 20);
		label_BarcodeNumber.setForeground(Color.WHITE);
		
		//product name/market
		label_product.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
		label_product.setBounds(4, 150, 300, 40);
		label_product.setForeground(Color.WHITE);
		
		label_product1.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
		label_product1.setBounds(4, 165, 300, 40);
		label_product1.setForeground(Color.WHITE);
		
		location_product.setFont(new Font(Font.DIALOG, Font.PLAIN, 13));
		location_product.setBounds(4, 135, 300, 40);
		location_product.setForeground(Color.WHITE);
		
		
		//Separator
		label_Separator.setText(" - - - - - - - - - - - or you can scan - - - - - - - - - - ");
		label_Separator.setFont(new Font(Font.DIALOG, Font.PLAIN, 14));
		label_Separator.setBounds(7, 120, 300, 20);
		label_Separator.setForeground(new Color(134, 151, 129));
	}
	
} // end of BarcodeReader class

