package selenium.testcases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class LoginTest {

	WebDriver driver;
	
	@BeforeMethod
	public void setUp() {
		driver = new ChromeDriver();
		driver.get("https://tutorialsninja.com/demo/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		
		driver.findElement(By.xpath("//span[text()='My Account']")).click();
		driver.findElement(By.linkText("Login")).click();
	}
	
	@AfterMethod
	public void browserQuit() {
		driver.quit();
	}
	
	
	@Test(priority = 1)
	public void loginTestWithVlaidCredentials() throws Throwable {

		Thread.sleep(2000);
		
		
		driver.findElement(By.id("input-email")).sendKeys("subbutop5@gmail.com");
		driver.findElement(By.id("input-password")).sendKeys("Password@123");
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		//Validate (Testing)
		String homePageText = driver.findElement(By.linkText("Edit your account information")).getText();
		Assert.assertTrue(homePageText.contains("Edit your account information"));
		
		
	}

	@Test(priority = 2)
	public void loginTestWithInvalidCredentials() throws Throwable {

		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//input[@type='submit']")).click();
		
		Thread.sleep(2000);

		String loginErrorText = driver.findElement(By.xpath("//*[contains(text(),'Warning: No match')]")).getText();
		Assert.assertTrue(loginErrorText.contains("Warning: No match"));
		
		if(loginErrorText.contains("Warning: No match")) {
			System.out.println("Verification is successful for Invalid credentials");
		}
		else{
			System.out.println("Verification failed for Invalid credentials");
		}	
	}
	
}
