package com.test.sample.tests;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.firefox.internal.ProfilesIni;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.test.sample.Constants;

public class test3 {

	public static void main(String[] args) {
		// Create a profile
		System.setProperty("webdriver.gecko.driver", Constants.firefoxDriver);
		FirefoxProfile profile=new FirefoxProfile();
		profile.setPreference("browser.download.dir", "/Users/300008042/eclipse-workspace/sample/src/test/resources/");
		profile.setPreference("browser.download.folderList", 0);
		 
		// Set preferences for file type 
		profile.setPreference("browser.helperApps.neverAsk.openFile", "application/zip");
		  
		// Open browser with profile                   
		WebDriver driver=new FirefoxDriver(profile);
		  
		
		  
		// Maximize window
		driver.manage().window().maximize();
		  
//		// Open APP to download application
//		driver.get("http://www.seleniumhq.org/download/");
//		// Set implicit wait
//				driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
//		  
//		// Click on download 
//		driver.findElement(By.xpath(".//td[@id='program-header']/div[4]/a[1]")).click();
//		
		
//		DesiredCapabilities capabilities = DesiredCapabilities.firefox();
//
//		 ProfilesIni allProfiles = new ProfilesIni();
//		 FirefoxProfile profile = allProfiles.getProfile("selenium");
//		 
//		 capabilities.setCapability(FirefoxDriver.PROFILE, profile);
//		 
//		 System.setProperty("webdriver.gecko.driver", Constants.firefoxDriver);
//		 
//		 WebDriver driver = new FirefoxDriver(capabilities);
//		 
//		 driver.get("https://www.google.com/");
//			// Maximize window
//			driver.manage().window().maximize();
			  
			// Open APP to download application
			driver.get("http://www.seleniumhq.org/download/");
			// Set implicit wait
					driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
			  
			// Click on download 
			driver.findElement(By.xpath(".//a[text()='32 bit Windows IE']")).click();
			
		 
	}

}
