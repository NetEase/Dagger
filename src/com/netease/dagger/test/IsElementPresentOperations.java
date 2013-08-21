package com.netease.dagger.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * �ж�Ԫ���Ƿ����
 * @author WeiYating
 */
public class IsElementPresentOperations {

	BrowserEmulator Be;

	@BeforeClass
	public void doBeforeClass() {
		Be = new BrowserEmulator();
		CommonFunction.openCaptain(Be);
	}
	
	@Test
	public void isElementPresentOperations(){
		CommonFunction.isElementPresentOperations(Be);
	}

	@Test(dependsOnMethods = "isElementPresentOperations",dataProvider = "data")
	public void isElementPresent(String XPath, boolean expect) {
		CommonFunction.isElementPresent(Be, XPath, expect);
	}
	
	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		Be.quit();
	}
	
	@DataProvider
	public Object [][] data(){
		return new Object[][]{
				{"//a[text()='�ɼ�ڵ� - app']",		true},
				{"//div[text()='�ɼ�ڵ� - dust']",	true},
				{"//li[text()='�ɼ�ڵ� - lte']",		true},
				{"//li[text()='���ɼ�ڵ� - dsp']",	false},
				{"//li[text()='���ɼ�ڵ� - vsb']",	false},
		};
	}
}
