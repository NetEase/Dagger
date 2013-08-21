package com.netease.dagger.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * Type的一系列测试
 * @author WeiYating
 */
public class TypeOperations {

	BrowserEmulator Be;
	String input = "hello wrold !";

	@BeforeClass
	public void doBeforeTest() {
		Be = new BrowserEmulator();
		CommonFunction.openCaptain(Be);
	}

	@Test
	public void typeOperations() {
		CommonFunction.typeOperations(Be);
	}

	@Test(dependsOnMethods = "typeOperations")
	public void typeIninput() {
		CommonFunction.typeInInput(Be, input);
	}
	
	@Test(dependsOnMethods = "typeIninput")
	public void typeInIframe(){
		CommonFunction.typeInIframe(Be, input);
	}
	
	@Test(dependsOnMethods = "typeInIframe")
	public void typeInTextarea(){
		CommonFunction.typeInTextarea(Be, input);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterTest() {
		Be.quit();
	}
}
