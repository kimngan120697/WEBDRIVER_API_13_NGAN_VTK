package webdriver_api;

import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_16_Wait_Part_IV_Implicit_Explicit {

	WebDriver driver;
	WebDriverWait waitExplicit;

	By startButtonBy = By.xpath("//button[text()='Start']");
	By loadingImgBy = By.xpath("//div[@id='loading']//img");
	By textHelloBy = By.xpath("//h4[contains(text(),'Hello World!')]");

	// Pre-condition
	@BeforeClass
	public void beforeClass() {

		driver = new FirefoxDriver();

		// Rõ ràng: Chờ cho element theo từng trạng thái cụ thể
		waitExplicit = new WebDriverWait(driver, 15);

		// Ngầm định: Ko chờ cho element nào có trạng thái cụ thể => chỉ tập trung đi
		// tìm element
		driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_Implicit_Wait() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Check cho element được hiển thị (Visible)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());

		// Click vào Start button
		System.out.println("Start click:" + getCurrentTime());
		startButton.click();
		System.out.println("End click:" + getCurrentTime());

		// Loading icon hiển thị và mất tới 5s mới biến mất

		// Set lại 10s cho implicit wait (3s không còn ý nghĩa) - bị ghi đè
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		// Check cho Hello World is displayed
		System.out.println("Start hello world: " + getCurrentTime());
		WebElement textHello = driver.findElement(textHelloBy);
		System.out.println("End hello world: " + getCurrentTime());

		System.out.println("Start display:" + getCurrentTime());
		Assert.assertTrue(textHello.isDisplayed());
		System.out.println("End display: " + getCurrentTime());

	}

	public void TC_02_Implicit_Override() throws InterruptedException {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Check cho element được hiển thị (Visible)
		WebElement startButton = driver.findElement(startButtonBy);
		Assert.assertTrue(startButton.isDisplayed());

		// Click vào Start button
		System.out.println("Start click:" + getCurrentTime());
		startButton.click();
		System.out.println("End click:" + getCurrentTime());

		// Loading icon hiển thị và mất tới 5s mới biến mất

		// Check cho Hello World is displayed
		System.out.println("Start hello world: " + getCurrentTime());
		WebElement textHello = driver.findElement(textHelloBy);
		System.out.println("End hello world: " + getCurrentTime());

		System.out.println("Start display:" + getCurrentTime());
		Assert.assertTrue(textHello.isDisplayed());
		System.out.println("End display: " + getCurrentTime());
	}

	public void TC_03_Explicit_Visible() {
		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Click Start button
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();

		// Loading icon hiển thị và biến mất sau 5s

		// Wait cho helloword được visibile trước khi check displayed
		System.out.println("Start wait visible:" + getCurrentTime());
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(textHelloBy));
		System.out.println("End wait visible: " + getCurrentTime());

		System.out.println("Start displayed:" + getCurrentTime());
		Assert.assertTrue(driver.findElement(textHelloBy).isDisplayed());
		System.out.println("End displayed: " + getCurrentTime());

	}

	public void TC_04_Explicit_Invisible() {

		driver.get("http://the-internet.herokuapp.com/dynamic_loading/2");

		// Click Start button
		waitExplicit.until(ExpectedConditions.elementToBeClickable(startButtonBy));
		driver.findElement(startButtonBy).click();

		// Loading icon hiển thị và biến mất sau 5s
		System.out.println("Start wait invisible:" + getCurrentTime());
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(loadingImgBy));
		System.out.println("End wait invisible: " + getCurrentTime());

		// Wait cho helloword được visibile trước khi check displayed
		waitExplicit.until(ExpectedConditions.visibilityOfElementLocated(textHelloBy));
		System.out.println("End wait visible: " + getCurrentTime());

		System.out.println("Start displayed:" + getCurrentTime());
		Assert.assertTrue(driver.findElement(textHelloBy).isDisplayed());
		System.out.println("End displayed: " + getCurrentTime());

	}

	@Test
	public void TC_05_Date_Explicit() {
		driver.get("https://demos.telerik.com/aspnet-ajax/ajaxloadingpanel/functionality/explicit-show-hide/defaultcs.aspx");
		
		//In ra ngày được chọn: No Selected Dates to displayed.
		WebElement dateSelectedText=driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		System.out.print("Date selected: " +dateSelectedText.getText());
		
		//click vào current day
		driver.findElement(By.xpath("//a[text()='7']")).click();
		
		//Chờ cho loading icon biến mất
		waitExplicit.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@style,'position: absolute;')]/div[@class='raDiv']")));
		
		//Check current day = selected
		Assert.assertTrue(driver.findElement(By.xpath("//td[@class='rcSelected']//a[text()='22']")).isDisplayed());
		
		dateSelectedText=driver.findElement(By.id("ctl00_ContentPlaceholder1_Label1"));
		System.out.println("Date selected= "+ dateSelectedText.getText());
		Assert.assertEquals(dateSelectedText.getText(),"Sunday, March 22, 2020");
	}

	public String getCurrentTime() {
		Date date = new Date();
		return date.toString();
	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
