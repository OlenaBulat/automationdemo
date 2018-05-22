package demo.automation;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Drivers {
	
	protected WebDriver webDriver;
	protected static DesiredCapabilities dc;
	
	public void setup() {
		/* set Google Chrome driver */ 
		setChromeDriver();
		
		dc = DesiredCapabilities.chrome();
		ChromeDriverService service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("C:\\Program Files\\Java\\drivers\\chromedriver.exe"))
				.usingAnyFreePort()
				.build();
		ChromeOptions options = new ChromeOptions();
		options.merge(dc);
		options.addArguments("--start-maximized");
		webDriver = new ChromeDriver(service, options);
		
		/* set FireFox driver */
		setFFDriver();
		
		System.setProperty("webdriver.gecko.driver",
				"C:\\Program Files\\Java\\drivers\\gekodriver.exe");
		FirefoxOptions fo = new FirefoxOptions();
		fo.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
		webDriver = new FirefoxDriver(fo); 
		
		webDriver.manage().window().maximize();
		webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	}

	public void tearDown() {
		webDriver.quit();
	}
	
	private static void setChromeDriver() {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuffer chromeBinaryPath = new StringBuffer("src/main/resources/drivers/");

		if (osName.startsWith("win")) {
			chromeBinaryPath.append("chrome-win/chromedriver.exe");
		} else if (osName.startsWith("lin")) {
			chromeBinaryPath.append("chrome-lin/chromedriver");
		} else if (osName.startsWith("mac")) {
			chromeBinaryPath.append("chrome-mac/chromedriver");
		} else
			throw new IllegalStateException("Your OS is invalid for webdriver tests");

		System.setProperty("webdriver.chrome.driver", chromeBinaryPath.toString());
	}

	private static void setFFDriver() {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuffer firefoxBinary = new StringBuffer("src/main/resources/drivers/");

		if (osName.startsWith("win")) {
			firefoxBinary.append("ff-win/geckodriver.exe");
		} else if (osName.startsWith("lin")) {
			firefoxBinary.append("ff-lin/geckodriver");
		} else if (osName.startsWith("mac")) {
			firefoxBinary.append("ff-mac/geckodriver");
		} else
			throw new IllegalStateException("Your OS is invalid for webdriver tests");

		System.setProperty("webdriver.gecko.driver", firefoxBinary.toString());
	}

}
