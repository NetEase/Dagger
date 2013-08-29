package com.netease.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * 点击链接，另开Tab页
 * @author Weiyating
 */
public class SelectWindow {

    BrowserEmulator be;
    String title = "Ruby on Rails: Welcome aboard";
	
	@BeforeClass
	public void doBeforeClass() {
		be = new BrowserEmulator();
		CommonFunction.openCaptain(be);
	}
	
	@Test
	public void selectWindowOpera() {
		CommonFunction.selectWindowOperations(be);
	}
	
	@Test(dependsOnMethods = "selectWindowOpera")
	public void selectWindow() {
		CommonFunction.selectWindow(be, title);
	}
	
	@AfterClass
	public void doAfterClass() {
		be.quit();
	}
}
