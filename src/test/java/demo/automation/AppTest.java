package demo.automation;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.*;

import demo.automation.DriversSetUp;


public class AppTest {
	
	protected WebDriver webDriver;
	

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
	
		/* openExamplePage */	
		WebElement examplesMainMenu = webDriver
				.findElement(By.xpath("//a[@href='/examples/'][text()='Examples']"));
		examplesMainMenu.click(); 
		WebElement examplesMainMenuSubPage = webDriver
				.findElement(By.xpath("(//a[@href='/examples/page-1/'][text()='Page 1'][text()='Page 1'])[2]"));
		examplesMainMenuSubPage.click(); 
	
		/*checkHover*/
		WebElement scrollToImageTextLinkElement = webDriver
				.findElement(By.xpath("//div[@id='c516']/div/div[1]"));
		((JavascriptExecutor) webDriver).executeScript("arguments[0].scrollIntoView(true);", scrollToImageTextLinkElement);
		Thread.sleep(5000);
		
		WebElement boxImageTextLink = webDriver.findElement(By.xpath("//div[@id='c522']/div/div/a"));
		Actions builder = new Actions(webDriver); 
		builder.clickAndHold(boxImageTextLink)
		//.clickAndHold(element2)
		//.click()
		.build()
		.perform();
		
		Assert.assertTrue(boxImageTextLink.getCssValue("z-index").equals("4"));
	}	

}
