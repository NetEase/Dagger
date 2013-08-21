package com.netease.dagger.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * click的一系列测试
 * @author WeiYating
 */
public class ClickOperations {

	BrowserEmulator Be;
	String input = "hello";

	@BeforeClass
	public void doBeforeClass() {
		Be = new BrowserEmulator();
		CommonFunction.openCaptain(Be);
	}

	@Test
	public void clickOperations() {
		CommonFunction.clickOperations(Be);
	}

	@Test(dependsOnMethods = "clickOperations")
	public void clickButton() {
		CommonFunction.clickButton(Be);
	}

	@Test(dependsOnMethods = "clickButton")
	public void clickLink() {
		CommonFunction.clickLink(Be);
	}

	@Test(dependsOnMethods = "clickLink")
	public void submitForm() {
		CommonFunction.submitForm(Be, input);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		Be.quit();
	}
}
