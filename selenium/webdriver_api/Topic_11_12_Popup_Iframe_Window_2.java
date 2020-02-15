package webdriver_api;

import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;


public class Topic_11_12_Popup_Iframe_Window_2 {

	WebDriver driver;
	JavascriptExecutor javascript;
	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		driver = new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_02_Window_Tab_01() throws InterruptedException {
		driver.get("https://automationfc.github.io/basic-form/index.html");
		
		String parentID=driver.getWindowHandle(); 
		System.out.println("Parent ID: "+parentID);
		
		driver.findElement(By.xpath("//a[text()='GOOGLE']")).click();
		Thread.sleep(2000);
		
		switchToWindowByTitle("Google");
		Assert.assertEquals(driver.getTitle(),"Google");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);
		Assert.assertEquals(driver.getTitle(),"SELENIUM WEBDRIVER FORM DEMO");
		
		driver.findElement(By.xpath("//a[text()='FACEBOOK']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Facebook - Đăng nhập hoặc đăng ký");
		Assert.assertEquals(driver.getTitle(),"Facebook - Đăng nhập hoặc đăng ký");
		
		switchToWindowByTitle("SELENIUM WEBDRIVER FORM DEMO");
		Thread.sleep(2000);

		driver.findElement(By.xpath("//a[text()='TIKI']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		Assert.assertEquals(driver.getTitle(),"Mua Hàng Trực Tuyến Uy Tín với Giá Rẻ Hơn tại Tiki.vn");
		
		closeAllWindowWithoutParent(parentID);
		
	}
	
	@Test
	public void TC_03_Window_Tab_02() throws InterruptedException {
		
		driver.get("https://kyna.vn/");
		String parentID=driver.getWindowHandle();
		
		List <WebElement> fancyPopup=driver.findElements(By.xpath("//div[@class='fancybox-inner']"));
		System.out.println("Fancybox popup is displayed: " + fancyPopup.size());
		if (fancyPopup.size()>0)
		{
			Assert.assertTrue(fancyPopup.get(0).isDisplayed());
			driver.findElement(By.cssSelector(".fancybox-close")).click();
		}

		clickElementByJavascript("//div[@class='social']/a/img[@alt='facebook']");
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(),"Kyna.vn - Trang chủ | Facebook");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Thread.sleep(2000);
		
		clickElementByJavascript("//a//img[@alt='youtube']");
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn - YouTube");
		Assert.assertEquals(driver.getTitle(),"Kyna.vn - YouTube");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Thread.sleep(2000);
		
		clickElementByJavascript("//a//img[@alt='zalo']");
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn");
		Assert.assertEquals(driver.getCurrentUrl(),"https://zalo.me/1985686830006307471");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");
		Thread.sleep(2000);
		
		clickElementByJavascript("//a/img[@alt='apple-app-icon']");
		Thread.sleep(2000);
		switchToWindowByTitle("‎KYNA on the App Store");
		Assert.assertEquals(driver.getTitle(),"‎KYNA on the App Store");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");		
		Thread.sleep(2000);
		
		clickElementByJavascript("//a/img[@alt='android-app-icon']");
		Thread.sleep(2000);
		switchToWindowByTitle("KYNA - Học online cùng chuyên gia - Apps on Google Play");
		Assert.assertEquals(driver.getTitle(),"KYNA - Học online cùng chuyên gia - Apps on Google Play");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");		
		Thread.sleep(2000);
		
		driver.switchTo().frame(driver.findElement(By.xpath("//iframe[contains(@src,\"facebook.com\")]")));
		driver.findElement(By.xpath("//a[text()='Kyna.vn']")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Kyna.vn - Trang chủ | Facebook");
		Assert.assertEquals(driver.getTitle(),"Kyna.vn - Trang chủ | Facebook");
		
		driver.switchTo().defaultContent();
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");		
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//p[text()='Trường học trực tuyến cho trẻ']//preceding-sibling::a")).click();;
		Thread.sleep(2000);
		switchToWindowByTitle("Kynaforkids.vn trường học trực tuyến cho trẻ");
		Assert.assertEquals(driver.getTitle(),"Kynaforkids.vn trường học trực tuyến cho trẻ");
		
		switchToWindowByTitle("Kyna.vn - Học online cùng chuyên gia");	
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//p[text()='Đào tạo trực tuyến cho doanh nghiệp']//preceding-sibling::a")).click();
		Thread.sleep(2000);
		switchToWindowByTitle("Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn");
		Assert.assertEquals(driver.getTitle(),"Giải pháp đào tạo nhân sự online toàn diện - KynaBiz.vn");
		
		closeAllWindowWithoutParent(parentID);

	}
	

	@Test
	public void TC_04_Window_Tab_03() {
		driver.get("http://live.guru99.com/index.php/");
		clickElementByJavascript("//a[text()='Mobile']");
		driver.findElement(By.xpath("//a[text()='Sony Xperia']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Sony Xperia has been added to comparison list.']")).isDisplayed());
		
		driver.findElement(By.xpath("//a[text()='Samsung Galaxy']/parent::h2/following-sibling::div[@class='actions']/ul/li/a[text()='Add to Compare']")).click();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The product Samsung Galaxy has been added to comparison list.']")).isDisplayed());
	
		driver.findElement(By.xpath("//span[text()='Compare']")).click();
		switchToWindowByTitle("Products Comparison List - Magento Commerce");
		Assert.assertEquals(driver.getTitle(), "Products Comparison List - Magento Commerce");
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Sony Xperia']")).isDisplayed());
		Assert.assertTrue(driver.findElement(By.xpath("//img[@alt='Samsung Galaxy']")).isDisplayed());
		
		driver.findElement(By.xpath("//button[@title='Close Window']")).click();
		switchToWindowByTitle("Mobile");
		driver.findElement(By.xpath("//a[text()='Clear All']")).click();
		driver.switchTo().alert().accept();
		Assert.assertTrue(driver.findElement(By.xpath("//span[text()='The comparison list was cleared.']")).isDisplayed());
		
		
		
		
	}
	
	//Switch to child window (only 2 window)
	public void switchToWindowByID(String parentID) {
		Set <String> allWindows=driver.getWindowHandles();
		for(String runWindow:allWindows)
		{
			if(!runWindow.equals(parentID)) {
				driver.switchTo().window(runWindow);
				break;
			}
		}
	}
	
	//Switch to child Windows (greater than 2 windows and title of the pages are unique)
	public void switchToWindowByTitle(String title)
	{
		Set <String> allWindows= driver.getWindowHandles();
		for(String runWindows:allWindows)
		{
			driver.switchTo().window(runWindows);
			String currentWin=driver.getTitle();
			if(currentWin.equals(title))
			{
				break;
			}
		}
	}
	
	//Close all windows without parent window
	public void closeAllWindowWithoutParent(String parentWindow) {
		Set<String>allWindows=driver.getWindowHandles();
		for(String runWindow:allWindows)
		{
				if(!runWindow.equals(parentWindow))
				{
					driver.switchTo().window(runWindow);
					driver.close();
				}
		}
		driver.switchTo().window(parentWindow);
	}

	public void clickElementByJavascript(String locator) {
		WebElement element=driver.findElement(By.xpath(locator));
		javascript=(JavascriptExecutor) driver;
		javascript.executeScript("arguments[0].click();", element);	
		
	}

	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
