package com.netease.dagger.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * WebDriver执行JS
 * @author Weiyating
 */
public class ExecuteJS {

	BrowserEmulator Be;

	@BeforeClass
	public void doBeforeClass() {
		Be = new BrowserEmulator();
		CommonFunction.openCaptain(Be);
	}

	@Test
	public void test() throws InterruptedException {
		CommonFunction.executeJS(Be);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		Be.quit();
	}
}
