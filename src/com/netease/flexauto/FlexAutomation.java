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
package com.netease.flexauto;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.netease.dagger.BrowserEmulator;
import com.netease.dagger.GlobalSettings;
import com.netease.dagger.LogTools;

/**
 * FlexAutomation enables operation and verification automation for embedded
 * flex application. The flex application needs to be re-compiled with some
 * libraries added.
 * 
 * @author ddook007
 */
public class FlexAutomation {

	BrowserEmulator be;
	JavascriptExecutor js;
	String flashObjId;
	int stepInterval = Integer.parseInt(GlobalSettings.stepInterval);
	int pollingTimes = Integer.parseInt(GlobalSettings.timeout) / stepInterval;

	/**
	 * @param wrapper
	 *            who actually perform flash auto test
	 * @param flashObjIdInHTML
	 *            id of target flash object in HTML
	 */
	public FlexAutomation(BrowserEmulator wrapper, String flashObjIdInHTML) {
		be = wrapper;
		js = (JavascriptExecutor) be.getBrowserCore();
		flashObjId = flashObjIdInHTML;
	}

	/**
	 * flex element locator using 1 or 2 prop:value pairs<br>
	 * 
	 * @param args
	 *            prop:value pairs String array
	 * @return
	 */
	private String locatorGenerator(String[] args) {

		int argnum = args.length;
		switch (argnum) {
		// if so, 'automationName' is used as prop
		case 1: {
			String value = args[0];
			return " value=\"" + value + "\" ";
		}

		// 1 pair of prop:value
		case 2: {
			String prop = args[0];
			String value = args[1];
			return " prop=\"" + prop + "\" value=\"" + value + "\" ";
		}

		// 2 pairs of prop:value, first pair from target flex element and second
		// from its ancestor
		case 4: {

			String prop = args[0];
			String value = args[1];
			String containerProp = args[2];
			String containerValue = args[3];
			return " prop=\"" + prop + "\" value=\"" + value + "\" " + "containerProp=\"" + containerProp + "\" containerValue=\"" + containerValue + "\" ";
		}

		default: {
			Assert.fail("flex element locator generating failed with provided String array:" + args);
			return null;
		}

		}
	}

	/**
	 * polling the js command communicating with the target flex application
	 * 
	 * @param automationJS
	 *            the js command
	 */
	private void pollingCommand(String automationJS) {
		if (GlobalSettings.browserCoreType != 3) {
			// when tests run in parallel,
			// "This SWF is no longer connected to the FlexMonkey console" alert
			// will be seen.
			// this annoying alert has no title, using this to handle
			String checkcmd = "return document.getElementById('" + flashObjId + "').getForSelenium('<VerifyProperty value=\"Alert\" prop=\"className\" propertyString=\"title\" />','')";
			String clickcmd = "return document.getElementById('" + flashObjId + "').verifyFromSelenium('<UIEvent command=\"Click\" value=\"OK\" />','')";
			String title = "";
			for (int t = 0;; t++) {
				try {
					Thread.sleep(stepInterval);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				if (t >= pollingTimes)
					Assert.fail("flex automation failed to perform the following operation in time: " + automationJS);
				try {
					title = (String) js.executeScript(checkcmd);
					if (title.equals(""))
						js.executeScript(clickcmd);
					boolean r = (Boolean) js.executeScript(automationJS);
					if (r)
						break;
				} catch (Exception e) {
					// e.printStackTrace();
				}

			}
		} else {
			// where there's IE there's if-else
			// Todo

		}
	}

	/**
	 * verify if value of specified flex element's prop is expected<br>
	 * 
	 * @param locatorArgs
	 * @param propertyString
	 * @param expectedValue
	 */
	public void verifyProperty(String[] locatorArgs, String propertyString, String expectedValue) {

		String locator = locatorGenerator(locatorArgs);
		String flexmonkeyCommand = "<VerifyProperty " + locator + " propertyString=\"" + propertyString + "\" expectedValue=\"" + expectedValue + "\" propertyType=\"equals\" />";
		String automationJS = "return document.getElementById('" + flashObjId + "')." + "verifyFromSelenium('" + flexmonkeyCommand + "','')";
		pollingCommand(automationJS);
		// avoiding unnecessary logging
		if (propertyString.equals("initialized") && expectedValue.equals("true"))
			return;
		LogTools.log("flex element: " + locator + " is as expected in prop:value - " + propertyString + ":\"" + expectedValue + "\"");

	}

	/**
	 * get the value of specified property of the flex element<br>
	 * 
	 * @param locatorArgs
	 * @param propertyString
	 * @return
	 */
	public String getProperty(String[] locatorArgs, String propertyString) {
		String getR = "";
		String locator = locatorGenerator(locatorArgs);
		String flexmonkeyCommand = "<VerifyProperty " + locator + " propertyString=\"" + propertyString + "\" />";
		String automationJS = "return document.getElementById('" + flashObjId + "')." + "getForSelenium('" + flexmonkeyCommand + "','')";
		// verifyProperty(locatorArgs, "initialized", "true");

		try {
			getR = (String) js.executeScript(automationJS);
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail();
		}

		return getR;
	}

	/**
	 * click operation
	 * 
	 * @param locatorArgs
	 *            the prop-value pair String array used to locate the flex
	 *            element
	 */
	public void click(String[] locatorArgs) {
		String locator = locatorGenerator(locatorArgs);
		String flexmonkeyCommand = "<UIEvent command=\"Click\" " + locator + " />";
		String automationJS = "return document.getElementById('" + flashObjId + "')." + "verifyFromSelenium('" + flexmonkeyCommand + "','')";

		verifyProperty(locatorArgs, "initialized", "true");
		LogTools.log("clicking flex element: " + locator);
		pollingCommand(automationJS);
		LogTools.log("click flex element: " + locator + " finished");

	}

	/**
	 * input in a text area
	 * 
	 * @param locatorArgs
	 *            prop-value pair String array used to locate the flex element
	 * @param inputValue
	 *            whatever you want
	 */
	public void input(String[] locatorArgs, String inputValue) {
		String locator = locatorGenerator(locatorArgs);
		String flexmonkeyCommand = "<UIEvent command=\"Input\" " + locator + "><arg value=\"" + inputValue + "\"/></UIEvent>";
		String automationJS = "return document.getElementById('" + flashObjId + "')." + "verifyFromSelenium('" + flexmonkeyCommand + "','')";
		verifyProperty(locatorArgs, "initialized", "true");
		LogTools.log("inputing in flex element: " + locator + " with \"" + inputValue + "\"");
		pollingCommand(automationJS);
		LogTools.log("input in flex element: " + locator + " finished");

	}

	/**
	 * select operation for TabBar etc.
	 * 
	 * @param tabarLocatorArgs
	 *            prop-value pair String array used to locate the tab bar
	 * @param tabLocatorArgs
	 *            prop-value pair String array used to locate the very tab
	 */
	public void select(String[] tabarLocatorArgs, String[] tabLocatorArgs) {
		String tabarLocator = locatorGenerator(tabarLocatorArgs);
		String tabLocator = locatorGenerator(tabLocatorArgs);
		String flexmonkeyCommand = "<UIEvent command=\"Select\" " + tabarLocator + " ><arg " + tabLocator + " /></UIEvent>";
		String automationJS = "return document.getElementById('" + flashObjId + "')." + "verifyFromSelenium('" + flexmonkeyCommand + "','')";
		verifyProperty(tabarLocatorArgs, "initialized", "true");
		verifyProperty(tabLocatorArgs, "initialized", "true");
		LogTools.log("selecting in: " + tabarLocator + "for tab: " + tabLocator + "");
		pollingCommand(automationJS);
		LogTools.log("select finished");

	}

}
