package com.about_demoqa_selenium;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.List;
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

public class ElementsWebTable {
	private WebDriver driver;
	private WebDriverWait wait;
	private Properties properties;
	@BeforeTest
	public void setUp() {
		properties = new Properties();
		String configFilePath = "E:\\priyanka\\e\\02.07.2023\\about_demoqa_selenium\\src\\test\\java\\elementsWebTable.properties";
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
	public void webTableData() {

		String demoqaElementsUrl = properties.getProperty("demoqaElementsUrl");
		String webTableOption = properties.getProperty("webTableOption");
		String webTable = properties.getProperty("webTable");
		String rowTable = properties.getProperty("rowTable");
		String columnTable= properties.getProperty("columnTable");
		String data = properties.getProperty("data");
		String add = properties.getProperty("add");
		String edit = properties.getProperty("edit");
		String delete = properties.getProperty("delete");
		String search = properties.getProperty("search");
		String searchData = properties.getProperty("searchData");
		String firstName = properties.getProperty("firstName");
		String name = properties.getProperty("name");
		String lastName = properties.getProperty("lastName");
		String lastNameIs= properties.getProperty("lastNameIs");
		String userEmail = properties.getProperty("userEmail");
		String email= properties.getProperty("email");
		String age = properties.getProperty("age");
		String ageIs = properties.getProperty("ageIs");
		String salary = properties.getProperty("salary");
		String salaryIs = properties.getProperty("salaryIs");
		String department = properties.getProperty("department");
		String departmentName = properties.getProperty("departmentName");
		String submit = properties.getProperty("submit");


		driver.get(properties.getProperty("demoqaElementsUrl"));
		driver.findElement(By.xpath(webTableOption)).click();
		WebElement tableContainer = driver.findElement(By.cssSelector(webTable));
		List<WebElement> rows = tableContainer.findElements(By.cssSelector(rowTable));
		for (WebElement row : rows) {
			List<WebElement> columns = row.findElements(By.cssSelector(columnTable));
			for (WebElement column : columns) {
				String cellData = column.getText();
				System.out.print(cellData + data); 
			}
			System.out.println(); 
		}
		WebElement addNewRecordButton = driver.findElement(By.id(add));
		WebElement editButton = driver.findElement(By.id(edit));
		WebElement deleteButton = driver.findElement(By.id(delete));
		addNewRecordButton.click();
		WebElement searchBox = driver.findElement(By.id(search));
		searchBox.sendKeys(searchData); 
		driver.findElement(By.id(firstName)).sendKeys(name);
		driver.findElement(By.id(lastName)).sendKeys(lastNameIs);
		driver.findElement(By.id(userEmail)).sendKeys(email);
		driver.findElement(By.id(age)).sendKeys(ageIs);
		driver.findElement(By.id(salary)).sendKeys(salaryIs);
		driver.findElement(By.id(department)).sendKeys(departmentName);
		driver.findElement(By.id(submit)).click();

	}
	@AfterMethod
	private void closeApp() {
		driver.close();
	}
}
