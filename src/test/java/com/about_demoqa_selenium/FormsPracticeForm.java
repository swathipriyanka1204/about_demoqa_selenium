package com.about_demoqa_selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class FormsPracticeForm {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties properties;
	@BeforeTest
	public void setUp() {
		properties = new Properties();
		String configFilePath = "E:\\priyanka\\e\\02.07.2023\\about_demoqa_selenium\\src\\test\\java\\formsPracticeForm.properties";
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
	public void formFill      () {

		String demoqaFormslUrl = properties.getProperty("demoqaFormslUrl");
		String practiceFormButton = properties.getProperty("practiceFormButton");
		String firstName = properties.getProperty("firstName");
		String firstNameIs = properties.getProperty("firstNameIs");
		String lastName= properties.getProperty("lastName");
		String lastNameIs = properties.getProperty("lastNameIs");
		String userEmail = properties.getProperty("userEmail");
		String userEmailIs = properties.getProperty("userEmailIs");
		String gender = properties.getProperty("gender");
		String userNumber = properties.getProperty("userNumber");
		String number = properties.getProperty("number");
		String dateOfBirthInput = properties.getProperty("dateOfBirthInput");
		String month = properties.getProperty("month");
		String monthIs= properties.getProperty("monthIs");
		String year = properties.getProperty("year");
		String yearIs= properties.getProperty("yearIs");
		String date = properties.getProperty("date");
		String subjectsInputIs = properties.getProperty("subjectsInputIs");
		String subjectIs = properties.getProperty("subjectIs");
		String sports = properties.getProperty("sports");
		String uploadPicture = properties.getProperty("uploadPicture");
		String picturePath = properties.getProperty("picturePath");
		String address = properties.getProperty("address");
		String addressIs = properties.getProperty("addressIs");
		String submit = properties.getProperty("submit");


		driver.get(properties.getProperty("demoqaFormslUrl"));
		driver.findElement(By.xpath(practiceFormButton)).click();
		driver.findElement(By.id(firstName)).sendKeys(firstNameIs);
		driver.findElement(By.id(lastName)).sendKeys(lastNameIs);
		driver.findElement(By.id(userEmail)).sendKeys(userEmailIs);
		driver.findElement(By.xpath(gender)).click();
		driver.findElement(By.id(userNumber)).sendKeys(number);
		driver.findElement(By.id(dateOfBirthInput)).click();
		WebElement monthDropdown = driver.findElement(By.className(month));
		Select select = new Select(monthDropdown);
		select.selectByVisibleText(monthIs);
		WebElement yearDropdown = driver.findElement(By.className(year));
		Select select1 = new Select(yearDropdown);
		select1.selectByVisibleText(yearIs);
		driver.findElement(By.xpath(date)).click();
		WebElement subjectsInput = driver.findElement(By.id(subjectsInputIs));
		subjectsInput.sendKeys(subjectIs);
		subjectsInput.sendKeys(Keys.ENTER);
		WebElement sportsCheckbox = driver.findElement(By.xpath(sports));
		sportsCheckbox.click();
		WebElement file_input = driver.findElement(By.id(uploadPicture));
		String file_path = picturePath;
		file_input.sendKeys(file_path);
		driver.findElement(By.id(address)).sendKeys(addressIs); 
		driver.findElement(By.id(submit)).submit();

	}
	@AfterMethod
	private void closeApp() {
		driver.close();
	}	
}