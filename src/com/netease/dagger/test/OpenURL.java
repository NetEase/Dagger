package com.netease.dagger.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * 打开网页
 * @author WeiYating
 */
public class OpenURL {

	BrowserEmulator Be;

	@BeforeClass
	public void doBeforeClass() {
		Be = new BrowserEmulator();
//		CommonFunction.openCaptain(Be);
	}

	@Test
	public void test() {
		CommonFunction.openURL(Be);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		Be.quit();
	}
}
