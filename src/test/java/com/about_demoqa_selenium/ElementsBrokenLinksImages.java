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

public class ElementsBrokenLinksImages {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties properties;
	@BeforeTest
	public void setUp() {
		properties = new Properties();
		String configFilePath = "E:\\priyanka\\e\\02.07.2023\\about_demoqa_selenium\\src\\test\\java\\elementsBrokenLinksImages.properties";
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
	public void brokenLinksImages() {

		String demoqaElementsUrl = properties.getProperty("demoqaElementsUrl");
		String brokenLinksImagesButton = properties.getProperty("brokenLinksImagesButton");
		String validImage = properties.getProperty("validImage");
		String validImageClick = properties.getProperty("validImageClick");
		String src= properties.getProperty("src=");
		String alt = properties.getProperty("alt");
		String imageSourceMessage1 = properties.getProperty("imageSourceMessage1");
		String imageSourceMessage2 = properties.getProperty("imageSourceMessage2");
		String altTextMessage1 = properties.getProperty("altTextMessage1");
		String altTextMessage2 = properties.getProperty("altTextMessage2");
		String brokenImage = properties.getProperty("brokenImage");
		String brokenImageMessage1 = properties.getProperty("brokenImageMessage1");
		String validImage2 = properties.getProperty("validImage2");
		String validImageMessage2 = properties.getProperty("validImageMessage2");
		String validLink = properties.getProperty("validLink");
		String validLinkMessage= properties.getProperty("validLinkMessage");
		String validLinkClick = properties.getProperty("validLinkClick");
		String validLinkClickMessage = properties.getProperty("validLinkClickMessage");
		String brokenLink = properties.getProperty("brokenLink");
		String brokenLinkMessage = properties.getProperty("brokenLinkMessage");
		String brokenLinkClick = properties.getProperty("brokenLinkClick");
		String brokenLinkClickMessage = properties.getProperty("brokenLinkClickMessage");



		driver.get(properties.getProperty("demoqaElementsUrl"));
		driver.findElement(By.xpath(brokenLinksImagesButton)).click();
		WebElement validImageElement = driver.findElement(By.xpath(validImage));
		validImageElement.click();
		WebElement imageElement = driver.findElement(By.xpath(validImageClick));
		String srcUrl = imageElement.getAttribute("src");
		String altText = imageElement.getAttribute("alt");
		if (!srcUrl.isEmpty()) {
			System.out.println(imageSourceMessage1 + srcUrl);
		} else {
			System.err.println(imageSourceMessage2);
		}

		if (!altText.isEmpty() && !altText.equals(" ")) {
			System.out.println(altTextMessage1 + altText);
		} else {
			System.err.println(altTextMessage2);
		}

		WebElement brokenImageElement = driver.findElement(By.xpath(brokenImage));
		brokenImageElement.click();
		System.out.println(brokenImageMessage1);
		WebElement secondValidImageElement = driver.findElement(By.xpath(validImage2));
		secondValidImageElement.click();
		System.out.println(validImageMessage2); 


		WebElement validLinkElement = driver.findElement(By.xpath(validLink));
		validLinkElement.click();
		System.out.println(validLinkMessage);
		String mainwindow = driver.getWindowHandle();
		Set<String> s1 = driver.getWindowHandles();
		Iterator<String> i1 = s1.iterator();

		while (i1.hasNext()) {
			String ChildWindow = i1.next();
			if (!mainwindow.equalsIgnoreCase(ChildWindow)) {
				driver.switchTo().window(ChildWindow);
				WebElement text = driver.findElement(By.linkText(validLinkClick));
				System.out.println(text.getText());
				driver.close();

			}
		}    
		driver.switchTo().window(mainwindow);
		WebElement brokenLinkElement = driver.findElement(By.xpath(brokenLink));
		brokenLinkElement.click();
		System.out.println(brokenLinkMessage);
		WebElement linkWithTextElement1 = driver.findElement(By.xpath(brokenLinkClick));
		linkWithTextElement1.click();
		System.out.println(brokenLinkClickMessage);
	}
	@AfterMethod
	private void closeApp() {
		driver.close();
	}

}
