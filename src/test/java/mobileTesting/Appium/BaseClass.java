package mobileTesting.Appium;

import java.io.File;



import java.net.URL;

import org.openqa.selenium.DeviceRotation;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebElement;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

import com.google.common.collect.ImmutableMap;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.Activity;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.service.local.AppiumDriverLocalService;
import io.appium.java_client.service.local.AppiumServiceBuilder;

public class BaseClass {

	public AppiumDriverLocalService server;
	public AndroidDriver driver;

	@BeforeClass
	public void initSetUp() throws Exception {
		// help to start server in code
		server = new AppiumServiceBuilder()
				.withAppiumJS(new File("C:\\Users\\sivaraj.m\\AppData\\Roaming\\npm\\node_modules\\appium\\build\\lib\\main.js"))
				.withIPAddress("127.0.0.1").usingPort(4723).build();
		server.start();

		UiAutomator2Options options = new UiAutomator2Options();
		options.setDeviceName("TestingEmulator");
		options.setApp("D:\\siva\\Appium-Wrokspaces\\Appium\\src\\test\\java\\resources\\ApiDemos-debug.apk");

		driver = new AndroidDriver(new URL("http://127.0.0.1:4723"), options);
	}

	public void longPress(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: longClickGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), "duration", 2000));
	}

	public void scrollDown(String scroll) {

		driver.findElement(AppiumBy
				.androidUIAutomator("new UiScrollable(new UiSelector()).scrollIntoView(text(\"" + scroll + "\"));"));
	}

	public void scrollDown() {

		boolean b = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
				.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		System.out.println(b);
	}

	public void scrollUntilEnd() {
		boolean scrollBottom;
		do {

			scrollBottom = (Boolean) ((JavascriptExecutor) driver).executeScript("mobile: scrollGesture", ImmutableMap
					.of("left", 100, "top", 100, "width", 200, "height", 200, "direction", "down", "percent", 3.0));
		} while (scrollBottom);
	}

	public void swipeOptions(WebElement get, String direction) throws Exception {
		Thread.sleep(2000);
		do {
			((JavascriptExecutor) driver).executeScript("mobile: swipeGesture",
					ImmutableMap.of("elementId", ((RemoteWebElement) get).getId(), "direction", direction, "percent", 1.0));
		}while(get.equals("true"));
	}

	public void dragAndDrop(WebElement element) {
		((JavascriptExecutor) driver).executeScript("mobile: dragGesture",
				ImmutableMap.of("elementId", ((RemoteWebElement) element).getId(), 
						"endX", 619,
						"endY", 560));
	}

	public void rotateDevice(int x, int y, int z) {
		DeviceRotation deviceRotation = new DeviceRotation(x, y, z);
		driver.rotate(deviceRotation);
	}

	public void navigateToPage(String appPackage, String appActivity) {
		Activity activity = new Activity(appPackage,appActivity);
		driver.startActivity(activity);
	}

	@AfterClass
	public void tearDown() {
		driver.quit();
		server.stop();
	}
}
