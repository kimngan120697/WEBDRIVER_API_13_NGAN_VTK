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

public class Topic_04_Browser {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver
	WebDriver driver;

	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_VerifyUrl() {

		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/");
		
		System.out.println("Step 02 - Click 'My Account' link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		System.out.println("Step 03 - Verify 'Login Page' Url ");
		String loginPageUrl=driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		System.out.println("Step 04 - Click 'CREATE AN ACCOUNT' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 05 - Verify 'Register Page' Url");
		String registerPageUrl=driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");

	}

	@Test
	public void TC_02_Verify() {
		
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/");
		
		System.out.println("Step 02 - Click 'My Account' link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		System.out.println("Step 03 - Verify title của Login Page = Customer Login ");
		String titleLoginPage=driver.getTitle();
		Assert.assertEquals(titleLoginPage, "Customer Login");
		
		System.out.println("Step 04 - Click 'CREATE AN ACCOUNT' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();
		
		System.out.println("Step 05 - Verify title của 'Register Page'= Create New Customer Account");
		String titleRegiserPage=driver.getTitle();
		Assert.assertEquals(titleRegiserPage, "Create New Customer Account");
	}

	@Test
	public void TC_03_NavigateFunction() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/");
		
		System.out.println("Step 02 - Click 'My Account' link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();
		
		System.out.println("Step 03 - Click 'CREATE AN ACCOUNT' button");
		driver.findElement(By.xpath("//a[@title='Create an Account']")).click();

		System.out.println("Step 04 - Verify url của Register Page");
		String registerPageUrl=driver.getCurrentUrl();
		Assert.assertEquals(registerPageUrl, "http://live.demoguru99.com/index.php/customer/account/create/");
		
		System.out.println("Step 05 - Back Login Page");
		driver.navigate().back();
		
		System.out.println("Step 06 - Verify Url of Login Page");
		String loginPageUrl=driver.getCurrentUrl();
		Assert.assertEquals(loginPageUrl, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		System.out.println("Step 07 - Forward tới trang Register Page");
		driver.navigate().forward();
		
		System.out.println("Step 08 - Verify title của Register Page= Create New Custmer Account");
		String registerPageTitle=driver.getTitle();
		Assert.assertEquals(registerPageTitle, "Create New Customer Account");
		
	}

	@Test
	public void TC_04_GetPageSourceCode() {
		System.out.println("Step 01 - Open Url");
		driver.get("http://live.demoguru99.com/");
		
		System.out.println("Step 02 - Click 'My Account' link");
		driver.findElement(By.xpath("//div[@class='footer']//a[text()='My Account']")).click();

		System.out.println("Step 02 - Verify Login Page chứa text: Login or Create an Account");
		Assert.assertTrue(driver.getPageSource().contains("Create an Account"));
	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
