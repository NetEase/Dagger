package com.netease.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * 打开网页
 * @author WeiYating
 */
public class OpenURL {

	BrowserEmulator be;

	@BeforeClass
	public void doBeforeClass() {
		be = new BrowserEmulator();
//		CommonFunction.openCaptain(Be);
	}

	@Test
	public void test() {
		CommonFunction.openURL(be);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		be.quit();
	}
}
