package com.test.sample.tests;

import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.test.sample.Constants;

public class test2 {

	public static void main(String[] args) throws InterruptedException {
		// Create a profile
		System.setProperty("webdriver.gecko.driver", Constants.firefoxDriver);
		FirefoxProfile profile=new FirefoxProfile();
		profile.setPreference("browser.download.dir", "/Users/300008042/eclipse-workspace/sample/src/test/resources/");
		profile.setPreference("browser.download.folderList", 2);
		 
		// Set preferences for file type 
		profile.setPreference("browser.helperApps.neverAsk.openFile", "application/zip");
		  
		// Open browser with profile                   
		WebDriver driver=new FirefoxDriver(profile);
		  
		
		  
		// Maximize window
		driver.manage().window().maximize();
		  
		// Open APP to download application
		driver.get("http://money.rediff.com/gainers/bsc/daily/groupa");
		// Set implicit wait
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		
		
		List<WebElement> list = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr"));
				
		for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			System.out.println(webElement.getText());
//			Thread.sleep(4000);
//			System.out.println(webElement.findElement(By.xpath("//td[1]")).getText());
		}
		System.out.println("==========");
		List<WebElement> list1 = driver.findElements(By.xpath("//table[@class='dataTable']/tbody/tr/td[1]"));
		
		for (Iterator iterator = list1.iterator(); iterator.hasNext();) {
			WebElement webElement = (WebElement) iterator.next();
			System.out.println(webElement.getText());
		}
		 
	}

}
