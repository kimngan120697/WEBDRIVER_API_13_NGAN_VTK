package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_11_12_Popup_Iframe_Window {

	WebDriver driver;
	
	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_displayed() {
		
		
	}

	@Test
	public void TC_02_Enabled_Disabled() {
		
		
	}

	@Test
	public void TC_03_Selected() {
		
		
		
	}

	public void clickToElement(By by) {
		WebElement element=driver.findElement(by);
		element.click();
	}
	public boolean isElementDisplayed(By by)
	{
		WebElement element=driver.findElement(by);
		if(element.isDisplayed())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	
	public boolean isElementEnabled(By by) {
		WebElement element=driver.findElement(by);
		if(element.isEnabled())
		{
			System.out.println("Element ["+by+"] is enabled");
			return true;
		}
		else
		{
			System.out.println("Element ["+by+"] is disabled");
			return false;
		}
	}
	
	public boolean isElementSelected(By by) {
		WebElement element=driver.findElement(by);
		if(element.isSelected())
		{
			System.out.println("Element ["+by+"] is selected");
			return true;
		}
		else
		{
			System.out.println("Element ["+by+"] is unselected");
			return false;
		}
	}
	

	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}
	

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
