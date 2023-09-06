package com.about_demoqa_selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Iterator;
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

public class AlertsFrameAndWindows_BrowserWindows {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties properties;
	@BeforeTest
	public void setUp() {
		properties = new Properties();
		String configFilePath = "E:\\priyanka\\e\\02.07.2023\\about_demoqa_selenium\\src\\test\\java\\alertsFrameAndWindows_BrowserWindows.properties";
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
	public void browserWindowsOption() {

		String demoqaAlertsFrameAndWindows_BrowserWindowsUrl = properties.getProperty("demoqaAlertsFrameAndWindows_BrowserWindowsUrl");
		String browserWindows = properties.getProperty("browserWindows");
		String tabButton = properties.getProperty("tabButton");
		String sampleHeading = properties.getProperty("sampleHeading");
		String windowMessage1= properties.getProperty("windowMessage1");
		String windowMessage2 = properties.getProperty("windowMessage2");
		String windowButton = properties.getProperty("windowButton");
		String messageWindowButton = properties.getProperty("messageWindowButton");
		String mainWindowMessage1 = properties.getProperty("mainWindowMessage1");
		String mainWindowMessage2 = properties.getProperty("mainWindowMessage2");
		String mainWindowMessage3 = properties.getProperty("mainWindowMessage3");


		driver.get(properties.getProperty("demoqaAlertsFrameAndWindows_BrowserWindowsUrl"));
		driver.findElement(By.xpath(browserWindows)).click();
		driver.findElement(By.id(tabButton)).click();
		String mainwindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				WebElement text = driver.findElement(By.id(sampleHeading));
				System.out.println(windowMessage1 + text.getText());
				driver.close();
				System.out.println(windowMessage2);
			}
		}    
		driver.switchTo().window(mainwindow);




		driver.findElement(By.id(windowButton)).click();
		driver.findElement(By.id(messageWindowButton)).click();


		String MainWindow = driver.getWindowHandle();
		System.out.println(mainWindowMessage1 + MainWindow);
		Set<String> s2 = driver.getWindowHandles();
		System.out.println(mainWindowMessage2 + s2);
		Iterator<String> i2 = s2.iterator();
		while (i2.hasNext()) {
			String ChildWindow = i2.next();
			if (!MainWindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				driver.close();
				System.out.println(mainWindowMessage3);
			}
		}

	}
	@AfterMethod
	private void closeApp() {
		driver.close();
	}
}
