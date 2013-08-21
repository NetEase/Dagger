package com.netease.dagger.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * mouseOver的一系列操作
 * @author WeiYating
 */
public class MouseOverOperations {

	BrowserEmulator Be;

	@BeforeClass
	public void doBeforeClass() {
		Be = new BrowserEmulator();
		CommonFunction.openCaptain(Be);
	}

	@Test
	public void mouseOverOperation() {
		CommonFunction.mouseOverOperations(Be);
	}

	@Test(dependsOnMethods = "mouseOverOperation")
	public void mouseOverCSS() {
		CommonFunction.mouseOverCSS(Be);
	}

	@Test(dependsOnMethods = "mouseOverCSS")
	public void mouseOverJS() {
		CommonFunction.mouseOverJS(Be);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		Be.quit();
	}
}
