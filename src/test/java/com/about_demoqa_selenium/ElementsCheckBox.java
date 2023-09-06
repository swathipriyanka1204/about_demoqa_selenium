package com.about_demoqa_selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementsCheckBox {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties properties;
	@BeforeTest
	public void setUp() {
		properties = new Properties();
		String configFilePath = "E:\\priyanka\\e\\02.07.2023\\about_demoqa_selenium\\src\\test\\java\\elementsCheckBox.properties";
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
	public void checkBoxButton() {

		String demoqaElementsUrl = properties.getProperty("demoqaElementsUrl");
		String checkBox = properties.getProperty("checkBox");
		String checkBoxClick = properties.getProperty("checkBoxClick");


		driver.get(properties.getProperty("demoqaElementsUrl"));
		driver.findElement(By.xpath(checkBox)).click();
		WebElement checkbox = driver.findElement(By.className(checkBoxClick));
		checkbox.click();

	}
	@AfterMethod
	private void closeApp() {
		driver.close();
	}
}
