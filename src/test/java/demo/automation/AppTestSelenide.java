package demo.automation;

import static com.codeborne.selenide.Condition.*;
import com.codeborne.selenide.Configuration;
import org.apache.commons.lang3.RandomStringUtils;
import org.openqa.selenium.By;
import org.testng.annotations.Test;

import static com.codeborne.selenide.Selectors.*;
import static com.codeborne.selenide.Selenide.*;

public class AppTestSelenide {

    @Test
    public void testRegistrationPass (){
        //set new browser driver. By default = FF, if FF < 48 version
        Configuration.browser = "chrome";
        System.setProperty("webdriver.chrome.driver",
                "C:\\Program Files\\Java\\drivers\\chromedriver.exe");

        open ("http://demoqa.com");
        sleep (5000);
        //findByID
        $("#menu-item-374").click();
        //$(byXpath(".//*[@id='menu-item-374']/a")).click();
        $("#post-49").shouldBe(visible);
        $("#name_3_firstname").setValue("Olena");
        $("#name_3_lastname").setValue("Bulat");
        $(byAttribute("value","single")).setSelected(true);
        $(byAttribute("value","reading")).setSelected(true);
        $("#dropdown_7").selectOption("Ukraine");

        $("#mm_date_8").selectOption(9);
        $("#dd_date_8").selectOption(9);
        $("#yy_date_8").selectOption("1993");//need scroll

        $(byName("phone_9")).setValue("380930060517");
        //$("#phone_9").setValue("+380930060517");

       // $("#username").setValue("OlenaBulat");
        //$("#email_1").setValue("olena.testmail@gmail.com");
        String username = "OlenaBulat" + RandomStringUtils.randomNumeric(3);
        $("#username").setValue(username);
        $("#email_1").setValue(username + "@gmail.com");

        //set image
        $("#profile_pic_10").setValue("C:\\Users\\Olena\\Downloads\\Personal\\blank-calendar-10.gif");

        $("#password_2").setValue("1234567890");
        $("#confirm_password_password_2").setValue("1234567890");

        //byClass
        $(".fieldset.piereg_submit_button").findElement(By.tagName("input")).click();
        $(byClassName("piereg_message")).shouldBe(visible);


    }

}
