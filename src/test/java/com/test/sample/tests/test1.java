package com.test.sample.tests;

import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.Reporter;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.test.sample.BaseTest;
import com.test.sample.Config;

public class test1 extends BaseTest{
	
	public test1() {
		logger = Logger.getLogger(this.getClass());
	}
	
	@Test
	public void test1() {
		altID = 1;
		logger.info("test completed :"+Config.getValue("URL"));
		Reporter.log("swsddffxfedsfssd");
		
		try {
			googleSearchNavigator.validateHomePage(driver);
		} catch (Exception e) {
			e.printStackTrace();
		}
		isSuccess = true;
	}
	@Test
	public void test2() {
		altID = 2;
		logger.info("test completed :"+Config.getValue("URL"));
		
		Assert.assertTrue(false);
		isSuccess = false;
	}
	@Test
	public void test3() throws InterruptedException {
		altID = 2;
		logger.info("test completed :"+Config.getValue("URL"));
		String baseUrl = "http://demo.guru99.com/selenium/upload/";
		driver.get(baseUrl);
		driver.manage().timeouts().implicitlyWait(10000, TimeUnit.SECONDS);
       // WebDriver driver = new FirefoxDriver();

        
        WebElement uploadElement = driver.findElement(By.id("uploadfile_0"));

        // enter the file path onto the file-selection input field
        uploadElement.sendKeys("/Users/300008042/eclipse-workspace/sample/src/test/resources/testdata.xls");
        Thread.sleep(3000);

        // check the "I accept the terms of service" check box
        driver.findElement(By.id("terms")).click();

        // click the "UploadFile" button
        driver.findElement(By.name("send")).click();
		Assert.assertTrue(false);
		isSuccess = false;
	}
	@AfterMethod 
	public void aftermethod() {
		baseAfterMethod();
	}
	@BeforeMethod
	public void beforeMethod() {
		isSuccess = false;
	}
}
