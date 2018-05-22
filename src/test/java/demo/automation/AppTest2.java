package demo.automation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import demo.automation.DriversSetUp;

public class AppTest2 {
	
	protected WebDriver webDriver;
	String userName = "olena.bulat@resultify.se";
	String userName2 = "olena@pixelant.se";

	@BeforeMethod
	@Parameters("browserName")
	public void setup(String browserName) {
		webDriver = DriversSetUp.getInstance(browserName);
	}
	
	@AfterMethod
	public void tearDown() throws Exception {
		if (webDriver != null) {
			webDriver.quit ();
		}
	}
	
	@Test
	public void getURL ( ) throws IOException, InterruptedException {
		webDriver.get("http://demo.t3kit.com/");
		
		
		
		WebElement scrollToSubscribe = webDriver.findElement(By.xpath("//input[@id='email']"));
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", scrollToSubscribe);
		Thread.sleep(5000);
		scrollToSubscribe.click();
		scrollToSubscribe.sendKeys(userName2);
		
		WebElement submit = webDriver.findElement(By.xpath("//*[@id='c444']/div/form/div[3]/input"));
		submit.click();
		
		WebElement alert = webDriver.findElement(By.xpath("//*[@id='c444']/div/div[1]"));
		Assert.assertTrue(alert.isDisplayed(), "Test DONE!");
		
	}

}
