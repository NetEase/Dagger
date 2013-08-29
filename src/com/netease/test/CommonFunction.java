package com.netease.test;

import org.openqa.selenium.JavascriptExecutor;
import org.testng.Assert;

import com.netease.dagger.BrowserEmulator;

/**
 * SanityTest 公共操作
 * 
 * @author WeiYating
 */
public class CommonFunction {

	public static String ip = "192.168.145.247";
	public static String port = "3001";

	/**
	 * 打开页面Captain
	 * 
	 * @param be
	 * @param ip
	 * @param port
	 */
	public static void openCaptain(BrowserEmulator be) {
		be.open("http://" + ip + ":" + port + "/welcome");
		be.expectElementExistOrNot(true,
				"//h1[text()='Dagger测试系统 - SanityTest']", 5000);
	}

	/**
	 * 打开网页
	 * 
	 * @param be
	 */
	public static void openURL(BrowserEmulator be) {
		be.open("http://" + ip + ":" + port + "/open");
		be.expectElementExistOrNot(true,
				"//h1[contains(text(),'BrowserEmulator.open')]", 5000);
	}

	// TODO
	// /**
	// * 网页超时
	// * @param be
	// */
	// public static void overTime(BrowserEmulator be) {
	// be.click("//a[contains(text(),'BrowserEmulator.open')]");
	// be.expectElementExistOrNot(true,
	// "//div[@id='errorTitle']/h1[text()='无法连接']", 8000);
	// }

	/**
	 * 点击click链接，进入click操作页面
	 * 
	 * @param be
	 */
	public static void clickOperations(BrowserEmulator be) {
		be.click("//a[contains(text(),'BrowserEmulator.click')]");
		be.expectElementExistOrNot(true,
				"//h1[contains(text(),' BrowserEmulator.click')]", 5000);
	}

	/**
	 * 点击Button按钮
	 * 
	 * @param be
	 */
	public static void clickButton(BrowserEmulator be) {
		be.click("//input[@value='点击button，触发Ajax']");
		be.expectElementExistOrNot(true, "//h1[(text()='点击了button，触发了Ajax')]",
				5000);
	}

	/**
	 * 点击链接
	 * 
	 * @param be
	 */
	public static void clickLink(BrowserEmulator be) {
		be.click("//a[(text()='点击<a>节点，页面跳转')]");
		be.expectElementExistOrNot(true,
				"//h1[text()='Dagger测试系统 - SanityTest']", 5000);
		be.click("//a[contains(text(),'BrowserEmulator.click')]");
	}

	/**
	 * 输入内容，点击Button提交表单
	 * 
	 * @param be
	 * @param input
	 */
	public static void submitForm(BrowserEmulator be, String input) {
		be.type("//input[@value='在此输入内容']", input);
		be.click("//input[@value='点击<input>，提交表单']");
		be.expectElementExistOrNot(true, "//h1[text()='" + input + "']", 5000);
	}

	// TODO
	// /**
	// * 点击不可点击部分
	// * @param be
	// */
	// public static void clickNothing(BrowserEmulator be){
	// be.click("");
	// be.expectElementExistOrNot(true,
	// "//h1[text()='本页面用于测试 - BrowserEmulator.click(String locator)']", 5000);
	// }

	/**
	 * 点击type链接，进入type操作页面
	 * 
	 * @param be
	 */
	public static void typeOperations(BrowserEmulator be) {
		be.click("//a[contains(text(),'BrowserEmulator.type')]");
		be.expectElementExistOrNot(true,
				"//h1[contains(text(),'BrowserEmulator.type')]", 5000);
	}

	/**
	 * 在input中输入文本
	 * 
	 * @param be
	 * @param input
	 */
	public static void typeInInput(BrowserEmulator be, String input) {
		be.type("//input[@value='在<input>输入文本']", input);
		be.click("//input[@value='提交<input>节点文本']");
		be.expectElementExistOrNot(true, "//h1[text()='" + input + "']", 5000);
		be.open("http://" + ip + ":" + port + "/type");
	}

	/**
	 * 在iframe中输入文本
	 * 
	 * @param be
	 * @param input
	 */
	public static void typeInIframe(BrowserEmulator be, String input) {
		be.type("//body/iframe", input);
		be.click("//input[@value='提交<iframe>节点文本']");
		be.expectElementExistOrNot(true, "//h1[contains(text(),'" + input
				+ "')]", 5000);
		be.open("http://" + ip + ":" + port + "/type");
	}

	/**
	 * 在textarea中输入文本
	 * 
	 * @param be
	 * @param input
	 */
	public static void typeInTextarea(BrowserEmulator be, String input) {
		be.type("//textarea[text()='在<textarea>输入文本']", input);
		be.click("//input[@value='提交<textarea>节点文本']");
		be.expectElementExistOrNot(true, "//h1[text()='" + input + "']", 5000);
		be.open("http://" + ip + ":" + port + "/type");
	}

	/**
	 * 点击mouseOver链接，进入mouseOver操作页面
	 * 
	 * @param be
	 */
	public static void mouseOverOperations(BrowserEmulator be) {
		be.click("//a[contains(text(),'BrowserEmulator.mouseOver')]");
		be.expectElementExistOrNot(true, "//a[text()='CSS下拉菜单']", 5000);
		be.expectElementExistOrNot(true, "//a[text()='JS下拉菜单']", 5000);
	}

	/**
	 * mouseOverCSS下拉框
	 * 
	 * @param be
	 */
	public static void mouseOverCSS(BrowserEmulator be) {
		be.mouseOver("//a[text()='CSS下拉菜单']");
		be.expectElementExistOrNot(true, "//a[text()='甲']", 5000);
		be.expectElementExistOrNot(true, "//a[text()='乙']", 5000);
		be.expectElementExistOrNot(true, "//a[text()='丙']", 5000);
		be.expectElementExistOrNot(true, "//a[text()='丁']", 5000);
	}

	/**
	 * mouseOverJS下拉框
	 * 
	 * @param be
	 */
	public static void mouseOverJS(BrowserEmulator be) {
		be.mouseOver("//a[text()='JS下拉菜单']");
		be.expectElementExistOrNot(true, "//a[text()='AAA']", 5000);
		be.expectElementExistOrNot(true, "//a[text()='BBB']", 5000);
		be.expectElementExistOrNot(true, "//a[text()='CCC']", 5000);
		be.expectElementExistOrNot(true, "//a[text()='DDD']", 5000);
	}

	/**
	 * 点击getText链接，进入getText操作页面
	 * 
	 * @param be
	 */
	public static void getTextOperations(BrowserEmulator be) {
		be.click("//a[contains(text(),'BrowserEmulator.getText')]");
		be.expectElementExistOrNot(true,
				"//h1[contains(text(), BrowserEmulator.getText)]", 5000);
	}

	/**
	 * 点击isTextPresentOperations，进入操作页面
	 * 
	 * @param be
	 */
	public static void isTextPresentOperations(BrowserEmulator be) {
		be.click("//a[contains(text(),'BrowserEmulator.isTextPresent')]");
		be.expectElementExistOrNot(true,
				"//h1[contains(text(),'BrowserEmulator.isTextPresent')]", 5000);
	}

	/**
	 * 判断页面上文本可见不可见
	 * 
	 * @param be
	 * @param text
	 */
	public static void isTextPresent(BrowserEmulator be, String text,
			boolean expect) {
		if (expect) {
			if (!be.isTextPresent(text, 3000)) {
				Assert.fail();
			}
		} else {
			if (be.isTextPresent(text, 3000)) {
				Assert.fail();
			}
		}
	}

	/**
	 * 点击isElementPresent，进入操作页面
	 * 
	 * @param be
	 */
	public static void isElementPresentOperations(BrowserEmulator be) {
		be.click("//a[contains(text(),'BrowserEmulator.isElementPresent')]");
		be.expectElementExistOrNot(true,
				"//h1[contains(text(),'BrowserEmulator.isElementPresent')]",
				5000);
	}

	/**
	 * 判断元素在页面可见不可见
	 * 
	 * @param be
	 * @param xpath
	 */
	public static void isElementPresent(BrowserEmulator be, String xpath,
			boolean expect) {
		if (expect) {
			if (!be.isElementPresent(xpath, 3000)) {
				Assert.fail();
			}
		} else {
			if (be.isElementPresent(xpath, 3000)) {
				Assert.fail();
			}
		}
	}

	/**
	 * 点击selectPicInWindowsTop链接，进入操作页面
	 * 
	 * @param be
	 */
	public static void selectPicInWindowsTopOpera(BrowserEmulator be) {
		be.click("//a[contains(text(),'BrowserEmulator.selectPicInWindowsPop')]");
		be.expectElementExistOrNot(true,
				"//h1[contains(text(),'选择文件后执行JS代码')]", 5000);
	}

	/**
	 * 点击selectWindow,进入操作页面
	 * 
	 * @param be
	 */
	public static void selectWindowOperations(BrowserEmulator be) {
		be.click("//a[contains(text(),'BrowserEmulator.selectWindow')]");
		be.expectElementExistOrNot(true,
				"//a[text()='Click me to open a new window']", 5000);
	}

	/**
	 * 点击<a>，另开Tab页
	 * 
	 * @param be
	 */
	public static void selectWindow(BrowserEmulator be, String title) {
		be.click("//a[text()='Click me to open a new window']");
		be.selectWindow(title);
		be.expectElementExistOrNot(true, "//h1[text()='Welcome aboard']", 5000);
	}

	// TODO
	// /**
	// * 点击<a>，另开Tab页超时
	// * @param Be
	// */
	// public static void selectWindowOverTime(BrowserEmulator Be){
	//
	// }

	/**
	 * WebDriver执行JS
	 * 
	 * @param be
	 * @throws InterruptedException
	 */
	public static void executeJS(BrowserEmulator be)
			throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) be.getBrowserCore();
		js.executeScript("return document.getElementsByTagName('li')[0].getElementsByTagName('a')[0].click()");
		Thread.sleep(5000);
		String text = (String) js
				.executeScript("return document.getElementsByTagName('h1')[0].firstChild.textContent");
		be.expectTextExistOrNot(true, text, 5000);
	}
}
