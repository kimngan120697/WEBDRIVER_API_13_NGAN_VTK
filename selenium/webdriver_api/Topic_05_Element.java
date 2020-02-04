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
	By ageUnder18RadioButtonBy=By.xpath("//input[@id='under_18']");
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
	public void TC_03_Selected() {
		
		
		System.out.println("01.Truy cập trang");
		
		driver.get("https://automationfc.github.io/basic-form/index.html");
		driver.navigate().refresh();
		
		
		System.out.println("02.Click chọn");
		clickToElement(ageUnder18RadioButtonBy);
		clickToElement(developmentCheckboxBy);
		
		System.out.println("03.Check elements selected at step 02");
		Assert.assertTrue(isElementSelected(ageUnder18RadioButtonBy));
		Assert.assertTrue(isElementSelected(developmentCheckboxBy));
		
		System.out.println("04.Click non-selected Interests (Development) checkbox");
		clickToElement(developmentCheckboxBy);
		
		System.out.println("05.Check elements selected at step 05");
		Assert.assertTrue(isElementSelected(ageUnder18RadioButtonBy));
		Assert.assertFalse(isElementSelected(developmentCheckboxBy));
		
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
