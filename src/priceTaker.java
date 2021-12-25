
import java.time.Duration;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class priceTaker {
	//snt
	//mis
	//mis
	
	// Functions
	
	private static WebDriver createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\ChromeDriver\\chromedriver.exe");
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.setPageLoadStrategy(PageLoadStrategy.NORMAL);
		WebDriver driver = new ChromeDriver(chromeOptions);
		return driver;
	}
	
	public static void a101(String barcode) {
		WebDriver driver = createDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://www.a101.com.tr/");
		driver.findElement(By.id("CybotCookiebotDialogBodyLevelButtonLevelOptinAllowAll")).click();
		WebElement searchBar = driver.findElement(By.name("search_text"));
		searchBar.sendKeys(barcode);
		driver.findElement(By.xpath("/html/body/section/header/div/div[2]/div[3]/form/button")).click();
		String name = driver.findElement(By.className("name")).getText();
		String price = driver.findElement(By.className("current")).getText();
		System.out.println(name +": " + price);
		driver.quit();
	}
	
	public static void carrefour(String barcode) {
		WebDriver driver = createDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.navigate().to("https://www.carrefoursa.com/");
		driver.findElement(By.xpath("//*[@id=\"js-site-search-input\"]")).sendKeys(barcode); // Search bar
		driver.findElement(By.xpath("/html/body/main/header/div[1]/div[3]/div/div/div[6]/div/form/div/span/button")).click(); // Search button
		String price = driver.findElement(By.className("price-cont")).getText();
		String name = driver.findElement(By.className("item-name")).getText();
		System.out.println(name +": " + price);
		driver.quit();
		
	}
	
	public static void amazon(String barcode) {
		
	}
	
	public static void trendyol(String barcode) {
		
	}
	
	public static void hepsiburada(String barcode) {
		
	}
	
}
