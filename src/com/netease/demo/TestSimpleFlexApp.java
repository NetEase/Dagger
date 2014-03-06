/*
 * Copyright (c) 2012-2013 NetEase, Inc. and other contributors
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 *
 */
package com.netease.demo;

import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.netease.dagger.BrowserEmulator;
import com.netease.dagger.LogTools;
import com.netease.flexauto.FlexAutomation;

/**
 * This demo shows how to use flex automation feature to test simple flex app
 * Detail wiki to be continued
 * 
 * @author ddook007
 */
public class TestSimpleFlexApp {

	String flexUrl = "http://ddook007.github.io/demo/demo.html";
	String flexId = "demo";
	BrowserEmulator be;
	FlexAutomation fa;

	@BeforeClass
	public void doBeforeClass() throws Exception {
		be = new BrowserEmulator();
	}

	@Test
	public void doTest() {
		be.open(flexUrl);
		fa = new FlexAutomation(be, flexId);
		// input 'flex automation is simple'
		fa.input(new String[] { "id", "input" }, "flex automation is simple");
		// click the button, show the input in above label
		fa.click(new String[] { "id", "inputBtn" });
		// what we input should be shown in the label
		fa.verifyProperty(new String[] { "id", "lb" }, "text", "flex automation is simple");

		// get the button's value of label property
		LogTools.log("The label of the button is - " + fa.getProperty(new String[] { "id", "inputBtn" }, "label"));
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		be.quit();
	}

}
