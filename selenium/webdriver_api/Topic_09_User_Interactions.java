package webdriver_api;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_09_User_Interactions {

	WebDriver driver;
	Actions action;

	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		
		FirefoxProfile profile=new FirefoxProfile(); //tắt notification of Firefox
		profile.setPreference("dom.webnotification.enabled", false); //tắt notification of Firefox
		driver=new FirefoxDriver(profile);
		action=new Actions(driver);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_HoverToElement() {
		
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Discover']"))).perform();
		driver.findElement(By.xpath("//a[text()='American Eagle']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title' and text()='American Eagle']")).isDisplayed());
	}

	@Test
	public void TC_02_ClickAndHoleElement() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		
	}

	@Test
	public void TC_03_NavigateFunction() {
		
		
	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
