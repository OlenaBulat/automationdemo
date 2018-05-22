package demo.automation;


import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

import org.testng.annotations.Test;
import com.codeborne.selenide.Configuration;

public class AppTestSelenidet3kit {
	@Test
    public void Subscribe (){
        //Chrome driver
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver",
               "C:\\Program Files\\Java\\drivers\\chromedriver.exe");
		
        //FF diver
        /*Configuration.browser = "firefox";
        System.setProperty("webdriver.firefox.driver",
                "C:\\Program Files\\Java\\drivers\\geckodriver.exe");*/

        open ("http://demo.t3kit.com/");
        $("#email").scrollTo();
        $(".subscribe").click();
        $(".alert").getText().contains("Invalid email-address.");

    }


}