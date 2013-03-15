/*
 * Copyright (c) 2012-2013 NetEase, Inc. and other contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.netease.dagger;

import java.io.FileInputStream;
import java.util.Properties;

public class GlobalSettings {

	public static Properties prop = getProperties();

	public static int BrowserCoreType = Integer.parseInt(prop.getProperty("BrowserCoreType", "2"));

	public static String ChromeDriverPath = System.getProperties().getProperty("os.name").toLowerCase().contains("win") ? 
			prop.getProperty("ChromeDriverPath", "res/chromedriver_for_win.exe") : prop.getProperty("ChromeDriverPath", "res/chromedriver_for_mac");
	
	public static String IEDriverPath = prop.getProperty("IEDriverPath", "res/iedriver_32.exe");

	public static String StepInterval = prop.getProperty("StepInterval", "500");

	public static String Timeout = prop.getProperty("Timeout", "30000");

	public static String getProperty(String Property) {
		return prop.getProperty(Property);
	}
	
	public static Properties getProperties() {
		Properties prop = new Properties();
		try {
			FileInputStream file = new FileInputStream("prop.properties");
			prop.load(file);
			file.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return prop;
	}
}