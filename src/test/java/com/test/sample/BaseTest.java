package com.test.sample;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;

import com.test.sample.googlesearchui.GoogleSearchNavigator;
import com.test.sample.googlesearchui.impl.GoogleSearchNavigatorImpl;
import com.test.sample.utils.BrowserFactory;
import com.test.sample.utils.CommonUtil;
import com.test.sample.utils.CustomHTMLLayout;
import com.test.sample.utils.ExcelUtil;


public class BaseTest {
	
	protected static boolean isInit = false ;
	
	/***stores the <b>alt Id</b> of currently running bvt*/
	protected static int altID;
	/***stores boolean value as true or false according to status of current bvt*/
	protected boolean isSuccess = false ;
	
	protected Logger logger = null ;
	
	protected static final boolean testEnabled = true;
	
	
	protected static String testcaseId;
	
	protected static String bvtFilePath = null;
	public static WebDriver driver; 
	protected static GoogleSearchNavigator googleSearchNavigator = null;
	
//	public BaseTest(){
//		init();
//	}
////	static {
	////		
	////		new Config();
	////		//elcNavigator = new ELCNavigatorImpl();
	//////		jiraNavigator = new JiraNavigatorFactory().getInstance();
	//////		zapiService = new ZAPIAPIServiceImpl();
////	}
	public static void init(){
		if(isInit){
			return;
		}
		new Config();
		googleSearchNavigator = new GoogleSearchNavigatorImpl();
		bvtFilePath = Config.getFilePath("testData.xls");
		ExcelUtil.setBuildNumber(bvtFilePath, Config.getValue("BUILD_NO"));
		isInit=true;
	}
	
	@BeforeSuite
	public void beforeSuite() {
		init();
		driver = BrowserFactory.getBrowser(BrowserType.FIREFOX);
		CommonUtil.launchBrowser(driver, Config.getValue("URL"));
		
	}
	/**
	 * updates the current bvt status in excel bvt sheet
	 * @param status		default value as <b>f<b>
	 */
	public void updateStatus(){
		String status = "f";
		if(isSuccess){
			status="p";
		}
		ExcelUtil.writeStatus(Config.getFilePath("testData.xls"), altID, status);
	}
	
	public  void captureScreenshotInLog() {
//		CommonUtil.captureScreenshot(Config.getScreenPath());
		String imgPath = CommonUtil.captureScreenshot(driver, Config.getScreenPath());
		logger.info(CustomHTMLLayout.IMAGE_PREFIX + "src=\"../" + imgPath + "\"/>");
	}
	
//	@BeforeSuite
//	public void beforeSuite(){
//		WebDriver driver = BrowserFactory.getBrowser("firefox");
//		extentReport = new ExtentReports("reports" + File.separator + "ExtentReportsTestNG.html", true);
//		bvtFilePath = Config.getFilePath("ZE_5.0_BVT_Fayre.xls");
//		TestStatusUpdateUtil.setBuildNumber(bvtFilePath, Config.getValue("BUILD_NO"));
		
		
//		CommonUtil.launchBrowser(driver, Config.getValue("ELC_URL"));
		
		/*FileOperations f1=new FileOperations();
		f1.clearDirectory(Config.getValue("DOWNLOAD_PATH"));*/
//	}
//
	@AfterSuite
	  public void afterSuite() {
		  BrowserFactory.closeAllDriver();
	  }
		
	 /**
	  * updates the status of current bvt in bvt excel sheet<br>
	  */
	public void baseAfterMethod() {
		if(!isSuccess){
			captureScreenshotInLog();
		}
		if(altID > 0){  
			updateStatus();
		}
	}

	

}
