package demo.automation;


import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;

/**
 * A factory that returns a singleton of WebDriver object.
 */
public class DriversSetUp {

	private static final String CHROME = "chrome";
	private static final String FIREFOX = "firefox";
	private static final String EDGE = "edge";

	private static WebDriver webDriver;
	private static DesiredCapabilities dc;

	private DriversSetUp () {

	}

	/**
	 * Gets the single instance of WebDriverFactory.
	 *
	 * @param browser the browser set in properties
	 * @return single instance of WebDriverFactory
	 * @throws Exception the exception of invalid browser property
	 */
	public static WebDriver getInstance(String browser) {
		if (webDriver == null) {
			if (CHROME.equals(browser)) {
				
				/** set Google Chrome driver */ 
				setChromeDriver();
				
				dc = DesiredCapabilities.chrome();
				ChromeDriverService service = new ChromeDriverService.Builder()
						.usingDriverExecutable(new File("C:\\Program Files\\Java\\drivers\\chromedriver.exe"))
						.usingAnyFreePort()
						.build();
				ChromeOptions options = new ChromeOptions();
				options.addExtensions(new File("adblock.crx"));
				options.addArguments("test-type");
				//options.setBinary(new File("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe"));
				
				/** if needs to use user profile with special settings use user Profile and user-data-dir arg */
				/*String userProfile= ("C:\\Users\\Olena\\AppData\\Local\\Google\\Chrome\\User Data\\Default" - path/to/settings/folder);
				options.addArguments("user-data-dir="+userProfile); */
				
				options.addArguments("--start-maximized");
			    dc.setCapability(ChromeOptions.CAPABILITY, options); 
			    webDriver = new ChromeDriver(service, options); 
			    
			    
			} else if (FIREFOX.equals(browser)) {
				
				/** set FireFox driver */
				setFFDriver();
				
				System.setProperty("webdriver.gecko.driver",
						"C:\\Program Files\\Java\\drivers\\geckodriver.exe");
				FirefoxOptions fo = new FirefoxOptions();
				fo.setBinary("C:\\Program Files\\Mozilla Firefox\\firefox.exe");
				webDriver = new FirefoxDriver(fo);
				
			} else if (EDGE.equals(browser)) {
				
				/** set Edge Microsoft driver */
				setEdgeDriver();
				
				System.setProperty("webdriver.edge.driver", "C:\\Program Files\\Java\\drivers\\MicrosoftWebDriver.exe");
				webDriver = new EdgeDriver();
				
			} else throw new IllegalArgumentException("Invalid browser property set in configuration file");
			
			webDriver.manage().window().maximize();
			webDriver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		}

		return webDriver;
	}

	/**
	 * Kill driver instance.
	 * @throws Exception 
	 */
	public static void killDriverInstance() {
		if (webDriver != null) {
			webDriver.quit();
			webDriver = null;
		}
	}

	/**
	 * Sets the chrome driver path for specific OS.
	 *
	 * @throws Exception the exception
	 */
	private static void setChromeDriver() {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuffer chromeBinaryPath = new StringBuffer(
				"src/main/resources/drivers/");

		if (osName.startsWith("win")) {
			chromeBinaryPath.append("chrome-win/chromedriver.exe");
		} /*else if (osName.startsWith("lin")) {
			chromeBinaryPath.append("chrome-lin/chromedriver");
		} */ else if (osName.startsWith("mac")) {
			chromeBinaryPath.append("chrome-mac/chromedriver.exe");
		} else
			throw new IllegalStateException("Your OS is invalid for webdriver tests");

		System.setProperty("webdriver.chrome.driver",
				chromeBinaryPath.toString());
	}
	
	private static void setFFDriver() {
		String osName = System.getProperty("os.name").toLowerCase();
		StringBuffer firefoxBinary = new StringBuffer(
				"src/main/resources/drivers/");

		if (osName.startsWith("win")) {
			firefoxBinary.append("ff-win/geckodriver.exe");
		} /*else if (osName.startsWith("lin")) {
			firefoxBinary.append("ff-lin/geckodriver");
		} else if (osName.startsWith("mac")) {
			firefoxBinary.append("ff-mac/geckodriver");
		}*/ else
				throw new IllegalStateException("Your OS is invalid for webdriver tests");

		System.setProperty("webdriver.gecko.driver",
				firefoxBinary.toString());
	}
	
	private static void setEdgeDriver() {
		String osName = System.getProperty("os.name").toLowerCase();
		/*can't use String, because is using .append*/
		StringBuffer edgeBinary = new StringBuffer(
				"src/main/resources/drivers/");
		
		if (osName.startsWith("win")) {
			edgeBinary.append("edge-win/MicrosoftWedDriver.exe");
		} else
			throw new IllegalStateException("Your OS is invalid for webdriver tests");

		System.setProperty("webdriver.edge.driver",
				edgeBinary.toString());
	}

}