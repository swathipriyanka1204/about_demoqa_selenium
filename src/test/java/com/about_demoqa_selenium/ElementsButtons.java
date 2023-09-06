package com.about_demoqa_selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementsButtons {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties properties;
	@BeforeTest
	public void setUp() {
		properties = new Properties();
		String configFilePath = "E:\\priyanka\\e\\02.07.2023\\about_demoqa_selenium\\src\\test\\java\\elementsButtons.properties";
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
	public void Buttons() {

		String demoqaElementsUrl = properties.getProperty("demoqaElementsUrl");
		String buttonsOption = properties.getProperty("buttonsOption");
		String doubleClick = properties.getProperty("doubleClick");
		String rightClick = properties.getProperty("rightClick");
		String clickMe= properties.getProperty("clickMe");

		driver.get(properties.getProperty("demoqaElementsUrl"));
		driver.findElement(By.xpath(buttonsOption)).click();
		WebElement doubleClickMeButton = driver.findElement(By.id(doubleClick)); 
		Actions actions = new Actions(driver);
		actions.doubleClick(doubleClickMeButton).perform();

		WebElement rightClickMeButton = driver.findElement(By.id(rightClick)); 
		Actions actions1 = new Actions(driver);
		actions1.contextClick(rightClickMeButton).perform();

		WebElement clickMeButton = driver.findElement(By.xpath(clickMe));
		clickMeButton.click();

	}
	@AfterMethod
	private void closeApp() {
		driver.close();
	}

}
