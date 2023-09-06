package com.about_demoqa_selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class ElementsUploadAndDownload {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties properties;
	@BeforeTest
	public void setUp() {
		properties = new Properties();
		String configFilePath = "E:\\priyanka\\e\\02.07.2023\\about_demoqa_selenium\\src\\test\\java\\elementsUploadAndDownload.properties";
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
	public void elementsUploadAndDownload() {

		String demoqaElementsUrl = properties.getProperty("demoqaElementsUrl");
		String uploadDownload = properties.getProperty("uploadDownload");
		String download = properties.getProperty("download");
		String downloadMessage = properties.getProperty("downloadMessage");
		String uploadFile= properties.getProperty("uploadFile");
		String filePathIs = properties.getProperty("filePathIs");
		String uploadMessage = properties.getProperty("uploadMessage");


		driver.get(properties.getProperty("demoqaElementsUrl"));
		JavascriptExecutor js = (JavascriptExecutor) driver;
		int scrollPixels = 500;
		js.executeScript("window.scrollBy(100, " + scrollPixels + ");");
		driver.findElement(By.xpath(uploadDownload)).click();
		WebElement downloadButton = driver.findElement(By.linkText(download));
		downloadButton.click();
		System.out.println(downloadMessage);
		WebElement fileInput = driver.findElement(By.id(uploadFile));
		String filePath = filePathIs;
		fileInput.sendKeys(filePath);
		System.out.println(uploadMessage);

	}
	@AfterMethod
	private void closeApp() {
		driver.close();
	}
}
