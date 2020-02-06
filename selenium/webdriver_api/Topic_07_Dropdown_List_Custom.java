package webdriver_api;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class Topic_07_Dropdown_List_Custom {
	WebDriver driver;
	WebDriverWait waitExplicit;
	Select select;

	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		waitExplicit = new WebDriverWait(driver, 10);
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	@Test
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
	
	@Test
	public void TC_02_Angular() {
		driver.get("https://jqueryui.com/resources/demos/selectmenu/default.html");
		// Click vào 1 và kiểm tra nó được chọn thành công
		selectItemInCustomDropdown("//span[@id='number-button']", "//ul[@id='number-menu']/li", "1");
		Assert.assertTrue(isElementDisplayed("//span[@id='number-button']/span[@class='ui-selectmenu-text' and text()='1']"));

	}
	public boolean isElementDisplayed(String locatorXpath) {
		WebElement element = driver.findElement(By.xpath(locatorXpath));
		if (element.isDisplayed()) {
			return true;
		} else {
			return false;
		}
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
