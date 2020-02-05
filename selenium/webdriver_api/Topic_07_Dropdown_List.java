package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_List {
	WebDriver driver;
	Select select;
	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
	public void TC_01_Dropdown_List() {
		System.out.println("01. Truy cập trang");
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		select= new Select(driver.findElement(By.xpath("//select[@id='job1']")));
		
		System.out.println("02. Kiểm tra dropdown Job Role 1 - Single Dropdown - không hỗ trợ thuộc tính multiple select");
		Assert.assertFalse(select.isMultiple());
		
		System.out.println("03. Chọn giá trị Mobile Testing trong dropdown bằng phương thức selectByVisibleText");
		select.selectByVisibleText("Mobile Testing");
		
		System.out.println("04. Kiểm tra giá trị đã được chọn thành công");
		Assert.assertEquals(select.getFirstSelectedOption().getText(),"Mobile Testing");

		System.out.println("05. Chọn giá trị Manual Testing trong dropdown bằng phương thức selectByValue");
		select.selectByValue("manual");
		
		System.out.println("06. Kiểm tra giá trị đã được chọn thành công");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Manual Testing");
		
		System.out.println("07. Chọn giá trị Functional UI Testing trong dropdown bằng phương thức selectIndex");
		select.selectByIndex(9);
		
		System.out.println("08. Kiểm tra giá trị đã được chọn thành công");
		Assert.assertEquals(select.getFirstSelectedOption().getText(), "Functional UI Testing");
		
		System.out.println("09. Kiểm tra dropdown có đủ 10 giá trị");
		Assert.assertEquals(select.getOptions().size(),10);
		
		System.out.println("10. Kiểm tra dropdown Job Role 2 - Multiple dropdown có hỗ trợ thuộc tính multiple select");
		select= new Select(driver.findElement(By.xpath("//select[@id='job2']")));
		Assert.assertTrue(select.isMultiple());
		
		System.out.println("11. Chọn nhiều giá trị cùng một lúc: Automation, Mobile, Desktop");
		select.selectByVisibleText("Automation");
		select.selectByVisibleText("Mobile");
		select.selectByVisibleText("Desktop");
		
		System.out.println("12. Kiểm tra 3 giá trị được chọn thành công");
		List <WebElement> optionSelected= select.getAllSelectedOptions();
		List <String> arraySelected=new ArrayList<String>();
		for(WebElement select:optionSelected)
		{
			System.out.println(select.getText());
			arraySelected.add(select.getText());
		}
		Assert.assertTrue(arraySelected.contains("Automation"));
		Assert.assertTrue(arraySelected.contains("Mobile"));
		Assert.assertTrue(arraySelected.contains("Desktop"));
		
		System.out.println("13. De-select 3 giá trị cùng một lúc");
		select.deselectAll();
		
		System.out.println("14. Kiểm tra không còn giá trị nào được chọn");
		List <WebElement> optionUnSelected= select.getAllSelectedOptions();
		Assert.assertEquals(optionUnSelected.size(),0);
	}

	@Test
	public void TC_02_Dropdown_List() {
		System.out.println("01. Truy cập trang");
		driver.get("https://demo.nopcommerce.com/register");
		
		System.out.println("02. Click Register link trên Header");
		driver.findElement(By.xpath("//a[@class='ico-register']")).click();
		
		System.out.println("03. Input các thông tin hợp lệ vào form");
		driver.findElement(By.xpath("//input[@id='gender-male']")).click();
		driver.findElement(By.xpath("//input[@id='FirstName']")).sendKeys("Julia");
		driver.findElement(By.xpath("//input[@id='LastName']")).sendKeys("Vo");
		
		select=new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthDay']")));
		select.selectByIndex(1);
		Assert.assertEquals(select.getOptions().size(), 32);
		
		select=new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthMonth']")));
		select.selectByVisibleText("May");
		Assert.assertEquals(select.getOptions().size(), 13);
		
		select=new Select(driver.findElement(By.xpath("//select[@name='DateOfBirthYear']")));
		select.selectByVisibleText("1980");
		Assert.assertEquals(select.getOptions().size(), 112);
		
		driver.findElement(By.xpath("//input[@id='Email']")).sendKeys("julia_vo12345@gmail.com");
		driver.findElement(By.xpath("//input[@id='Company']")).sendKeys("Innerbus");
		driver.findElement(By.xpath("//input[@id='Password']")).sendKeys("abcd12345");
		driver.findElement(By.xpath("//input[@id='ConfirmPassword']")).sendKeys("abcd12345");
		
		System.out.println("04. Click register button");
		driver.findElement(By.xpath("//input[@id='register-button']")).click();
		
		System.out.println("05. Verify đã vào trang HomePage sau khi đăng kí thành công");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class='ico-account']")).getText(), "My account");
		Assert.assertEquals(driver.findElement(By.xpath("//a[@class='ico-logout']")).getText(), "Log out");
		Assert.assertEquals(driver.findElement(By.xpath("//div[@class='result']")).getText(), "Your registration completed");
	}

	

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
