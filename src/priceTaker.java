import java.time.Duration;
import java.util.ArrayList;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class priceTaker {
	
	// Functions
	
	private static WebDriver createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		return driver;
	}
	
	public static ArrayList<String> a101(String barcode) {
		
		ArrayList<String> info = new ArrayList<String>();
		
		WebDriver driver = createDriver();
		
		driver.navigate().to("https://www.a101.com.tr/list/?search_text="+barcode);
		
		String name = driver.findElement(By.className("name")).getText();
		String price = driver.findElement(By.className("current")).getText();
		
		info.add("A101"); info.add(name); info.add(price); 
		
		System.out.print(info);
		driver.quit();
		return info;
	}
	
	public static ArrayList<String> carrefour(String barcode) {
		
		ArrayList<String> info = new ArrayList<String>();
		
		WebDriver driver = createDriver();
		
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://www.carrefoursa.com/search/?text="+barcode);
		
		String price = driver.findElement(By.className("price-cont")).getText();
		String name = driver.findElement(By.className("item-name")).getText();
		
		info.add("CARREFOUR"); info.add(name); info.add(price); 
		
		System.out.println(info);
		
		driver.quit();
		
		return info;
	}
	
	public static void amazon(String barcode) {
	
	}
	
	public static ArrayList<String> trendyol(String barcode) {
		
		ArrayList<String> info = new ArrayList<String>();
		
		WebDriver driver = createDriver();
		
		driver.navigate().to("https://www.trendyol.com/sr?q="+barcode);
		
		String brand = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[3]/div/div/div[1]/a/div[2]/div[1]/div/span[1]")).getText();
		String name = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[3]/div/div/div[1]/a/div[2]/div[1]/div/span[2]")).getText();
		String price = driver.findElement(By.xpath("/html/body/div[1]/div[3]/div[2]/div[2]/div/div/div/div[1]/div[2]/div[3]/div/div/div[1]/a/div[2]/div[3]/div/div/div[3]/div[2]")).getText();
		
		info.add("TRENDYOL"); info.add(brand); info.add(name); info.add(price); 
		
		System.out.print(info);
		
		driver.quit();
		
		return info;
	}
	
	public static void hepsiburada(String barcode) {
		
	}
	
}
