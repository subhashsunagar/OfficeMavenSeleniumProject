package selenium.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SearchTest {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
	}
	
	@AfterMethod
	public void browserQuit() {
		driver.quit();
	}
	
	@Test(priority = 1)
	public void searchValidProduct() {
		
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("HP");
		driver.findElement(By.xpath("//div[@id='search']//button[@type='button']")).click();
		
		String hpProductText = driver.findElement(By.linkText("HP LP3065")).getText();
		Assert.assertTrue(hpProductText.equals("HP LP3065"));
		
	}
	
	@Test(priority = 2)
	public void searchInvalidProduct() {
		
		driver.findElement(By.xpath("//input[@name='search']")).sendKeys("ABCDEFG");
		driver.findElement(By.xpath("//div[@id='search']//button[@type='button']")).click();
	
		String invalidProductErrorText = driver.findElement(By.xpath("//p[text()='There is no product that matches the search criteria.']")).getText();
		Assert.assertTrue(invalidProductErrorText.contains("There is no product"));

	}
}
