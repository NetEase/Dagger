package com.netease.test;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;

/**
 * Type的一系列测试
 * @author WeiYating
 */
public class TypeOperations {

	BrowserEmulator be;
	String input = "hello wrold !";

	@BeforeClass
	public void doBeforeTest() {
		be = new BrowserEmulator();
		CommonFunction.openCaptain(be);
	}

	@Test
	public void typeOperations() {
		CommonFunction.typeOperations(be);
	}

	@Test(dependsOnMethods = "typeOperations")
	public void typeIninput() {
		CommonFunction.typeInInput(be, input);
	}
	
	@Test(dependsOnMethods = "typeIninput")
	public void typeInIframe() {
		CommonFunction.typeInIframe(be, input);
	}
	
	@Test(dependsOnMethods = "typeInIframe")
	public void typeInTextarea() {
		CommonFunction.typeInTextarea(be, input);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterTest() {
		be.quit();
	}
}
