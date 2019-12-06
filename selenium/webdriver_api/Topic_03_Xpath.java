package webdriver_api;

import java.util.Random;
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
	String validEmail = "automation" + randomNumber() + "@gmail.com";
	String validPassword = "123123";

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
		String errorMsg = driver.findElement(By.xpath("//li[@class='error-msg']//span")).getText();
		Assert.assertEquals(errorMsg, "Invalid login or password.");

	}

	@Test
	// Create a new account
	public void TC_05_CreateNewAccount() {
		// 1. Truy cập trang http://live.demoguru99.com
		// 2. Click vào link My Account để tới trang đăng nhập

		// 3. Click CREATE AN ACCOUNT button để tới trang đăng kí tài khoản
		driver.findElement(By.xpath("//span[text()='Create an Account']")).click();
		
		// 4. Nhập thông tin hợp lệ vào tất cả các field: FirstName/Lastname/Email Address/Password/Confirm Password
		driver.findElement(By.xpath("//input[@id='firstname']")).sendKeys(firstName);
		driver.findElement(By.xpath("//input[@id='lastname']")).sendKeys(lastName);
		driver.findElement(By.xpath("//input[@id='email_address']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='password']")).sendKeys(validPassword);
		driver.findElement(By.xpath("//input[@id='confirmation']")).sendKeys(validPassword);
		
		//05. Click REGISTER button
		driver.findElement(By.xpath("//button[@title='Register']")).click();

		
		//06. Verify message xuất hiện khi đăng ký thành công: Thank you for registering with Main Website Store.
		Assert.assertTrue(driver.findElement(By.xpath("//li[@class='success-msg']//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		
		String content1 = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();
		Assert.assertEquals(content1, "MY DASHBOARD");
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p[contains(.,'" + validEmail + "')]")).isDisplayed());
		
		//07. Logout khỏi hệ thống
		driver.findElement(By.xpath("//div[@class='account-cart-wrapper']//span[text()='Account']")).click();
		driver.findElement(By.xpath("//a[@title='Log Out']")).click();
		
		
		//08. Kiểm tra hệ thống navigate về Home page sau khi logout thành công.
	}

	@Test
	public void TC_06_LoginWithValidEmailAndPassword() {
		// 3. Nhập email correct and password correct: automation@gmail.com/123123
		driver.findElement(By.xpath("//input[@id='email']")).sendKeys(validEmail);
		driver.findElement(By.xpath("//input[@id='pass']")).sendKeys(validPassword);
		// 4. Click Login button
		driver.findElement(By.xpath("//button[@id=\"send2\"]")).click();

		// 5. Verify các thông tin được hiển thị
		// Cách 1: Dùng hàm assertTrue(điều kiện) -> locator được hiển thị (isDisplayed)
		Assert.assertTrue(driver.findElement(By.xpath("//strong[text()='Hello, " + firstName + " " + lastName + "!']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p[contains(text(),'" + firstName + " " + lastName + "')]")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='col-1']//p[contains(.,'" + validEmail + "')]")).isDisplayed());

		// Cách 2: Dùng hàm assertEquals (điều kiện 1, điều kiện 2) -> getText
		String content1 = driver.findElement(By.xpath("//div[@class='page-title']//h1")).getText();
		Assert.assertEquals(content1, "MY DASHBOARD");

	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

	public int randomNumber() {
		Random rand = new Random();
		int number = rand.nextInt(100);
		return number;
	}

}
