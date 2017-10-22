package com.test.sample;

import java.io.File;
import java.util.Properties;

import org.apache.log4j.xml.DOMConfigurator;

import com.test.sample.utils.CommonUtil;


public class Config {


	private static boolean isInit = false ;
	private static String path = null ;
	private static Properties prop = null ;
	private static String logPath = null ;
	
	public Config() {
		if (isInit) {
			return ;
		}
		init();
	}
	
	private void init() {
		
		Config.path = "src/test/resources/config.properties" ;
		prop = CommonUtil.loadProperties(Config.path);
		
		if (prop == null) {
			throw new RuntimeException("Unable to load properties file: " + Config.path);
		}
		initLog4j();
		logPath = "logs" + File.separator;
		
		isInit = true ;
		
	}
	
	/* Log4j appender is located at a different dir, so using programmatically initialization */
	private static void initLog4j() {
		try {
			File file = CommonUtil.getPropertyFilePath("src/test/resources/log4j.xml");
			DOMConfigurator.configure(file.getAbsolutePath());
		} catch (Exception e) {
			RuntimeException fe = new RuntimeException("Couldn't initialize log4j.", e);
			throw fe ;
		}		
	}
	
	public static String getValue(String key) {
		return prop.getProperty(key);
	}
	
	public static String getScreenPath() {
		return logPath + "screenshot" + File.separator + System.currentTimeMillis() + ".png";
	}
	
	
	public static String getFilePath(String filePath) {
		return prop.get(Constants.CONFIG_PATH) +  File.separator + filePath;
	}
}
