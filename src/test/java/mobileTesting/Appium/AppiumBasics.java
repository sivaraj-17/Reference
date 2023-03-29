package mobileTesting.Appium;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.appium.java_client.AppiumBy;

public class AppiumBasics extends BaseClass {

	@Test(priority = 1, enabled = false)
	public void appiumTest() throws Exception {

		// Xpath, id, accessibilityId, classname, androidUIAutomator
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Preference\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		String name = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertTrue(name.equals("WiFi settings"), "AsserFail");
		driver.findElement(By.id("android:id/edit")).sendKeys("Sivaraj");
		driver.findElement(By.id("android:id/button1")).click();

		driver.quit();
		server.stop();
	}

	@Test(priority = 2, enabled = false)
	public void longPressAndScrollDown() throws Exception {

		// Xpath, id, accessibilityId, classname, androidUIAutomator
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(AppiumBy.accessibilityId("Expandable Lists")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc='1. Custom Adapter']")).click();

		// LongPress code
		WebElement longClick = driver.findElement(By.xpath("//android.widget.TextView[@text='People Names']"));
		longPress(longClick);
		Thread.sleep(5000);

		String getText = driver.findElement(By.id("android:id/title")).getText();
		Assert.assertTrue(getText.equals("Sample menu"), "No");
		System.out.println(
				"---------------------------------------------------------------------------------------------------------------------------");
		// Scroll Down
		scrollUntilEnd();
		Thread.sleep(2000);

		tearDown();
	}

	@Test(priority = 3, enabled = true)
	public void swipeRight() throws Exception {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Gallery\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"1. Photos\"]")).click();
		driver.findElement(By.xpath("(//android.widget.ImageView)[1]")).click();
		WebElement get = driver.findElement(By.xpath("(//android.widget.ImageView)[3]"));
		get.getAttribute("focusable");
		swipeOptions(get, "left");
		WebElement gets = driver.findElement(By.xpath("(//android.widget.ImageView)[1]"));
		swipeOptions(gets, "right");
		Assert.assertTrue(gets.getAttribute("focusable").equals("true"), "");

		tearDown();
	}

	@Test(priority = 4, enabled = true)
	public void dragDrop() throws Exception {
		driver.findElement(AppiumBy.accessibilityId("Views")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Drag and Drop\"]")).click();
		WebElement element = driver.findElement(By.id("io.appium.android.apis:id/drag_dot_1"));
		dragAndDrop(element);
		Thread.sleep(3000);
		Assert.assertTrue(driver.findElement(By.id("io.appium.android.apis:id/drag_result_text")).isDisplayed(),
				"Drag and drop not perform!");

		tearDown();
	}

	@Test(priority = 5, enabled = true)
	public void rotateDevices() throws Exception {
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"Preference\"]")).click();
		driver.findElement(By.xpath("//android.widget.TextView[@content-desc=\"3. Preference dependencies\"]")).click();
		driver.findElement(By.id("android:id/checkbox")).click();

		// Rotate device
		rotateDevice(0, 0, 90);
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		Thread.sleep(3000);
		driver.setClipboardText("Sivaraj");
		String name = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertTrue(name.equals("WiFi settings"), "AsserFail");
		driver.findElement(By.id("android:id/edit")).sendKeys(driver.getClipboardText());
		driver.findElement(By.id("android:id/button1")).click();

		tearDown();
	}

	@Test(priority = 6, enabled = true)
	public void navigateDirectToPage() throws Exception {
		navigateToPage("io.appium.android.apis", "io.appium.android.apis.preference.PreferenceDependencies");
		driver.findElement(By.id("android:id/checkbox")).click();
		driver.findElement(By.xpath("(//android.widget.RelativeLayout)[2]")).click();
		Thread.sleep(3000);
		driver.setClipboardText("Sivaraj");
		String name = driver.findElement(By.id("android:id/alertTitle")).getText();
		Assert.assertTrue(name.equals("WiFi settings"), "AsserFail");

	}

}
