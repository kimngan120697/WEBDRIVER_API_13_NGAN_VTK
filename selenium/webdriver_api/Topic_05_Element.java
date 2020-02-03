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

public class Topic_05_Element {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver
	WebDriver driver;
	By emailTextboxBy=By.xpath("//input[@id='mail']");
	By ageUnder18RadioButtonBy=By.xpath("//label[contains(text(),'Under 18')]");
	By educationTextAreaBy=By.xpath("//textarea[@id='edu']");
	By jobRole01By=By.xpath("//select[@id='job1']");
	By developmentCheckboxBy=By.xpath("//input[@id='development']");
	By slider01By=By.xpath("//input[@id='slider-1']");
	By passwordTextboxBy=By.xpath("//input[@id='password']");
	By radioBtnDisabledBy=By.xpath("//input[@id='radio-disabled']");
	By biographyTextAreaBy=By.xpath("//textarea[@id='bio']");
	By slider02By= By.xpath("//input[@id='slider-2']");
	
	
	
	
	
	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_displayed() {
		
		System.out.println("Step 01 - Open Url");
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		System.out.println("Step 02 - Check element is displayed: Emai, Age(Under 18), Education");
		if(isElementDisplayed(emailTextboxBy))
		{
			sendkeyToElement(emailTextboxBy,"Automation Testing");
		}
		
		if(isElementDisplayed(ageUnder18RadioButtonBy))
		{
			clickToElement(ageUnder18RadioButtonBy);
		}
		
		if(isElementDisplayed(educationTextAreaBy))
		{
			sendkeyToElement(educationTextAreaBy,"Automation Testing");
		}
	}

	@Test
	public void TC_02_Enabled_Disabled() {
		System.out.println("Truy cập trang");
		driver.get("https://automationfc.github.io/basic-form/index.html");
				
		Assert.assertTrue(isElementEnabled(emailTextboxBy));
		Assert.assertTrue(isElementEnabled(ageUnder18RadioButtonBy));
		Assert.assertTrue(isElementEnabled(educationTextAreaBy));
		Assert.assertTrue(isElementEnabled(developmentCheckboxBy));
		Assert.assertTrue(isElementEnabled(jobRole01By));
		Assert.assertTrue(isElementEnabled(slider01By));

		Assert.assertFalse(isElementEnabled(passwordTextboxBy));
		Assert.assertFalse(isElementEnabled(radioBtnDisabledBy));
		Assert.assertFalse(isElementEnabled(biographyTextAreaBy));
		Assert.assertFalse(isElementEnabled(slider02By));
		
		
	}

	@Test
	public void TC_03_NavigateFunction() {
		
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
	
	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}
	
	public void clickToElement(By by) {
		WebElement element=driver.findElement(by);
		element.click();
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
	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
