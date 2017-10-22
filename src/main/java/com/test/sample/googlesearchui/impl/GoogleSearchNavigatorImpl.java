package com.test.sample.googlesearchui.impl;

import org.openqa.selenium.WebDriver;

import com.test.sample.googlesearchui.GoogleSearchNavigator;
import com.test.sample.googlesearchui.pages.GoogleHomePage;

public class GoogleSearchNavigatorImpl implements GoogleSearchNavigator {

	@Override
	public boolean validateHomePage(WebDriver driver) throws Exception {
		return GoogleHomePage.getInstance(driver).validateHomePage(driver);
	}

}
