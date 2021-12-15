
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class priceTaker {
	//snt
	//mis
	//mis
	
	// Functions
	
	public static void a101(String barcode) {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		WebDriver driver = new ChromeDriver(chromeOptions);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://www.a101.com.tr/");
		driver.findElement(By.xpath("/html/body/div[1]/div/div[2]/button[2]")).click();
		WebElement searchBar = driver.findElement(By.name("search_text"));
		searchBar.sendKeys(barcode);
		driver.findElement(By.xpath("/html/body/section/header/div/div[2]/div[3]/form/button")).click();
		String name = driver.findElement(By.className("name")).getText();
		String price = driver.findElement(By.className("current")).getText();
		System.out.println(name +": " + price);
		driver.close();
	}
	
	public static void carrefour(String barcode) {
		
	}
	
	public static void amazon(String barcode) {
		
	}
	
	public static void trendyol(String barcode) {
		
	}
	
	public static void hepsoburada(String barcode) {
		
	}
	
}
