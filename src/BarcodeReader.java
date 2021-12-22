import java.awt.image.BufferedImage;
import javax.swing.JFrame;

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;
import com.github.sarxos.webcam.WebcamResolution;

import com.google.zxing.BinaryBitmap;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.HybridBinarizer;

public class BarcodeReader {
	private int isFound = 0;
	public String barcode = null;
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
	
	public BarcodeReader() {
		Webcam webcam = Webcam.getDefault();
		webcam.setViewSize(WebcamResolution.VGA.getSize());

		WebcamPanel panel = new WebcamPanel(webcam);
		panel.setFPSDisplayed(false);
		panel.setDisplayDebugInfo(false);
		panel.setImageSizeDisplayed(false);
		panel.setMirrored(false);

		JFrame window = new JFrame("Barcode Reader");
		window.add(panel);
		window.setResizable(true);
		window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		window.pack();
		window.setVisible(true);
		
		Thread t1 = new Thread(new Runnable() {
		    @Override
		    public void run() {
		    	String barcode = null;
				while (isFound == 0) {
					barcode = getBarcode(webcam);
				}
				System.out.println(barcode);
				priceTaker.a101(barcode);
				window.dispose();
				webcam.close();
				
		    }
		});  
		t1.start();
		
	}
}
