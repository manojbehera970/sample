package com.test.sample.utils;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.io.File;
import java.io.FileInputStream;
import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;
import java.util.Set;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.StaleElementReferenceException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.test.sample.Constants;

public class CommonUtil {

	public static Properties loadProperties(String path) {
		Properties properties = new Properties();
		FileInputStream fis = null;
		try {
			File file = getPropertyFilePath(path);
			fis = new FileInputStream(file);
			properties.load(fis);
			fis.close();
		} catch (Exception e) {
			return null;
		} finally {
			if (fis != null) {
				try {
					fis.close();
				} catch (Exception e) {
					;
				}
			}
		}
		return properties;
	}

	public static File getPropertyFilePath(String path) throws URISyntaxException, MalformedURLException {
		URL url = new File(path).toURI().toURL();
//		path.getClass().getResource(name)getResource(path);
		File inputFile = new File(url.toURI());
		return inputFile;
	}

	public static boolean launchBrowser(WebDriver driver, String url) {
		try {
			driver.get(url);
			driver.manage().window().maximize();
			implicitWait(driver, Constants.EXPLICIT_WAIT_HIGH);
			Thread.sleep(5000);
		} catch (Exception e) {
			System.out.println(e);
			return false;
		}
		return true;
	}

	public static void moveToElement(WebDriver driver, WebElement wb) {
		Actions action = new Actions(driver);

		action.moveToElement(wb).build().perform();
	}

	public static boolean isElementPresent(WebDriver driver, String xpath) {
		boolean flag = false;
		try {
			WebElement element = driver.findElement(By.xpath(xpath));
			if (element.isDisplayed() || element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e ) {
			flag = false;
		} 
		return flag ;
	}

	public static void implicitWait(WebDriver driver, int timetowaitInSec) {
		driver.manage().timeouts().implicitlyWait(timetowaitInSec, TimeUnit.SECONDS);
	}

//	public static void ExplicitWaitForElement(WebDriver driver, WebElement element) {
//		try {
//			WebDriverWait wb = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_HIGH);
//			wb.until(ExpectedConditions.visibilityOf(element));
//
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//	}

	public static void isElementClickable(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wb = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_HIGH);
			wb.until(ExpectedConditions.elementToBeClickable(element));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void closeTheDriver(WebDriver driver) {
		driver.quit();
	}

	public static String getTitle(WebDriver driver) {
		return driver.getTitle();
	}

	public static String getCurrentURL(WebDriver driver) {
		return driver.getCurrentUrl();
	}

	public static void normalWait(int timeInMilli) {
		try {
			Thread.sleep(timeInMilli);

		} catch (InterruptedException e) {

		}

	}

	public static void selectListWithVisibleText(WebElement wb, String textToSelect) {
		Select select = new Select(wb);
		select.selectByVisibleText(textToSelect);
		normalWait(1000);
	}

	public static void selectListWithIndex(WebElement wb, int indexToSelect) {
		Select select = new Select(wb);
		select.selectByIndex(indexToSelect);
	}

	public static void selectListWithValue(WebElement wb, String valueToSelect) {
		Select select = new Select(wb);
		select.selectByValue(valueToSelect);
	}

	public static WebElement returnWebElement(WebDriver driver, String xpath) {
		return driver.findElement(By.xpath(xpath));
	}

	public static List<WebElement> returnWebElements(WebDriver driver, String xpath) {
		return driver.findElements(By.xpath(xpath));
	}

	public static int returnSizeOfElements(WebDriver driver, String xpath) {
		int count = 0;
		try {
			List<WebElement> list = driver.findElements(By.xpath(xpath));
			count = list.size();
		} catch (Exception e) {
			return count;
		}
		return count;
	}

	public static boolean visibilityOfElementLocated(WebDriver driver, String xpath) {
		try {
			WebDriverWait wb = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_HIGH);
			wb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean visibilityOfElementLocated(WebDriver driver, String xpath, int waitTimeInSeconds) {
		try {
			WebDriverWait wb = new WebDriverWait(driver, waitTimeInSeconds);
			wb.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean visibilityOfElementLocated(WebDriver driver, WebElement element) {
		try {
			WebDriverWait wb = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_HIGH);
			wb.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean visibilityOfElementLocated(WebDriver driver, WebElement element, int waitTimeInSeconds) {
		try {
			WebDriverWait wb = new WebDriverWait(driver, waitTimeInSeconds);
			wb.until(ExpectedConditions.visibilityOf(element));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean textToBePresentInElement(WebDriver driver, WebElement element, String text) {
		try {
			WebDriverWait wb = new WebDriverWait(driver, Constants.EXPLICIT_WAIT_HIGH);
			wb.until(ExpectedConditions.textToBePresentInElement(element, text));
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static String captureScreenshot(WebDriver driver, String destPath) {
		try {
			EventFiringWebDriver edriver = new EventFiringWebDriver(driver);
			File src = edriver.getScreenshotAs(OutputType.FILE);
			System.out.println("output: " + src.getAbsolutePath());
			File dest = new File(destPath);
			FileUtils.copyFile(src, new File(destPath));
			return destPath;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public static Actions actionClass(WebDriver driver) {
		Actions act = new Actions(driver);
		return act;
	}

	public static Alert switchToAlert(WebDriver driver) {
		try {
			return driver.switchTo().alert();
		} catch (Exception e) {
			return null;
		}
	}

	public static boolean isElementPresent(WebElement element) {
		boolean flag = false;
		try {
			if (element.isDisplayed() || element.isEnabled())
				flag = true;
		} catch (NoSuchElementException e) {
			flag = false;
		} catch (StaleElementReferenceException e) {
			flag = false;
		}
		return flag;
	}

	public static void pressEnter() {
		Robot r;
		try {
			r = new Robot();
			r.keyPress(KeyEvent.VK_ENTER);
			r.keyRelease(KeyEvent.VK_ENTER);
		} catch (AWTException e) {
			e.printStackTrace();
		}

	}

	public static void browserRefresh(WebDriver driver) {
		driver.navigate().refresh();
		implicitWait(driver, 120);
	}

	public static void doubleClick(WebDriver driver, WebElement element) {
		try {
			Actions action = new Actions(driver).doubleClick(element);
			action.build().perform();

			System.out.println("Double clicked the element");
		} catch (StaleElementReferenceException e) {
			System.out.println("Element is not attached to the page document " + e.getStackTrace());
		} catch (NoSuchElementException e) {
			System.out.println("Element " + element + " was not found in DOM " + e.getStackTrace());
		} catch (Exception e) {
			System.out.println("Element " + element + " was not clickable " + e.getStackTrace());
		}
	}

	public static void javaWait(int time) {
		try {
			if (time == 1) {
				Thread.sleep(1000);
			} else if (time == 2) {
				Thread.sleep(2000);
			} else if (time == 3) {
				Thread.sleep(3000);
			} else if (time == 4) {
				Thread.sleep(4000);
			} else if (time == 5) {
				Thread.sleep(5000);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static String getText(WebDriver driver, String xpath) {
		String text = null;
		try {
			text = driver.findElement(By.xpath(xpath)).getText();
		} catch (Exception e) {
			return text;
		}
		return text;
	}

	public static boolean scrollPage(WebDriver driver) {
		try {
			((JavascriptExecutor) driver).executeScript("javascript:window.scrollBy(0,400)");

		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean scrollToWebElement(WebDriver driver, WebElement wb) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();", wb);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean ClickOnElementUsingJS(WebDriver driver, WebElement wb) {
		try {
			((JavascriptExecutor) driver).executeScript("arguments[0].click();", wb);
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static String launchNewBrowserWindow(WebDriver driver, String url) {
		try {
			// String selectLinkOpeninNewTab = Keys.chord(Keys.CONTROL,"t");
			// Driver.driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL
			// +"n");
			// Actions newTab = new Actions(Driver.driver);
			// newTab.sendKeys(Keys.CONTROL + "t").perform();
			((JavascriptExecutor) driver).executeScript("window.open();");

			Set<String> windowID = driver.getWindowHandles();

			Iterator<String> it1 = windowID.iterator();
			String parentWin = it1.next();
			String childWin = it1.next();
			driver.switchTo().window(childWin);
			driver.navigate().to(url);
			implicitWait(driver, 60);
			return parentWin;
		} catch (Exception e) {
			return null;
		}

	}

	public static boolean returnToParentWindow(WebDriver driver, String parentWindowId) {

		try {
			driver.switchTo().window(parentWindowId);
			normalWait(500);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public static boolean closeCurrentBrowserTab(WebDriver driver) {
		try {
			driver.findElement(By.cssSelector("body")).sendKeys(Keys.CONTROL + "w");
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static boolean closeCurrentBrowserWindow(WebDriver driver) {
		try {
			driver.close();
		} catch (Exception e) {
			return false;
		}
		return true;
	}

	public static String returnTodaysDate() {
		DateFormat df = new SimpleDateFormat("MM/dd/yyyy");
		return df.format(new Date());
	}

	public static String returnFutureDateStartingTodaysDate(int noOfMonthsToAdd, int noOfDaysToAdd,
			int noOfYearsToAdd) {
		String todaysDate = returnTodaysDate();
		String splitDays[] = todaysDate.split("/");
		int newMM = Integer.parseInt(splitDays[0]) + noOfMonthsToAdd;
		int newDD = Integer.parseInt(splitDays[1]) + noOfDaysToAdd;
		int newYYYY = Integer.parseInt(splitDays[2]) + noOfYearsToAdd;
		String newDate;
		String newMonth;
		if (newDD < 10) {
			newDate = "0" + newDD;
		} else {
			if (newDD > 30) {
				newMM++;

				if ((newDD - 30) < 10) {
					newDate = "0" + (newDD - 30) + "";
				} else {
					newDate = (newDD - 30) + "";
				}
			} else {
				newDate = "" + newDD;
			}
		}

		if (newMM < 10) {
			newMonth = "0" + newMM;
		} else {
			if (newMM > 12) {
				newYYYY++;

				if ((newMM - 12) < 10) {
					newMonth = "0" + (newMM - 12) + "";
				} else {
					newMonth = (newMM - 12) + "";
				}
			} else {
				newMonth = "" + newMM;
			}

		}

		return newMonth + "/" + newDate + "/" + newYYYY;
	}

}
