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

public class WidgetsAccordian {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties properties;
	@BeforeTest
	public void setUp() {
		properties = new Properties();
		String configFilePath = "E:\\priyanka\\e\\02.07.2023\\about_demoqa_selenium\\src\\test\\java\\widgetsAccordian.properties";
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
	public void widgetsAccordian() {

		String demoqaWidgetsAccordianUrl = properties.getProperty("demoqaWidgetsAccordianUrl");
		String widgets = properties.getProperty("widgets");
		String accordian = properties.getProperty("accordian");
		String section2Heading= properties.getProperty("section2Heading");
		String section2Content = properties.getProperty("section2Content");
		String dataUnderSection2Content = properties.getProperty("dataUnderSection2Content");
		String section1Heading = properties.getProperty("section1Heading");
		String section1Content = properties.getProperty("section1Content");
		String dataUnderSection1Content= properties.getProperty("dataUnderSection1Content");
		String section3Heading = properties.getProperty("section3Heading");
		String section3Content = properties.getProperty("section3Content");
		String dataUnderSection3Content = properties.getProperty("dataUnderSection3Content");



		driver.get(properties.getProperty("demoqaWidgetsAccordianUrl"));
		driver.findElement(By.xpath(widgets)).click();
		driver.findElement(By.xpath(accordian)).click();

		driver.findElement(By.id(section2Heading)).click();
		WebElement sectionContent2 = driver.findElement(By.id(section2Content));
		String sectionText2 = sectionContent2.getText();
		System.out.println(dataUnderSection2Content + sectionText2);

		driver.findElement(By.id(section1Heading)).click();
		WebElement sectionContent1 = driver.findElement(By.id(section1Content));
		String sectionText1 = sectionContent1.getText();
		System.out.println(dataUnderSection1Content + sectionText1);

		driver.findElement(By.id(section3Heading)).click();
		WebElement sectionContent3 = driver.findElement(By.id(section3Content));
		String sectionText3 = sectionContent3.getText();
		System.out.println(dataUnderSection3Content + sectionText3);

	}
	@AfterMethod
	private void closeApp() {
		driver.close();
	}
}