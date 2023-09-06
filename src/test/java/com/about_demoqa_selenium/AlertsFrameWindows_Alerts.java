package com.about_demoqa_selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class AlertsFrameWindows_Alerts {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties properties;
	@BeforeTest
	public void setUp() {
		properties = new Properties();
		String configFilePath = "E:\\priyanka\\e\\02.07.2023\\about_demoqa_selenium\\src\\test\\java\\alertsFrameWindows_Alerts.properties";
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
	public void alerts() {

		String demoqaAlertsFrameAndWindows_AlertsUrl = properties.getProperty("demoqaAlertsFrameAndWindows_AlertsUrl");
		String alertsButton = properties.getProperty("alertsButton");
		String seeMeAlert = properties.getProperty("seeMeAlert");
		String confirmButton= properties.getProperty("confirmButton");
		String alertMessage = properties.getProperty("alertMessage");
		String promtButton = properties.getProperty("promtButton");
		String promptAlertMessage = properties.getProperty("promptAlertMessage");
		String alertTextIs = properties.getProperty("alertTextIs");



		driver.get(properties.getProperty("demoqaAlertsFrameAndWindows_AlertsUrl"));
		driver.findElement(By.xpath(alertsButton)).click();
		driver.findElement(By.id(seeMeAlert)).click();
		Alert simpleAlert = driver.switchTo().alert();
		simpleAlert.accept();



		try {
			JavascriptExecutor js = (JavascriptExecutor) driver;
			js.executeScript("document.getElementById('timerAlertButton').click();");
			Thread.sleep(5000);
			Alert alert = driver.switchTo().alert();
			alert.accept();
		} catch (Exception e) {
			e.printStackTrace();
		}



		WebElement element = driver.findElement(By.id(confirmButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", element);
		Alert confirmationAlert = driver.switchTo().alert();
		String alertText = confirmationAlert.getText();
		System.out.println(alertMessage + alertText);
		confirmationAlert.accept();




		WebElement element1 = driver.findElement(By.id(promtButton));
		((JavascriptExecutor) driver).executeScript("arguments[0].click()", element1);
		Alert promptAlert  = driver.switchTo().alert();
		String alertText1 = promptAlert.getText();
		System.out.println(promptAlertMessage + alertText1);
		promptAlert.sendKeys(alertTextIs);
		promptAlert.accept();

	}
	@AfterMethod
	private void closeApp() {
		driver.close();
	}
}