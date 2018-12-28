package FirstAppium;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.When;
import io.appium.java_client.android.AndroidDriver;
import cucumber.api.java.en.Then;
import static org.junit.Assert.*;

import java.io.File;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.remote.DesiredCapabilities;

public class Stepdefs {
	AndroidDriver driver;
	
	@Given("^I am staying calculator app$")
	public void i_am_staying_calculator_app() throws Exception {
		DesiredCapabilities cap = new DesiredCapabilities();
		//Nền tảng test, Android or Iphone
        cap.setCapability("platformName", "ANDROID");
        //Thiết bị chạy test - Lấy qua lệnh ADB Devices
        cap.setCapability("deviceName", "37304c3258543398");
        //Các thông số của App muốn Test
        //Để lấy được các thông số này cần mở App trên device sau đó chạy các lệnh sau:
        //adb shell
        //dumpsys window windows|grep -E 'mCurrentFocus'
        cap.setCapability("appPackage", "com.sec.android.app.popupcalculator");
        cap.setCapability("appActivity","com.sec.android.app.popupcalculator.Calculator");
        //Khởi tạo Android Driver với profile ở trên
        driver = new AndroidDriver(new URL(
                "http://localhost:4723/wd/hub"), cap);
        //Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@When("^I input the (\\d+) and number (\\d+)$")
	public void i_input_the_and_number(int number1, int number2) throws Exception {
		//Lấy các locator thông qua tools: Sdk\\tools\\bin\\uiautomatorviewer.bat
		driver.findElementById("com.sec.android.app.popupcalculator:id/bt_0"+ number1).click();
		driver.findElementById("com.sec.android.app.popupcalculator:id/bt_add").click();
		driver.findElementById("com.sec.android.app.popupcalculator:id/bt_0"+ number2).click();
	}

	@Then("^I should see the result (\\d+) of addition calculate$")
	public void i_should_see_the_result_of_addition_calculate(int result) throws Exception {
		Assert.assertEquals(result, driver.findElementById("com.sec.android.app.popupcalculator:id/txtCalc_RealTimeResult").getText());
	}
}