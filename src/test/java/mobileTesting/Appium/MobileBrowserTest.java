package mobileTesting.Appium;

import java.io.File;

import java.net.URL;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.testng.annotations.Test;

import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class MobileBrowserTest {

	@Test
	public void browserTest() throws Exception {
		AppiumDriverLocalService server = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\sivaraj.m\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).withTimeout(Duration.ofSeconds(300)).build();
		server.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("TestingEmulator");
		options.setChromedriverExecutable("D:\\siva\\Appium\\chromedriver_win32\\chromedriver.exe");
		options.setCapability("browserName", "Chrome");

		AndroidDriver driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		
		driver.get("https://google.com");
		driver.findElement(By.name("q")).sendKeys("Sivaraj", Keys.ENTER);

	}
	
}
