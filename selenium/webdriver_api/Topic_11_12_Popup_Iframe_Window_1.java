package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_11_12_Popup_Iframe_Window_1 {

	WebDriver driver;
	WebDriverWait waitExplicit;
	
	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit=new WebDriverWait(driver,10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_displayed() throws InterruptedException {
		driver.get("https://kyna.vn/");
		
		List <WebElement> fancyPopup=driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		System.out.println("Fancybox popup is displayed: " + fancyPopup.size());
		if (fancyPopup.size()>0)
		{
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}
		
		//Switch vào iframe trước thì mới tương tác với các element trong đó
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,\"facebook.com\")]")));
		
		boolean facebookIframe=driver.findElement(By.xpath("//a[text()='Kyna.vn']")).isDisplayed();
		System.out.println("Facebook Iframe is displayed: " +facebookIframe);
		Assert.assertTrue(facebookIframe);
		
		String likesFacebook=driver.findElement(By.xpath("//a[text()='Kyna.vn']/parent::div/following-sibling::div")).getText();
		System.out.println("Facebook like number="+likesFacebook);
		Assert.assertEquals(likesFacebook, "170K likes");
		
		//Switch về main page lại
		driver.switchTo().defaultContent();
		Thread.sleep(3000);
		
		driver.findElement(By.className("button-login")).click();
		driver.findElement(By.id("user-login")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.id("user-password")).sendKeys("automationfc.vn@gmail.com");
		Thread.sleep(3000);
		driver.findElement(By.id("btn-submit-login")).click();
		
		Assert.assertTrue(driver.findElement(By.xpath("//span[@class='user' and text()='Automation FC']")).isDisplayed());
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
