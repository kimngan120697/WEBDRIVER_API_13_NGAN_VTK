package webdriver_api;

import java.util.Random;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_06_Textbox_Textarea {

	// Khai báo 1 cái biến driver đại diện cho Selenium WebDriver
	WebDriver driver;
	String username = "mngr244688";
	String password = "pYgEmYs";
	String customerID; // Biến toàn cục

	// Input (user) / Output (server) data in New Customer

	String customerName = "Selenium Online";
	String gender = "male";
	String dateOfBirth = "2000-02-02";
	String address = "123 Address";
	String city = "Ho Chi Minh";
	String state = "Tan Phu";
	String pin = "123456";
	String mobileNumber = "0123456789";
	String email = "kimngan" + randomNumber() + "@gmail.com";

	// Input in Edit Customer
	String editAddress = "So 20, duong D19, phuong 5";
	String editCity = "Ho Chi Minh City";
	String editState = "Tan Binh district";
	String editPin = "654321";
	String editMobileNumber = "9875643210";
	String editEmail = "kimnganedit" + randomNumber() + "@hotmail.com";

	// Locator
	By loginBtnBy = By.xpath("//input[@name='btnLogin']");
	By newCustomerMenuBy = By.xpath("//a[contains(text(),'New Customer')]");
	By editCustomerMenuBy = By.xpath("//a[contains(text(),'Edit Customer')]");

	By nameTextBoxBy = By.xpath("//input[@name='name']");
	By genderRadioBy = By.xpath("//input[@value=\"m\"]");
	By dateOfBirthTextboxBy = By.xpath("//input[@id='dob']");
	By addressTextAreaBy = By.xpath("//textarea[@name='addr']");
	By cityTextboxBy = By.xpath("//input[@name='city']");
	By stateTextboxBy = By.xpath("//input[@name='state']");
	By pinTextboxBy = By.xpath("//input[@name='pinno']");
	By phoneTextboxBy = By.xpath("//input[@name='telephoneno']");
	By emailTextboxBy = By.xpath("//input[@name='emailid']");
	By passwordTextboxBy = By.xpath("//input[@name='password']");
	By sumbitBtnBy = By.xpath("//input[@name='sub']");
	By sumbitBtnEditBy = By.xpath("//input[@name='AccSubmit']");
	By customerIDTxtEditFormBy = By.xpath("//input[@name='cusid']");

	By genderTxtEditBy = By.xpath("//input[@name='gender']");
	By dateOfBirthTxtEditBy = By.xpath("//input[@name='dob']");

	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

		driver.get("http://demo.guru99.com/v4/");
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		clickToElement(loginBtnBy);

		String homePageWelcomeMsg = driver.findElement(By.tagName("marquee")).getText();
		Assert.assertEquals(homePageWelcomeMsg, "Welcome To Manager's Page of Guru99 Bank");
		Assert.assertTrue(driver.findElement(By.xpath("//td[contains(text(),'Manger Id : " + username + "')]")).isDisplayed());

	}

	@Test
	public void TC_01_NewCustomer() {

		clickToElement(newCustomerMenuBy);
		sendkeyToElement(nameTextBoxBy, customerName);
		clickToElement(genderRadioBy);
		sendkeyToElement(dateOfBirthTextboxBy, dateOfBirth);
		sendkeyToElement(addressTextAreaBy, address);
		sendkeyToElement(cityTextboxBy, city);
		sendkeyToElement(stateTextboxBy, state);
		sendkeyToElement(pinTextboxBy, pin);
		sendkeyToElement(phoneTextboxBy, mobileNumber);
		sendkeyToElement(emailTextboxBy, email);
		sendkeyToElement(passwordTextboxBy, password);
		clickToElement(sumbitBtnBy);
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']")).isDisplayed());

		// Verify output data= input data
		Assert.assertEquals(customerName, driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText());
		Assert.assertEquals(gender, driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth, driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText());
		Assert.assertEquals(address, driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText());
		Assert.assertEquals(city, driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText());
		Assert.assertEquals(state, driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText());
		Assert.assertEquals(pin, driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText());
		Assert.assertEquals(mobileNumber, driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText());
		Assert.assertEquals(email, driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText());

		customerID = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td")).getText();
		System.out.println("Customer ID at New Customer Form=" + customerID + "");

	}

	@Test
	public void TC_02_EditCusomer() {

		clickToElement(editCustomerMenuBy);
		sendkeyToElement(customerIDTxtEditFormBy, customerID);
		System.out.println("Customer ID at New Customer Form=" + customerID + "");
		clickToElement(sumbitBtnEditBy);

		// Verify fields disabled: Name, gender, date of birth
		Assert.assertFalse(isElementEnabled(nameTextBoxBy));
		Assert.assertFalse(isElementEnabled(genderTxtEditBy));
		Assert.assertFalse(isElementEnabled(dateOfBirthTxtEditBy));

		// Verify output at Edit form = input at New Customer Form: Customer Name. Address
		Assert.assertEquals(customerName, driver.findElement(nameTextBoxBy).getAttribute("value"));
		Assert.assertEquals(address, driver.findElement(addressTextAreaBy).getText());

		// Edit data at edit customer, except fields disable
		driver.findElement(addressTextAreaBy).clear();
		driver.findElement(cityTextboxBy).clear();
		driver.findElement(stateTextboxBy).clear();
		driver.findElement(pinTextboxBy).clear();
		driver.findElement(phoneTextboxBy).clear();
		driver.findElement(emailTextboxBy).clear();

		sendkeyToElement(addressTextAreaBy, editAddress);
		sendkeyToElement(cityTextboxBy, editCity);
		sendkeyToElement(stateTextboxBy, editState);
		sendkeyToElement(pinTextboxBy, editPin);
		sendkeyToElement(phoneTextboxBy, editMobileNumber);
		sendkeyToElement(emailTextboxBy, editEmail);
		clickToElement(sumbitBtnBy);

		// Verify all fields after edit successful
		Assert.assertTrue(driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer details updated Successfully!!!']")).isDisplayed());

		// Verify output data= input data
		Assert.assertEquals(customerID, driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td")).getText());
		Assert.assertEquals(editAddress, driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText());
		Assert.assertEquals(editCity, driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText());
		Assert.assertEquals(editState, driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText());
		Assert.assertEquals(editPin, driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText());
		Assert.assertEquals(editMobileNumber, driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText());
		Assert.assertEquals(editEmail, driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText());
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	public boolean isElementEnabled(By by) {
		WebElement element = driver.findElement(by);
		if (element.isEnabled()) {
			System.out.println("Element [" + by + "] is enabled");
			return true;
		} else {
			System.out.println("Element [" + by + "] is disabled");
			return false;
		}
	}

	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}

	public int randomNumber() {
		Random rand = new Random();
		int number = rand.nextInt(100);
		return number;
	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
