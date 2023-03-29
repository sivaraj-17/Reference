package mobileTesting.Appium;

import java.io.File;


import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService; 
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class Assignment {


	@Test(priority = 1, enabled = true)
	public void initSetUp() throws Exception {
		//AppiumDriverLocalService - This class is used to start and stop the Appium server programmatically.
		
		AppiumDriverLocalService server = new AppiumServiceBuilder()    //AppiumServiceBuilder allows you to set various properties of the Appium server
				
				.withAppiumJS(new File("C:\\Users\\sivaraj.m\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				
				.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
		
		server.start();

		UiAutomator2Options options = new UiAutomator2Options();        //set various options and capabilities for the UiAutomator2 driver, such as the app package, app activity, automation name, and other driver-specific settings.
		options.setDeviceName("TestingEmulator");
		options.setChromedriverExecutable("D:\\siva\\Appium\\chromedriver_win32\\chromedriver.exe");
		options.setApp("D:\\siva\\Appium-Wrokspaces\\Appium\\src\\test\\java\\resources\\General-Store.apk");

		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		// driver.quit();
		// server.stop();

		Thread.sleep(5000);
		driver.findElement(By.id("android:id/text1")).click();
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"Australia\"));"));
		Thread.sleep(3000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Australia']")).click();
//		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		// Toast message validation
//		String errorMsg = driver.findElement(By.xpath("//android.widget.Toast[1]")).getText();
//		System.out.println(errorMsg);
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter name here']")).click();
		driver.findElement(By.xpath("//android.widget.EditText[@text='Enter name here']")).sendKeys("Sivaraj");
		driver.hideKeyboard();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/radioMale")).click();
		driver.findElement(AppiumBy.id("com.androidsample.generalstore:id/btnLetsShop")).click();
		Thread.sleep(5000);
		String text = driver.findElement(By.id("com.androidsample.generalstore:id/toolbar_title")).getText();
		Assert.assertTrue(text.equals("Products"));
		driver.findElement(AppiumBy.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"$115.0\"));"));
		Thread.sleep(3000);
		String na = driver.findElement(By.xpath("//android.widget.TextView[@text='Jordan Lift Off']//following-sibling::android.widget.LinearLayout//child::android.widget.TextView[@text='ADD TO CART']")).getText();
		System.out.println(na);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Jordan Lift Off']//following-sibling::android.widget.LinearLayout//child::android.widget.TextView[@text='ADD TO CART']")).click();
		Thread.sleep(2000);
		String count = driver.findElement(By.id("com.androidsample.generalstore:id/counterText")).getText();
		System.out.println("Add to cart count!" + count);
		driver.findElement(By.id("com.androidsample.generalstore:id/appbar_btn_cart")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath("//android.widget.TextView[@text='Cart']")).isDisplayed();

		driver.findElement(By.xpath("//android.widget.LinearLayout/android.widget.CheckBox")).click();
		driver.findElement(By.id("com.androidsample.generalstore:id/btnProceed")).click();
		Thread.sleep(3000);
		
		Set<String> context = driver.getContextHandles();
		for (String string : context) {
			System.out.println(string);
		}
		driver.context("WEBVIEW_com.androidsample.generalstore");
		driver.findElement(By.name("q")).sendKeys("Cristiano ronaldo",Keys.ENTER);
		
//		driver.findElement(By.id("com.androidsample.generalstore:id/webView")).isDisplayed();
	}
}
