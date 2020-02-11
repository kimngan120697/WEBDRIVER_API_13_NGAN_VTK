package webdriver_api;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
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

	public void TC_01_HoverToElement() {
		
		driver.get("http://www.myntra.com/");
		action.moveToElement(driver.findElement(By.xpath("//div[@class='desktop-navLink']//a[text()='Discover']"))).perform();
		driver.findElement(By.xpath("//a[text()='American Eagle']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//h1[@class='title-title' and text()='American Eagle']")).isDisplayed());
	}

	public void TC_02_ClickAndHoleElement() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> numbers=driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int numbersSize= numbers.size();
		System.out.println("Size before click and hold: "+numbersSize);
		action.clickAndHold(numbers.get(0)).moveToElement(numbers.get(3)).release().perform();
		
		List <WebElement> selectedNumbers=driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		System.out.println("Size after click and hold: "+numbers.size());
		
		for(WebElement number:selectedNumbers)
		{
			System.out.println(number.getText());
		}
		Assert.assertEquals(selectedNumbers.size(), 4);
	}

	public void TC_03_ClickAndSelectElement() {
		driver.get("https://jqueryui.com/resources/demos/selectable/display-grid.html");
		List <WebElement> numbers=driver.findElements(By.xpath("//ol[@id='selectable']/li"));
		int numbersSize= numbers.size();
		System.out.println("Size before click and hold: "+numbersSize);
		
		action.keyDown(Keys.CONTROL).perform();
		action.click(numbers.get(0)).click(numbers.get(2)).click(numbers.get(5)).click(numbers.get(10)).perform();
		action.keyUp(Keys.CONTROL).perform();
		
		List <WebElement> selectedNumbers=driver.findElements(By.xpath("//li[@class='ui-state-default ui-selectee ui-selected']"));
		System.out.println("Size after click and hold: "+numbers.size());
		
		for(WebElement number:selectedNumbers)
		{
			System.out.println(number.getText());
		}
		Assert.assertEquals(selectedNumbers.size(), 4);
		
	}
	
	@Test
	public void TC_04_DoubleClick() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		action.doubleClick(driver.findElement(By.xpath("//button[text()='Double click me']"))).perform();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='demo']")).getText(), "Hello Automation Guys!");
		
	}
	@Test
	public void TC_05_RightClickToElement() {
		
		
	}
	@Test
	public void TC_06_DragAnDropElement() {
		
		
	}
	@Test
	public void TC_07_DragAnDropHTML5() {
		
		
	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
