package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_15_16_WebDriver_Wait_I {

	WebDriver driver;
	List<WebElement> elements;
	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@Test
	public void TC_01_findElement() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");

		// Case 1: Không tìm thấy element nào hết
		// driver.findElement(By.xpath("//input[@id='id_order']")).sendKeys("automation@gmail.com");
		// Nếu như đang còn tìm mà chưa hết timeout - element nó xuất hiện thì vẫn pass
		// Luôn tìm theo element theo chu kì là 0.5s tìm lại 1 lần cho hết timeout của
		// implicit
		// Kết quả failed và throw exception: No such element

		// Case 2: Tìm thấy duy nhất 1 element (node)
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");

		// Case 3: Tìm thấy nhiều hơn 1 element (>=2) => Thao tác với element đầu tiên
		driver.findElement(By.xpath("//button[@type='submit']")).click();
	}

	@Test
	public void TC_02_findElements() throws InterruptedException {
		driver.get("http://automationpractice.com/index.php?controller=authentication&back=my-account");
		
		// Case 1: Không tìm thấy element nào hết
		elements = driver.findElements(By.xpath("//input[@id='id_order']"));
		// Nếu như đang còn tìm mà chưa hết timeout - element nó xuất hiện thì vẫn pass
		// Luôn tìm theo element theo chu kì là 0.5s tìm lại 1 lần cho hết timeout của implicit
		// Kết quả: Không đánh fail testcase mà trả về empty list (list rỗng không có phần tử nào hết)
		System.out.println("Size of list: "+ elements.size());
		Assert.assertTrue(elements.isEmpty());
		Assert.assertEquals(elements.size(), 0);

		// Case 2: Tìm thấy duy nhất 1 element (node)
		elements=driver.findElements(By.xpath("//input[@id='email']"));
		System.out.println("Size of list: "+ elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(), 1);
		elements.get(0).sendKeys("abcd123456");;
		
		// Case 3: Tìm thấy nhiều hơn 1 element (>=2) => Thao tác với element đầu tiên
		elements=driver.findElements(By.xpath("//button[@type='submit']"));
		System.out.println("Size of list: "+ elements.size());
		Assert.assertFalse(elements.isEmpty());
		Assert.assertEquals(elements.size(), 4);
	}
	
	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
