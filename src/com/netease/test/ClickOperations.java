package com.netease.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * click的一系列测试
 * @author WeiYating
 */
public class ClickOperations {

	BrowserEmulator be;
	String input = "hello";

	@BeforeClass
	public void doBeforeClass() {
		be = new BrowserEmulator();
		CommonFunction.openCaptain(be);
	}

	@Test
	public void clickOperations() {
		CommonFunction.clickOperations(be);
	}

	@Test(dependsOnMethods = "clickOperations")
	public void clickButton() {
		CommonFunction.clickButton(be);
	}

	@Test(dependsOnMethods = "clickButton")
	public void clickLink() {
		CommonFunction.clickLink(be);
	}

	@Test(dependsOnMethods = "clickLink")
	public void submitForm() {
		CommonFunction.submitForm(be, input);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		be.quit();
	}
}
