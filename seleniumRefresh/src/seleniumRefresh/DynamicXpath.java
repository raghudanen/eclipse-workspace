package seleniumRefresh;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class DynamicXpath {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		System.setProperty("webdriver.chrome.driver","c:/chromedriver.exe");
		WebDriver driver  = new ChromeDriver();
		/*
		 * driver.get("http://yahoo.com"); driver.manage().window().maximize();
		 * //driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		 * driver.findElement(By.xpath("//input[@id='header-search-input']")).sendKeys(
		 * "Selenium");
		 * //driver.findElement(By.xpath("//span[contains(text(),'tutorial')]")).click()
		 * ;
		 */		
		driver.get("https://www.spicejet.com/");
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
		Select S = new Select(driver.findElement(By.id("ctl00_mainContent_DropDownListCurrency")));
		S.selectByValue("AED");
		driver.findElement(By.id("ctl00_mainContent_ddl_originStation1_CTXT")).click();
		driver.findElement(By.xpath("//a[@value = 'MAA']")).click();
		Thread.sleep(2000);
		//driver.findElement(By.xpath("(//a[@value = 'BLR'])[2]")).click();
		driver.findElement(By.xpath("//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value = 'BLR']"))
	.click();	
		driver.findElement(By.xpath("//div[@id='divpaxinfo']")).click();
		Select S2  = new Select(driver.findElement(By.xpath("//select[@id='ctl00_mainContent_ddl_Adult']") ));
		S2.selectByValue("2");
		
		driver.findElement(By.xpath("//input[@id='ctl00_mainContent_chk_friendsandfamily']")).click();

		////input[@id='header-search-input']
		////span[contains(text(),'tutorial')]
		//div[@id='glsctl00_mainContent_ddl_destinationStation1_CTNR'] //a[@value = 'BLR']
	}
}
