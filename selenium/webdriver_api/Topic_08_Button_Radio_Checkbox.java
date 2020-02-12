package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_08_Button_Radio_Checkbox {
	WebDriver driver;
	JavascriptExecutor javascript;
	Alert alert;
	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
	}
	@Test 
	public void TC_01_Button_JavascriptExecutor() {
		System.out.println("01. Truy cập trang");
		driver.get("http://live.demoguru99.com/");
		
		System.out.println("02. Click vào My Account dưới Footer (Sử dụng Javascript Executor code)");
		clickElementByJavascript("//div[@class='footer']//a[text()='My Account']");
		
		System.out.println("03. Kiểm tra url của page sau khi click");
		String urlMyAccountPage=driver.getCurrentUrl();
		Assert.assertEquals(urlMyAccountPage, "http://live.demoguru99.com/index.php/customer/account/login/");
		
		System.out.println("Step 04 - Click 'CREATE AN ACCOUNT' button");
		clickElementByJavascript("//span[text()='Create an Account']");
		
		System.out.println("Step 05 - Verify 'Register Page' Url");
		String urlRegisterPage=driver.getCurrentUrl();
		Assert.assertEquals(urlRegisterPage, "http://live.demoguru99.com/index.php/customer/account/create/");
	}
	@Test 
	public void TC_02_CheckBox() {
		driver.get("https://demos.telerik.com/kendo-ui/checkbox/index");
		clickElementByJavascript("//label[text()='Dual-zone air conditioning']");
		Assert.assertTrue(isSelectedStatus("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		clickElementByJavascript("//label[text()='Dual-zone air conditioning']");
		Assert.assertFalse(isSelectedStatus("//label[text()='Dual-zone air conditioning']/preceding-sibling::input"));
		
		
	}
	@Test 
	public void TC_03_RadioButton() {
		String radio20Petrol="//label[text()='2.0 Petrol, 147kW']/preceding-sibling::input";
		driver.get("https://demos.telerik.com/kendo-ui/radiobutton/index");
		clickElementByJavascript(radio20Petrol);
		if(isSelectedStatus(radio20Petrol)==false)
		{
			clickElementByJavascript(radio20Petrol);
		}
		Assert.assertTrue(isSelectedStatus(radio20Petrol));
	}
	@Test 
	public void TC_04_Accept_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickElementByJavascript("//button[text()='Click for JS Alert']");
		
		alert=driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Alert");
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked an alert successfully");
	}
	@Test 
	public void TC_05_Confirm_Alert() {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickElementByJavascript("//button[text()='Click for JS Confirm']");
		alert=driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS Confirm");
		alert.dismiss();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You clicked: Cancel");
	}
	@Test 
	public void TC_06_Prompt_Alert() {
		String editText="Automation Tester";
		driver.get("https://automationfc.github.io/basic-form/index.html");
		clickElementByJavascript("//button[text()='Click for JS Prompt']");
		alert=driver.switchTo().alert();
		Assert.assertEquals(alert.getText(), "I am a JS prompt");
		alert.sendKeys(editText);
		alert.accept();
		Assert.assertEquals(driver.findElement(By.xpath("//p[@id='result']")).getText(),"You entered: "+editText);
	}
	@Test 
	public void TC_07_Authentication_Alert() {
		
		String username="admin";
		String password="admin";
		String url="http://the-internet.herokuapp.com/basic_auth";
		driver.get(byPassAuthenticationAlert(url, username, password));
		Assert.assertTrue(driver.findElement(By.xpath("//p[contains(text(),'Congratulations! You must have the proper credentials.')]")).isDisplayed());
	}
	
	public void clickElementByJavascript(String locator) {
		WebElement element=driver.findElement(By.xpath(locator));
		javascript=(JavascriptExecutor) driver;
		javascript.executeScript("arguments[0].click();", element);	
		
	}
	public boolean isSelectedStatus(String locator) {
		WebElement element=driver.findElement(By.xpath(locator));
		if(element.isSelected())
		{
			System.out.print("Element is selected");
			return true;
		}
		else
		{
			System.out.print("Element is non-selected");
			return false;
		}
	}

	public String byPassAuthenticationAlert(String url, String username, String password) {
		System.out.println("Old Url="+url);
		String[] splitURL=url.split("//");
		url=splitURL[0]+"//"+username+":"+password+"@"+splitURL[1];
		System.out.println("New Url="+url);
		return url;
	}
	
	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
