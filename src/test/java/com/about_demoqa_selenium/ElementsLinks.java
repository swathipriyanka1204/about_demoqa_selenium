package com.about_demoqa_selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementsLinks {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties properties;
	@BeforeTest
	public void setUp() {
		properties = new Properties();
		String configFilePath = "E:\\priyanka\\e\\02.07.2023\\about_demoqa_selenium\\src\\test\\java\\elementsLinks.properties";
		FileInputStream fis;
		try {
			fis = new FileInputStream(configFilePath);
			properties.load(fis);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		String chromeDriverPath = properties.getProperty("webdriver.chrome.driver");
		System.setProperty("webdriver.chrome.driver", chromeDriverPath);
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
	}

	@Test(priority=1)
	public void links() {

		String demoqaElementsUrl = properties.getProperty("demoqaElementsUrl");
		String linkButton = properties.getProperty("linkButton");
		String simpleLink = properties.getProperty("simpleLink");
		String dynamicLink = properties.getProperty("dynamicLink");
		String created= properties.getProperty("created");
		String noContent = properties.getProperty("noContent");
		String moved = properties.getProperty("moved");
		String badRequest = properties.getProperty("badRequest");
		String unauthorized = properties.getProperty("unauthorized");
		String forbidden = properties.getProperty("forbidden");
		String invalidUrl = properties.getProperty("invalidUrl");
		String get = properties.getProperty("get");

		driver.get(properties.getProperty("demoqaElementsUrl"));
		driver.findElement(By.xpath(linkButton)).click();
		WebElement link = driver.findElement(By.id(simpleLink));
		link.click();

		String originalWindowHandle = driver.getWindowHandle();
		Set<String> windowHandles = driver.getWindowHandles();
		for (String windowHandle : windowHandles) {
			if (!windowHandle.equals(originalWindowHandle)) {
				driver.switchTo().window(originalWindowHandle);
				break;
			}
		}
		WebElement link1 = driver.findElement(By.id(dynamicLink));
		link1.click();

		String originalWindowHandle1 = driver.getWindowHandle();
		Set<String> windowHandles1 = driver.getWindowHandles();
		for (String windowHandle1 : windowHandles1) {
			if (!windowHandle1.equals(originalWindowHandle1)) {
				driver.switchTo().window(originalWindowHandle1);
				break;
			}
		}

		driver.findElement(By.id(created)).click();
		driver.findElement(By.id(noContent)).click();
		driver.findElement(By.id(moved)).click();
		driver.findElement(By.id(badRequest)).click();
		driver.findElement(By.id(unauthorized)).click();
		driver.findElement(By.id(forbidden)).click();
		driver.findElement(By.id(invalidUrl)).click();

		String[] linkIds = {created, noContent, moved, badRequest, unauthorized, forbidden, invalidUrl};

		for (String linkId : linkIds) {

			WebElement link2 = driver.findElement(By.id(linkId));
			link2.click();

			try {

				URL apiUrl = new URL("https://example.com/api/" + linkId);
				HttpURLConnection connection = (HttpURLConnection) apiUrl.openConnection();
				connection.setRequestMethod(get);
				int responseCode = connection.getResponseCode();
				String statusText = connection.getResponseMessage();
				System.out.println("Link with ID '" + linkId + "' - API Response Code: " + responseCode);
				System.out.println("Link with ID '" + linkId + "' - API Status Text: " + statusText);
				if (responseCode == 201 && "Created".equals(statusText)) {
					System.out.println("Link with ID '" + linkId + "' has responded with status 201 and status text 'Created'.");
				} else {
					System.out.println("Link with ID '" + linkId + "' did not respond as expected.");
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	@AfterMethod
	private void closeApp() {
		driver.close();
	}
}
