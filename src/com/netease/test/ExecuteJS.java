package com.netease.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * WebDriver执行JS
 * @author Weiyating
 */
public class ExecuteJS {

	BrowserEmulator be;

	@BeforeClass
	public void doBeforeClass() {
		be = new BrowserEmulator();
		CommonFunction.openCaptain(be);
	}

	@Test
	public void test() throws InterruptedException {
		CommonFunction.executeJS(be);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		be.quit();
	}
}
