package com.netease.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * mouseOver的一系列操作
 * @author WeiYating
 */
public class MouseOverOperations {

	BrowserEmulator be;

	@BeforeClass
	public void doBeforeClass() {
		be = new BrowserEmulator();
		CommonFunction.openCaptain(be);
	}

	@Test
	public void mouseOverOperation() {
		CommonFunction.mouseOverOperations(be);
	}

	@Test(dependsOnMethods = "mouseOverOperation")
	public void mouseOverCSS() {
		CommonFunction.mouseOverCSS(be);
	}

	@Test(dependsOnMethods = "mouseOverCSS")
	public void mouseOverJS() {
		CommonFunction.mouseOverJS(be);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		be.quit();
	}
}
