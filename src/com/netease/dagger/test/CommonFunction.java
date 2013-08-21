package com.netease.dagger.test;

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
	 * @param Be
	 * @param ip
	 * @param port
	 */
	public static void openCaptain(BrowserEmulator Be) {
		Be.open("http://" + ip + ":" + port + "/welcome");
		Be.expectElementExistOrNot(true,
				"//h1[text()='Dagger测试系统 - SanityTest']", 5000);
	}

	/**
	 * 打开网页
	 * 
	 * @param Be
	 */
	public static void openURL(BrowserEmulator Be) {
		Be.open("http://" + ip + ":" + port + "/open");
		Be.expectElementExistOrNot(true,
				"//h1[contains(text(),'BrowserEmulator.open')]", 5000);
	}

	// TODO
	// /**
	// * 网页超时
	// * @param Be
	// */
	// public static void overTime(BrowserEmulator Be) {
	// Be.click("//a[contains(text(),'BrowserEmulator.open')]");
	// Be.expectElementExistOrNot(true,
	// "//div[@id='errorTitle']/h1[text()='无法连接']", 8000);
	// }

	/**
	 * 点击click链接，进入click操作页面
	 * 
	 * @param Be
	 */
	public static void clickOperations(BrowserEmulator Be) {
		Be.click("//a[contains(text(),'BrowserEmulator.click')]");
		Be.expectElementExistOrNot(true,
				"//h1[contains(text(),' BrowserEmulator.click')]", 5000);
	}

	/**
	 * 点击Button按钮
	 * 
	 * @param Be
	 */
	public static void clickButton(BrowserEmulator Be) {
		Be.click("//input[@value='点击button，触发Ajax']");
		Be.expectElementExistOrNot(true, "//h1[(text()='点击了button，触发了Ajax')]",
				5000);
	}

	/**
	 * 点击链接
	 * 
	 * @param Be
	 */
	public static void clickLink(BrowserEmulator Be) {
		Be.click("//a[(text()='点击<a>节点，页面跳转')]");
		Be.expectElementExistOrNot(true,
				"//h1[text()='Dagger测试系统 - SanityTest']", 5000);
		Be.click("//a[contains(text(),'BrowserEmulator.click')]");
	}

	/**
	 * 输入内容，点击Button提交表单
	 * 
	 * @param Be
	 * @param input
	 */
	public static void submitForm(BrowserEmulator Be, String input) {
		Be.type("//input[@value='在此输入内容']", input);
		Be.click("//input[@value='点击<input>，提交表单']");
		Be.expectElementExistOrNot(true, "//h1[text()='" + input + "']", 5000);
	}

	// TODO
	// /**
	// * 点击不可点击部分
	// * @param Be
	// */
	// public static void clickNothing(BrowserEmulator Be){
	// Be.click("");
	// Be.expectElementExistOrNot(true,
	// "//h1[text()='本页面用于测试 - BrowserEmulator.click(String locator)']", 5000);
	// }

	/**
	 * 点击type链接，进入type操作页面
	 * 
	 * @param Be
	 */
	public static void typeOperations(BrowserEmulator Be) {
		Be.click("//a[contains(text(),'BrowserEmulator.type')]");
		Be.expectElementExistOrNot(true,
				"//h1[contains(text(),'BrowserEmulator.type')]", 5000);
	}

	/**
	 * 在input中输入文本
	 * 
	 * @param Be
	 * @param input
	 */
	public static void typeInInput(BrowserEmulator Be, String input) {
		Be.type("//input[@value='在<input>输入文本']", input);
		Be.click("//input[@value='提交<input>节点文本']");
		Be.expectElementExistOrNot(true, "//h1[text()='" + input + "']", 5000);
		Be.open("http://" + ip + ":" + port + "/type");
	}

	/**
	 * 在iframe中输入文本
	 * 
	 * @param Be
	 * @param input
	 */
	public static void typeInIframe(BrowserEmulator Be, String input) {
		Be.type("//body/iframe", input);
		Be.click("//input[@value='提交<iframe>节点文本']");
		Be.expectElementExistOrNot(true, "//h1[contains(text(),'" + input
				+ "')]", 5000);
		Be.open("http://" + ip + ":" + port + "/type");
	}

	/**
	 * 在textarea中输入文本
	 * 
	 * @param Be
	 * @param input
	 */
	public static void typeInTextarea(BrowserEmulator Be, String input) {
		Be.type("//textarea[text()='在<textarea>输入文本']", input);
		Be.click("//input[@value='提交<textarea>节点文本']");
		Be.expectElementExistOrNot(true, "//h1[text()='" + input + "']", 5000);
		Be.open("http://" + ip + ":" + port + "/type");
	}

	/**
	 * 点击mouseOver链接，进入mouseOver操作页面
	 * 
	 * @param Be
	 */
	public static void mouseOverOperations(BrowserEmulator Be) {
		Be.click("//a[contains(text(),'BrowserEmulator.mouseOver')]");
		Be.expectElementExistOrNot(true, "//a[text()='CSS下拉菜单']", 5000);
		Be.expectElementExistOrNot(true, "//a[text()='JS下拉菜单']", 5000);
	}

	/**
	 * mouseOverCSS下拉框
	 * 
	 * @param Be
	 */
	public static void mouseOverCSS(BrowserEmulator Be) {
		Be.mouseOver("//a[text()='CSS下拉菜单']");
		Be.expectElementExistOrNot(true, "//a[text()='甲']", 5000);
		Be.expectElementExistOrNot(true, "//a[text()='乙']", 5000);
		Be.expectElementExistOrNot(true, "//a[text()='丙']", 5000);
		Be.expectElementExistOrNot(true, "//a[text()='丁']", 5000);
	}

	/**
	 * mouseOverJS下拉框
	 * 
	 * @param Be
	 */
	public static void mouseOverJS(BrowserEmulator Be) {
		Be.mouseOver("//a[text()='JS下拉菜单']");
		Be.expectElementExistOrNot(true, "//a[text()='AAA']", 5000);
		Be.expectElementExistOrNot(true, "//a[text()='BBB']", 5000);
		Be.expectElementExistOrNot(true, "//a[text()='CCC']", 5000);
		Be.expectElementExistOrNot(true, "//a[text()='DDD']", 5000);
	}

	/**
	 * 点击getText链接，进入getText操作页面
	 * 
	 * @param Be
	 */
	public static void getTextOperations(BrowserEmulator Be) {
		Be.click("//a[contains(text(),'BrowserEmulator.getText')]");
		Be.expectElementExistOrNot(true,
				"//h1[contains(text(), BrowserEmulator.getText)]", 5000);
	}

	/**
	 * 点击isTextPresentOperations，进入操作页面
	 * 
	 * @param Be
	 */
	public static void isTextPresentOperations(BrowserEmulator Be) {
		Be.click("//a[contains(text(),'BrowserEmulator.isTextPresent')]");
		Be.expectElementExistOrNot(true,
				"//h1[contains(text(),'BrowserEmulator.isTextPresent')]", 5000);
	}

	/**
	 * 判断页面上文本可见不可见
	 * 
	 * @param Be
	 * @param text
	 */
	public static void isTextPresent(BrowserEmulator Be, String text,
			boolean expect) {
		if (expect) {
			if (!Be.isTextPresent(text, 3000)) {
				Assert.fail();
			}
		} else {
			if (Be.isTextPresent(text, 3000)) {
				Assert.fail();
			}
		}
	}

	/**
	 * 点击isElementPresent，进入操作页面
	 * 
	 * @param Be
	 */
	public static void isElementPresentOperations(BrowserEmulator Be) {
		Be.click("//a[contains(text(),'BrowserEmulator.isElementPresent')]");
		Be.expectElementExistOrNot(true,
				"//h1[contains(text(),'BrowserEmulator.isElementPresent')]",
				5000);
	}

	/**
	 * 判断元素在页面可见不可见
	 * 
	 * @param Be
	 * @param XPath
	 */
	public static void isElementPresent(BrowserEmulator Be, String XPath,
			boolean expect) {
		if (expect) {
			if (!Be.isElementPresent(XPath, 3000)) {
				Assert.fail();
			}
		} else {
			if (Be.isElementPresent(XPath, 3000)) {
				Assert.fail();
			}
		}
	}

	/**
	 * 点击selectPicInWindowsTop链接，进入操作页面
	 * 
	 * @param Be
	 */
	public static void selectPicInWindowsTopOpera(BrowserEmulator Be) {
		Be.click("//a[contains(text(),'BrowserEmulator.selectPicInWindowsPop')]");
		Be.expectElementExistOrNot(true,
				"//h1[contains(text(),'选择文件后执行JS代码')]", 5000);
	}

	/**
	 * 点击selectWindow,进入操作页面
	 * 
	 * @param Be
	 */
	public static void selectWindowOperations(BrowserEmulator Be) {
		Be.click("//a[contains(text(),'BrowserEmulator.selectWindow')]");
		Be.expectElementExistOrNot(true,
				"//a[text()='Click me to open a new window']", 5000);
	}

	/**
	 * 点击<a>，另开Tab页
	 * 
	 * @param Be
	 */
	public static void selectWindow(BrowserEmulator Be, String title) {
		Be.click("//a[text()='Click me to open a new window']");
		Be.selectWindow(title);
		Be.expectElementExistOrNot(true, "//h1[text()='Welcome aboard']", 5000);
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
	 * @param Be
	 * @throws InterruptedException
	 */
	public static void executeJS(BrowserEmulator Be)
			throws InterruptedException {
		JavascriptExecutor js = (JavascriptExecutor) Be.getBrowserCore();
		js.executeScript("return document.getElementsByTagName('li')[0].getElementsByTagName('a')[0].click()");
		Thread.sleep(5000);
		String text = (String) js
				.executeScript("return document.getElementsByTagName('h1')[0].firstChild.textContent");
		Be.expectTextExistOrNot(true, text, 5000);
	}
}
