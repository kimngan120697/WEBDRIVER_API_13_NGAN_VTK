package webdriver_api;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_List_Custom {
	WebDriver driver;
	WebDriverWait waitExplicit;
	Select select;
	JavascriptExecutor javascript;
	Actions action;
	// Pre-condition

	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		action = new Actions(driver);
		javascript = (JavascriptExecutor) driver;
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_JQuery() throws InterruptedException {

		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		// Click vào 1 và kiểm tra nó được chọn thành công
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "1");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='1']"));
		Thread.sleep(2000);
		// Click vào 5 và kiểm tra nó được chọn thành công
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "5");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='5']"));
		Thread.sleep(2000);

		// Click vào 11 và kiểm tra nó được chọn thành công
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "11");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='11']"));
		Thread.sleep(2000);

		// Click vào 19 và kiểm tra nó được chọn thành công
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "19");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='19']"));
	}

	public void TC_02_Angular() throws InterruptedException {
		driver.get("https://ej2.syncfusion.com/angular/demos/?_ga=2.262049992.437420821.1575083417-524628264.1575083417#/material/drop-down-list/data-binding");

		// Click vào Football và kiểm tra nó được chọn thành công
		selectItemInCustomDropdown("//ejs-dropdownlist[@id='games']", "//ul[@id='games_options']/li", "Football");
		Thread.sleep(2000);
		// Kiểm tra Football được chọn thành công
		String expectedValue = getTextByJS("#games_hidden>option");
		System.out.println("Text=" + expectedValue);
		Assert.assertEquals(expectedValue, "Football");

	}

	public void TC_03_React() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/modules/dropdown/");

		// Click vào Christian và kiểm tra nó được chọn thành công
		selectItemInCustomDropdown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i", "", "Christian");
		// Kiểm tra Christian được chọn thành công
		Assert.assertTrue(isElementDisplayed("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/div[@class='text' and text()='Christian']"));
		Thread.sleep(2000);

		// Click vào Jenny Hess và kiểm tra nó được chọn thành công
		selectItemInCustomDropdown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i", "//div[@class='visible menu transition']/div/span", "Jenny Hess");
		// Kiểm tra Christian được chọn thành công
		Assert.assertTrue(isElementDisplayed("//div[@class='ui fluid selection dropdown']/div[@class='text' and text()='Jenny Hess']"));
		Thread.sleep(2000);

		// Click vào Christian và kiểm tra nó được chọn thành công
		selectItemInCustomDropdown("//h3[@id='selection']/ancestor::div[@class='equal width row']/following-sibling::div//div[@role='listbox']/i", "//div[@class='visible menu transition']/div/span", "Stevie Feliciano");
		// Kiểm tra Stevie Feliciano được chọn thành công
		Assert.assertTrue(isElementDisplayed("//div[@class='ui fluid selection dropdown']/div[@class='text' and text()='Stevie Feliciano']"));
		Thread.sleep(2000);

	}

	@Test
	public void TC_04_Editable() throws InterruptedException {
		driver.get("https://react.semantic-ui.com/maximize/dropdown-example-multiple-search-selection/");
		inputItemInCustomDropdown("//div[contains(@class,'search selection')]//i[@class='dropdown icon']","//input[@class='search']","California");
		Assert.assertTrue(isElementDisplayed("//div[contains(@class,'search selection')]/div[@class='text' and text()='California']"));
		Thread.sleep(2000);
	}

	public void inputItemInCustomDropdown(String parentXpath, String inputXpath, String expectedText) {
		// 01. Click vào thẻ chứa Dropdown để show all items
		driver.findElement(By.xpath(parentXpath)).click();
		
		// 02. Input text vào textbox
		driver.findElement(By.xpath(parentXpath)).sendKeys(expectedText);
		
		//Truyền phím ENTER vào input text
		action.sendKeys(driver.findElement(By.xpath(inputXpath)), Keys.ENTER);

	}

	public boolean isElementDisplayed(String locatorXpath) {
		WebElement element = driver.findElement(By.xpath(locatorXpath));
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
	}

	public String getTextByJS(String locator) {
		return (String) javascript.executeScript("return document.querySelector('" + locator + "').text");
	}

	public void selectItemInCustomDropdown(String parentXpath, String allItemsXpath, String expectedText) {

		// 01. Click vào thẻ chứa Dropdown để show all items
		driver.findElement(By.xpath(parentXpath)).click();

		// 02. Khai báo 1 List <Element> chứa all items bên trong.
		List<WebElement> allItems = driver.findElements(By.xpath(allItemsXpath));

		// 03. Wai để tất cả các item (List WebElement) được xuất hiện trong DOM
		waitExplicit.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(allItemsXpath)));

		// 04. Get text từng item sau đó so sánh với item mình cần chọn
		for (WebElement item : allItems) {
			System.out.println("Item: " + item.getText());
			// 05. Kiểm tra item nào đúng với mình cần chọn thì click vào
			if (item.getText().equals(expectedText)) {
				item.click();
				break;
			}
		}
	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
