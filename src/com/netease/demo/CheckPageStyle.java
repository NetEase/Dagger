package com.netease.demo;

import com.netease.imagecheck.ImageProcess;
import com.netease.dagger.BrowserEmulator;

/**
 * This demo shows how to use image contrast feature to check page style
 * Please see details at https://github.com/leonar/Dagger/wiki/Demo-for-Image-Contrast
 * @author LingFei
 */
public class CheckPageStyle {
	
	public static void main(String[] args) throws Exception {
		// Change githubUrl to https://github.com/login for contrast
		// Remember to change ScreenShotType from 1 to 2 in imagecheck.properties
		String githubUrl = "https://github.com/";
		String topbarXpath = "//div[@class='container clearfix']";
		String checkPoint = "topbar";
		String folderName = "github";
		BrowserEmulator be = new BrowserEmulator();
		be.open(githubUrl);
		ImageProcess.process(be.getBrowserCore(), folderName, checkPoint, topbarXpath);
		be.quit();
	}
	
}
