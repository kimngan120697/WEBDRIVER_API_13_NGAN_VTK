package webdriver_api;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class Topic_02_Template2 {

	//Khai báo 1 cái biến driver đại diện cho Selenium WebDriver
		WebDriver driver;

		//Pre-condition
		@BeforeClass
		public void beforeClass() {

		}

		@Test
		public void TC_01_ValidateCurrentUrl() {

		}

		@Test
		public void TC_02_ValidatePageTitle() {

		}

		@Test
		public void TC_03_LoginFormDisplayed() {

		}

		//Post condition
		@AfterClass
		public void afterClass() {
			//Tắt trình duyệt
			driver.quit();
		}

	}
