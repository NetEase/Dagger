package com.netease.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * 判断元素是否存在
 * @author WeiYating
 */
public class IsElementPresentOperations {

	BrowserEmulator be;

	@BeforeClass
	public void doBeforeClass() {
		be = new BrowserEmulator();
		CommonFunction.openCaptain(be);
	}
	
	@Test
	public void isElementPresentOperations() {
		CommonFunction.isElementPresentOperations(be);
	}

	@Test(dependsOnMethods = "isElementPresentOperations",dataProvider = "data")
	public void isElementPresent(String xpath, boolean expect) {
		CommonFunction.isElementPresent(be, xpath, expect);
	}
	
	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		be.quit();
	}
	
	@DataProvider
	public Object [][] data() {
		return new Object[][]{
				{"//a[text()='可见节点 - app']",		true},
				{"//div[text()='可见节点 - dust']",	true},
				{"//li[text()='可见节点 - lte']",		true},
				{"//li[text()='不可见节点 - dsp']",	false},
				{"//li[text()='不可见节点 - vsb']",	false},
		};
	}
}
