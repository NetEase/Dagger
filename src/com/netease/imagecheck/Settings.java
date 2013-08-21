package com.netease.imagecheck;

import java.io.FileInputStream;
import java.util.Properties;

/**
 * Parameter settings
 * @author LingFei
 */
public class Settings {
	
	public static Properties prop = getProperties();

	public static int screenShotType = Integer.parseInt(prop.getProperty("ScreenShotType", "1"));

	public static String sampleImagePath = prop.getProperty("SampleImagePath", "res/samples/");

	public static String contrastImagePath = prop.getProperty("ContrastImagePath", "images/");

	public static double maxColorThreshold = Double.parseDouble(prop.getProperty("MaxColorThreshold", "0"));

	public static int browserCoreType = Integer.parseInt(prop.getProperty("BrowserCoreType", "2"));
	
	public static String getProperty(String property) {
		return prop.getProperty(property);
	}

	public static Properties getProperties() {
		Properties prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("imagecheck.properties");
			prop.load(file);
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}

}
