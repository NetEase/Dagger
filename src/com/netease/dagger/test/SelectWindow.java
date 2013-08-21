package com.netease.dagger.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * 点击链接，另开Tab页
 * @author Weiyating
 */
public class SelectWindow {

    BrowserEmulator Be;
    String title = "Ruby on Rails: Welcome aboard";
	
	@BeforeClass
	public void doBeforeClass(){
		Be = new BrowserEmulator();
		CommonFunction.openCaptain(Be);
	}
	
	@Test
	public void selectWindowOpera(){
		CommonFunction.selectWindowOperations(Be);
	}
	
	@Test(dependsOnMethods = "selectWindowOpera")
	public void selectWindow(){
		CommonFunction.selectWindow(Be, title);
	}
	
	@AfterClass
	public void doAfterClass(){
		Be.quit();
	}
}
