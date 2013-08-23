package com.netease.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * 判断页面上的可见文本
 * @author WeiYating
 */
public class IsTextPresentOperations {

	BrowserEmulator be;
	
	@BeforeClass
	public void doBeforeClass() {
		be = new BrowserEmulator();
		CommonFunction.openCaptain(be);
	}

	@Test
	public void isTextPresentOperations() {
		CommonFunction.isTextPresentOperations(be);
	}

	@Test(dependsOnMethods = "isTextPresentOperations", dataProvider = "data")
	public void isTextPresent(String text, boolean expect) {
		CommonFunction.isTextPresent(be, text, expect);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		be.quit();
	}

	@DataProvider(name = "data")
	public Object[][] data() {
		return new Object[][] { 
				{ "历史记录",		true},
				{ "了解产品特色",	true },
				{ "这是不可见文本",	false}
			};
	}
}
