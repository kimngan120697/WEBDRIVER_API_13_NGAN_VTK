package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_03_Xpath {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver
	WebDriver driver;
	String firstName = "Automation";
	String lastName = "Testing";

	// Pre-condition
	@BeforeClass(description = "Chạy trước và 1 lần duy nhất cho tất cả các test bên dưới")
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@BeforeMethod(description = "Chạy trước cho mỗi test bên dưới")
	public void beforeMethod() {
		// 1. Truy cập trang
		driver.get("http://live.demoguru99.com/");

		// 2. Click vào link My Account để tới trang đăng nhập
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
	}

	// @Test
	public void TC_01_LoginWithEmptyEmailAndPassword() {

		// 3. Để trống Username & Password
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");

		// 4. Click Login button
		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();

		// 5. Verify Error message xuất hiện tại 2 field: This is required field.
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "This is a required field.");

		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-required-entry-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "This is a required field.");

	}

	// @Test
	public void TC_02_LoginWithInvalidEmail() {

		// 3. Nhập email invalid
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("1233343414@123123124");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("");

		// 4. Click Login button
		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();

		// 5. Verify Error message xuất hiện tại field:
		// Please enter a valid email address. For example johndoe@domain
		String emailErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-email-email']")).getText();
		Assert.assertEquals(emailErrorMsg, "Please enter a valid email address. For example johndoe@domain.com.");

	}

	// @Test
	public void TC_03_LoginWithPasswordLessThan6Character() {

		// 3. Nhập email correct and password incorrect: automation@gmail.com/123
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123");

		// 4. Click Login button
		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();

		// 5. Verify Error message xuất hiện:
		// Please enter 6 or more characters without leading or trailing spaces.
		String passwordErrorMsg = driver.findElement(By.xpath("//div[@id='advice-validate-password-pass']")).getText();
		Assert.assertEquals(passwordErrorMsg, "Please enter 6 or more characters without leading or trailing spaces.");
	}

	// @Test
	public void TC_04_LoginWithIncorrectPassword() {
		// 3. Nhập email correct and password incorrect: automation@gmail.com/123
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123123");

		// 4. Click Login button
		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();

		// 5. Verify Error message xuất hiện: Invalid login or password.
		String errorMsg = driver.findElement(By.xpath("//div[@class='account-login']//span")).getText();
		Assert.assertEquals(errorMsg, "Invalid login or password.");

	}

	@Test
	public void TC_05_LoginWithValidEmailAndPassword() {
		// 3. Nhập email correct and password correct: automation@gmail.com/123123
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys("automation@gmail.com");
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys("123123");

		// 4. Click Login button
		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();

		// 5. Verify các thông tin được hiển thị

		String content1 = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();
		Assert.assertEquals(content1, "MY DASHBOARD");

		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, "+firstName+" "+lastName+"!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p[contains(.,'automation@gmail.com')]")).isDisplayed());

	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

}