package webdriver_api;

import java.util.List;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_13_JavaScriptExecutor {

	
	WebDriver driver;
	String projectPath = System.getProperty("user.dir");
	JavascriptExecutor jsExecutor;
	WebElement element;

	String username = "mngr246313 ";
	String password = "hagepAp";
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
		// Set biến môi trường cho driver trong quá trình chạy
				System.setProperty("webdriver.chrome.driver", projectPath + "\\libraries\\chromedriver.exe");
				driver = new ChromeDriver();
		//driver = new FirefoxDriver();
		jsExecutor = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}
	@Test
	public void TC_01_JavaScriptExecutor() throws InterruptedException {

		navigateToUrlByJS("http://live.demoguru99.com/");

		String liveDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(liveDomain, "live.demoguru99.com");

		String liveUrl = (String) executeForBrowser("return document.URL");
		Assert.assertEquals(liveUrl, "http://live.demoguru99.com/");

		clickToElementByJS("//a[text()='Mobile']");
		clickToElementByJS(
				"//a[text()='Samsung Galaxy']//parent::h2//following-sibling::div[@class='actions']//button[@class='button btn-cart']");

		String pageInnerText=(String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(pageInnerText.contains("Samsung Galaxy was added to your shopping cart."));
		

		clickToElementByJS("//a[text()='Customer Service']");
		String customerServiceTitle = (String) executeForBrowser("return document.title");
		Assert.assertEquals(customerServiceTitle, "Customer Service");

		scrollToElement("//input[@id='newsletter']");
		Thread.sleep(3000);

		pageInnerText=(String) executeForBrowser("return document.documentElement.innerText;");
		Assert.assertTrue(pageInnerText.contains("Praesent ipsum libero, auctor ac, tempus nec, tempor nec, justo."));

		navigateToUrlByJS("http://demo.guru99.com/v4/");
		String guruDomain = (String) executeForBrowser("return document.domain");
		Assert.assertEquals(guruDomain, "demo.guru99.com");

	}

	@Test
	public void TC_02_JavaScriptExecutor() {

		navigateToUrlByJS("http://demo.guru99.com/v4");
		driver.findElement(By.name("uid")).sendKeys(username);
		driver.findElement(By.name("password")).sendKeys(password);
		clickToElement(loginBtnBy);
		
		clickToElement(newCustomerMenuBy);
		sendkeyToElement(nameTextBoxBy, customerName);
		clickToElement(genderRadioBy);
		removeAttributeInDOM("//input[@id='dob']","type");
		sendkeyToElement(dateOfBirthTextboxBy, dateOfBirth);
		sendkeyToElement(addressTextAreaBy, address);
		sendkeyToElement(cityTextboxBy, city);
		sendkeyToElement(stateTextboxBy, state);
		sendkeyToElement(pinTextboxBy, pin);
		sendkeyToElement(phoneTextboxBy, mobileNumber);
		sendkeyToElement(emailTextboxBy, email);
		sendkeyToElement(passwordTextboxBy, password);
		clickToElement(sumbitBtnBy);
		Assert.assertTrue(
				driver.findElement(By.xpath("//p[@class='heading3' and text()='Customer Registered Successfully!!!']"))
						.isDisplayed());

		// Verify output data= input data
		Assert.assertEquals(customerName,
				driver.findElement(By.xpath("//td[contains(text(),'Customer Name')]/following-sibling::td")).getText());
		Assert.assertEquals(gender,
				driver.findElement(By.xpath("//td[contains(text(),'Gender')]/following-sibling::td")).getText());
		Assert.assertEquals(dateOfBirth,
				driver.findElement(By.xpath("//td[contains(text(),'Birthdate')]/following-sibling::td")).getText());
		Assert.assertEquals(address,
				driver.findElement(By.xpath("//td[contains(text(),'Address')]/following-sibling::td")).getText());
		Assert.assertEquals(city,
				driver.findElement(By.xpath("//td[contains(text(),'City')]/following-sibling::td")).getText());
		Assert.assertEquals(state,
				driver.findElement(By.xpath("//td[contains(text(),'State')]/following-sibling::td")).getText());
		Assert.assertEquals(pin,
				driver.findElement(By.xpath("//td[contains(text(),'Pin')]/following-sibling::td")).getText());
		Assert.assertEquals(mobileNumber,
				driver.findElement(By.xpath("//td[contains(text(),'Mobile No.')]/following-sibling::td")).getText());
		Assert.assertEquals(email,
				driver.findElement(By.xpath("//td[contains(text(),'Email')]/following-sibling::td")).getText());

		customerID = driver.findElement(By.xpath("//td[contains(text(),'Customer ID')]/following-sibling::td"))
				.getText();
		System.out.println("Customer ID at New Customer Form=" + customerID + "");

	}

	@Test
	public void TC_03_JavaScriptExecutor() {
		navigateToUrlByJS("http://live.guru99.com/");

		clickToElementByJS("//div[@id='header-account']//a[contains(text(),'My Account')]");
		clickToElementByJS("//a[@class='button']");
		
		sendkeyToElementByJS("//input[@id='firstname']","Hello");
		sendkeyToElementByJS("//input[@id='middlename']","Automation");
		sendkeyToElementByJS("//input[@id='lastname']","Test");
		sendkeyToElementByJS("//input[@id='email_address']",email);
		sendkeyToElementByJS("//input[@id='password']","abcd@12345");
		sendkeyToElementByJS("//input[@id='confirmation']","abcd@12345");
		clickToElementByJS("//span[contains(text(),'Register')]");
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='Thank you for registering with Main Website Store.']")).isDisplayed());
		clickToElementByJS("//a[contains(text(),'Log Out')]");
		Assert.assertTrue(driver.findElement(By.xpath("//div[@class='page-title']")).isDisplayed());
		
	}

	public void clickToElement(By by) {
		WebElement element = driver.findElement(by);
		element.click();
	}

	public int randomNumber() {
		Random rand = new Random();
		int number = rand.nextInt(100);
		return number;
	}

	public void sendkeyToElement(By by, String value) {
		WebElement element = driver.findElement(by);
		element.sendKeys(value);
	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}

	// Browser
	public Object executeForBrowser(String javaSript) {
		return jsExecutor.executeScript(javaSript);
	}

	public boolean verifyTextInInnerText(String textExpected) {
		String textActual = (String) jsExecutor
				.executeScript("return document.documentElement.innerText.match('" + textExpected + "')[0]");
		System.out.println("Text actual = " + textActual);
		return textActual.equals(textExpected);
	}

	public void scrollToBottomPage() {
		jsExecutor.executeScript("window.scrollBy(0,document.body.scrollHeight)");
	}

	public void navigateToUrlByJS(String url) {
		jsExecutor.executeScript("window.location = '" + url + "'");
	}

	// Element
	public void highlightElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		String originalStyle = element.getAttribute("style");
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				"border: 5px solid red; border-style: dashed;");
		try {
			Thread.sleep(2000);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		jsExecutor.executeScript("arguments[0].setAttribute(arguments[1], arguments[2])", element, "style",
				originalStyle);

	}

	public void clickToElementByJS(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].click();", element);
	}

	public void scrollToElement(String locator) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].scrollIntoView(true);", element);
	}

	public void sendkeyToElementByJS(String locator, String value) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].setAttribute('value', '" + value + "')", element);
	}

	public void removeAttributeInDOM(String locator, String attributeRemove) {
		element = driver.findElement(By.xpath(locator));
		jsExecutor.executeScript("arguments[0].removeAttribute('" + attributeRemove + "');", element);
	}
}
