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
package com.netease.dagger;

import java.awt.AWTException;
import java.awt.Robot;
import java.util.Arrays;
import java.io.File;
import java.io.IOException;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriverBackedSelenium;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.testng.Assert;
import com.thoughtworks.selenium.Wait;

/**
 * BrowserEmulator is based on Selenium2 and adds some enhancements
 */
public class BrowserEmulator {

	RemoteWebDriver browserCore;
	WebDriverBackedSelenium browser;
	ChromeDriverService chromeServer;
	JavascriptExecutor javaScriptExecutor;
	
	int stepInterval = Integer.parseInt(GlobalSettings.stepInterval);
	int timeout = Integer.parseInt(GlobalSettings.timeout);
	
	private static Logger logger = Logger.getLogger(BrowserEmulator.class.getName());

	public BrowserEmulator() {
		setupBrowserCoreType(GlobalSettings.browserCoreType);
		browser = new WebDriverBackedSelenium(browserCore, "http://www.163.com/");
		javaScriptExecutor = (JavascriptExecutor) browserCore;
		logger.info("Started BrowserEmulator");
	}

	private void setupBrowserCoreType(int type) {
		if (type == 1) {
			browserCore = new FirefoxDriver();
			logger.info("Using Firefox");
			return;
		}
		if (type == 2) {
			chromeServer = new ChromeDriverService.Builder().usingDriverExecutable(new File(GlobalSettings.chromeDriverPath)).usingAnyFreePort().build();
			try {
				chromeServer.start();
			} catch (IOException e) {
				e.printStackTrace();
			}
			DesiredCapabilities capabilities = DesiredCapabilities.chrome();
			capabilities.setCapability("chrome.switches", Arrays.asList("--start-maximized"));
			browserCore = new RemoteWebDriver(chromeServer.getUrl(), capabilities);
			logger.info("Using Chrome");
			return;
		}
		if (type == 3) {
			System.setProperty("webdriver.ie.driver", GlobalSettings.ieDriverPath);
			DesiredCapabilities capabilities = DesiredCapabilities.internetExplorer();
			capabilities.setCapability(InternetExplorerDriver.INTRODUCE_FLAKINESS_BY_IGNORING_SECURITY_DOMAINS, true);
			browserCore = new InternetExplorerDriver(capabilities);
			logger.info("Using IE");
			return;
		}
		if (type == 4) {
			browserCore = new SafariDriver();
			logger.info("Using Safari");
			return;
		}

		Assert.fail("Incorrect browser type");
	}
	
	/**
	 * Get the WebDriver instance embedded in BrowserEmulator
	 * @return a WebDriver instance
	 */
	public RemoteWebDriver getBrowserCore() {
		return browserCore;
	}

	/**
	 * Get the WebDriverBackedSelenium instance embedded in BrowserEmulator
	 * @return a WebDriverBackedSelenium instance
	 */
	public WebDriverBackedSelenium getBrowser() {
		return browser;
	}
	
	/**
	 * Get the JavascriptExecutor instance embedded in BrowserEmulator
	 * @return a JavascriptExecutor instance
	 */
	public JavascriptExecutor getJavaScriptExecutor() {
		return javaScriptExecutor;
	}

	/**
	 * Open the URL
	 * @param url
	 *            the target URL
	 */
	public void open(String url) {
		pause(stepInterval);
		try {
			browser.open(url);
		} catch (Exception e) {
			e.printStackTrace();
			handleFailure("Failed to open url " + url);
		}
		logger.info("Opened url " + url);
	}

	/**
	 * Quit the browser
	 */
	public void quit() {
		pause(stepInterval);
		browserCore.quit();
		if (GlobalSettings.browserCoreType == 2) {
			chromeServer.stop();
		}
		logger.info("Quitted BrowserEmulator");
	}

	/**
	 * Click the page element
	 * @param xpath
	 *            the element's xpath
	 */
	public void click(String xpath) {
		pause(stepInterval);
		expectElementExistOrNot(true, xpath, timeout);
		try {
			clickTheClickable(xpath, System.currentTimeMillis(), 2500);
		} catch (Exception e) {
			e.printStackTrace();
			handleFailure("Failed to click " + xpath);
		}
		logger.info("Clicked " + xpath);
	}

	/**
	 * Click an element until it's clickable or timeout
	 * @param xpath
	 * @param startTime
	 * @param timeout in millisecond
	 * @throws Exception
	 */
	private void clickTheClickable(String xpath, long startTime, int timeout) throws Exception {
		try {
			browserCore.findElementByXPath(xpath).click();
		} catch (Exception e) {
			if (System.currentTimeMillis() - startTime > timeout) {
				logger.info("Element " + xpath + " is unclickable");
				throw new Exception(e);
			} else {
				Thread.sleep(500);
				logger.info("Element " + xpath + " is unclickable, try again");
				clickTheClickable(xpath, startTime, timeout);
			}
		}
	}

	/**
	 * Type text at the page element<br>
	 * Before typing, try to clear existed text
	 * @param xpath
	 *            the element's xpath
	 * @param text
	 *            the input text
	 */
	public void type(String xpath, String text) {
		pause(stepInterval);
		expectElementExistOrNot(true, xpath, timeout);

		WebElement we = browserCore.findElement(By.xpath(xpath));
		try {
			we.clear();
		} catch (Exception e) {
			logger.warn("Failed to clear text at " + xpath);
		}
		try {
			we.sendKeys(text);
		} catch (Exception e) {
			e.printStackTrace();
			handleFailure("Failed to type " + text + " at " + xpath);
		}

		logger.info("Type " + text + " at " + xpath);
	}

	/**
	 * Hover on the page element
	 * @param xpath
	 *            the element's xpath
	 */
	public void mouseOver(String xpath) {
		pause(stepInterval);
		expectElementExistOrNot(true, xpath, timeout);

		if (GlobalSettings.browserCoreType == 1) {
			Assert.fail("Mouseover is not supported for Firefox now");
		}
		if (GlobalSettings.browserCoreType == 2) {
			// First make mouse out of browser
			Robot rb = null;
			try {
				rb = new Robot();
			} catch (AWTException e) {
				e.printStackTrace();
			}
			rb.mouseMove(0, 0);
			
			// Then hover
			WebElement we = browserCore.findElement(By.xpath(xpath));
			try {
				Actions builder = new Actions(browserCore);
				builder.moveToElement(we).build().perform();
			} catch (Exception e) {
				e.printStackTrace();
				handleFailure("Failed to mouseover " + xpath);
			}

			logger.info("Mouseover " + xpath);
			return;
		} 
		if (GlobalSettings.browserCoreType == 3) {
			Assert.fail("Mouseover is not supported for IE now");
		}
		
		Assert.fail("Incorrect browser type");
	}

	/**
	 * Switch window/tab
	 * @param windowTitle
	 *            the window/tab's title
	 */
	public void selectWindow(String windowTitle) {
		pause(stepInterval);
		browser.selectWindow(windowTitle);
		logger.info("Switched to window " + windowTitle);
	}

	/**
	 * Enter the iframe
	 * @param xpath
	 *            the iframe's xpath
	 */
	public void enterFrame(String xpath) {
		pause(stepInterval);
		browserCore.switchTo().frame(browserCore.findElementByXPath(xpath));
		logger.info("Entered iframe " + xpath);
	}

	/**
	 * Leave the iframe
	 */
	public void leaveFrame() {
		pause(stepInterval);
		browserCore.switchTo().defaultContent();
		logger.info("Left the iframe");
	}
	
	/**
	 * Refresh the browser
	 */
	public void refresh() {
		pause(stepInterval);
		browserCore.navigate().refresh();
		logger.info("Refreshed");
	}
	
	/**
	 * Mimic system-level keyboard event
	 * @param keyCode
	 *            such as KeyEvent.VK_TAB, KeyEvent.VK_F11
	 */
	public void pressKeyboard(int keyCode) {
		pause(stepInterval);
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		rb.keyPress(keyCode);	// press key
		rb.delay(100); 			// delay 100ms
		rb.keyRelease(keyCode);	// release key
		logger.info("Pressed key with code " + keyCode);
	}

	//TODO Mimic system-level mouse event

	/**
	 * Expect some text exist or not on the page<br>
	 * Expect text exist, but not found after timeout => Assert fail<br>
	 * Expect text not exist, but found after timeout => Assert fail
	 * @param expectExist
	 *            true or false
	 * @param text
	 *            the expected text
	 * @param timeout
	 *            timeout in millisecond
	 */
	public void expectTextExistOrNot(boolean expectExist, final String text, int timeout) {
		if (expectExist) {
			try {
				new Wait() {
					public boolean until() {
						return isTextPresent(text, -1);
					}
				}.wait("Failed to find text " + text, timeout);
			} catch (Exception e) {
				e.printStackTrace();
				handleFailure("Failed to find text " + text);
			}
			logger.info("Found desired text " + text);
		} else {
			if (isTextPresent(text, timeout)) {
				handleFailure("Found undesired text " + text);
			} else {
				logger.info("Not found undesired text " + text);
			}
		}
	}

	/**
	 * Expect an element exist or not on the page<br>
	 * Expect element exist, but not found after timeout => Assert fail<br>
	 * Expect element not exist, but found after timeout => Assert fail<br>
	 * Here <b>exist</b> means <b>visible</b>
	 * @param expectExist
	 *            true or false
	 * @param xpath
	 *            the expected element's xpath
	 * @param timeout
	 *            timeout in millisecond
	 */
	public void expectElementExistOrNot(boolean expectExist, final String xpath, int timeout) {
		if (expectExist) {
			try {
				new Wait() {
					public boolean until() {
						return isElementPresent(xpath, -1);
					}
				}.wait("Failed to find element " + xpath, timeout);
			} catch (Exception e) {
				e.printStackTrace();
				handleFailure("Failed to find element " + xpath);
			}
			logger.info("Found desired element " + xpath);
		} else {
			if (isElementPresent(xpath, timeout)) {
				handleFailure("Found undesired element " + xpath);
			} else {
				logger.info("Not found undesired element " + xpath);
			}
		}
	}

	/**
	 * Is the text present on the page
	 * @param text
	 *            the expected text
	 * @param time           
	 *            wait a moment (in millisecond) before search text on page;<br>
	 *            minus time means search text at once
	 * @return
	 */
	public boolean isTextPresent(String text, int time) {
		pause(time);
		boolean isPresent = browser.isTextPresent(text);
		if (isPresent) {
			logger.info("Found text " + text);
			return true;
		} else {
			logger.info("Not found text " + text);
			return false;
		}
	}

	/**
	 * Is the element present on the page<br>
	 * Here <b>present</b> means <b>visible</b>
	 * @param xpath
	 *            the expected element's xpath
	 * @param time           
	 *            wait a moment (in millisecond) before search element on page;<br>
	 *            minus time means search element at once
	 * @return
	 */
	public boolean isElementPresent(String xpath, int time) {
		pause(time);
		boolean isPresent = browser.isElementPresent(xpath) && browserCore.findElementByXPath(xpath).isDisplayed();
		if (isPresent) {
			logger.info("Found element " + xpath);
			return true;
		} else {
			logger.info("Not found element" + xpath);
			return false;
		}
	}
	
	/**
	 * Pause
	 * @param time in millisecond
	 */
	public void pause(int time) {
		if (time <= 0) {
			return;
		}
		try {
			Thread.sleep(time);
			logger.info("Pause " + time + " ms");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
	
	private void handleFailure(String notice) {
		String png = LogTools.screenShot(this);
		String log = notice + " >> capture screenshot at " + png;
		logger.error(log);
		Assert.fail(log);
	}
}
