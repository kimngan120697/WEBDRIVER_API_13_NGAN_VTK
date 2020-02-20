package webdriver_api;

import java.io.IOException;
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
	String projectPath=System.getProperty("user.dir");
	String image01Path=projectPath+"\\uploadFiles\\Image_01.jpg";
	String image02Path=projectPath+"\\uploadFiles\\Image_02.jpg";
	String image03Path=projectPath+"\\uploadFiles\\Image_03.jpg";
	String image04Path=projectPath+"\\uploadFiles\\Image_04.jpg";
	String image05Path=projectPath+"\\uploadFiles\\Image_05.jpg";
	
	String chromeAutoIT=projectPath+"\\uploadAutoIT\\chrome.exe";
	String firefoxAutoIT=projectPath+"\\\\uploadAutoIT\\firefox.exe";
	
	// Pre-condition
	@BeforeClass
	public void beforeClass() {
		
		//Firefox Latest
		System.setProperty("webdriver.gecko.driver", ".\\libraries\\geckodriver.exe");
		driver = new FirefoxDriver();
		
		//Chrome
		//System.setProperty("webdriver.chrome.driver", projectPath+"\\libraries\\geckodriver.exe")
		//driver = new FirefoxDriver();
		
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}

	public void TC_01_UploadFile_bySendkeys() throws InterruptedException {
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement uploadFile=find("//input[@type='file']");
		uploadFile.sendKeys(image01Path+"\n"+image02Path+"\n"+image03Path+"\n"+image04Path+"\n"+image05Path);
		Thread.sleep(3000);
		
		List  <WebElement> startButtons=finds("//table//button[@class='btn btn-primary start']");
		for(WebElement start:startButtons)
		{
			start.click();
			Thread.sleep(2000);
		}
		Assert.assertTrue(find("//p[@class='name']//a[@title='Image_01.jpg']").isDisplayed());
		Assert.assertTrue(find("//p[@class='name']//a[@title='Image_02.jpg']").isDisplayed());
		Assert.assertTrue(find("//p[@class='name']//a[@title='Image_03.jpg']").isDisplayed());
		Assert.assertTrue(find("//p[@class='name']//a[@title='Image_04.jpg']").isDisplayed());
		Assert.assertTrue(find("//p[@class='name']//a[@title='Image_05.jpg']").isDisplayed());
		
		
	}
	
	@Test
	public void TC_02_UploadFile_byAutoIT() throws InterruptedException, IOException{
		driver.get("http://blueimp.github.com/jQuery-File-Upload/");
		
		WebElement uploadFile=driver.findElement(By.cssSelector(".fileinput-button"));
		uploadFile.click();
		
		//Execute time file(.exe/ .msi/ .jar/ .bat/ .sh)
		Runtime.getRuntime().exec(new String[] {firefoxAutoIT,image01Path} );
		find("//table//button[@class='btn btn-primary start']").click();
		Thread.sleep(2000);
		Assert.assertTrue(find("//p[@class='name']//a[@title='Image_01.jpg']").isDisplayed());
	}
	
	@Test
	public void TC_03_UploadFile_byRobotClass() throws InterruptedException {
		
	}
	
	@Test
	public void TC_04_UploadFile() throws InterruptedException {
		
	}

	public WebElement find(String xpath) {
		return driver.findElement(By.xpath(xpath));
	}
	public List <WebElement> finds(String xpath) {
		return driver.findElements(By.xpath(xpath));
	}
	// Post condition
	@AfterClass
	public void afterClass() {
		// Tắt trình duyệt
		driver.quit();
	}
}
