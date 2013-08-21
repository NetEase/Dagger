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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import com.netease.dagger.BrowserEmulator;

/**
 * A clone of TestNg.java to show "Parallel Mode"<br>
 * Please see https://github.com/NetEase/Dagger/wiki/Parallel-Mode
 * @author ChenKan
 */
public class TestNgClone {

	String googleUrl = "http://www.google.com";
	String searchBox = "//input[@name='q']";
	String searchBtn = "//input[@name='btnK']";
	BrowserEmulator be;

	@BeforeClass
	public void doBeforeClass() throws Exception {
		be = new BrowserEmulator();
	}

	@Test(dataProvider = "data")
	public void doTest(String keyword, String result) {
		be.open(googleUrl);
		be.type(searchBox, keyword);
		be.click(searchBtn);
		be.expectTextExistOrNot(true, result, 5000);
	}

	@AfterClass(alwaysRun = true)
	public void doAfterClass() {
		be.quit();
	}

	@DataProvider(name = "data")
	public Object[][] data() {
		return new Object[][] { 
				{ "java", "www.java.com" }, 
				{ "github", "https://github.com/" }, 
			};
	}
}