package com.test.sample.googlesearchui.pages;

import org.apache.log4j.Logger;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import com.test.sample.utils.CommonUtil;

public class GoogleHomePage {

	Logger logger = Logger.getLogger(this.getClass());
	WebDriver driver = null;
		
//		public GoogleHomePage(){
//			logger = Logger.getLogger(this.getClass());
//		}

		public static GoogleHomePage getInstance(WebDriver driver){
			return PageFactory.initElements(driver, GoogleHomePage.class);
		}
	/**
	 * WEB ELEMENTS
	 */
	@FindBy(id = "hplogo")
	private WebElement googlelogo;
	
	@FindBy(id = "lst-ib")
	private WebElement googleSearchInputbox;
	
	@FindBy(name = "btnK")
	private WebElement googleSearchBtn;
	
	/**
	 * METHODS
	 */
	/**
	 * This Method will Validate Google home Page by taking input as driver object.
	 * @param driver
	 * @return boolean value
	 * @throws Exception
	 */
	public boolean validateHomePage(WebDriver driver) throws Exception {
		try {
			Assert.assertEquals(driver.getTitle(), "Google", "Google home page title not verified.");
			logger.info("Google home page title validated successfully.");
			
			Assert.assertTrue(CommonUtil.isElementPresent(googlelogo), "Google Logo Not present in the Google home page.");
			logger.info("Google home page Logo validated successfully.");
			
			Assert.assertTrue(CommonUtil.isElementPresent(googleSearchInputbox), "Google Search Box Not present in the Google home page.");
			logger.info("Google home page Search box validated successfully.");
			
			Assert.assertTrue(CommonUtil.isElementPresent(googleSearchBtn), "Google Search Btn Not present in the Google home page.");
			logger.info("Google home page Search Btn validated successfully.");
			
			return true;
		}catch(NoSuchElementException ne) {
			logger.error("Validate Google home page failed.");
			throw new Exception("Google Home Page Validation Failed.");
		}
	}
}
