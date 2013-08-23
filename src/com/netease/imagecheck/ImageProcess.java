package com.netease.imagecheck;

import java.awt.AWTException;
import java.awt.Robot;
import java.io.File;
import java.io.IOException;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Point;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.Augmenter;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

/**
 * ImageProcess contains APIs for screen shot and image process
 * @author LingFei
 */
public class ImageProcess {
	
	/**
	 * ScreenShot
	 * 
	 * @param augmentedDriver
	 * @param folderName
	 * @param imageName
	 * @param type
	 * @return path
	 */
	public static String screenShot(WebDriver augmentedDriver, String folderName, String imageName) {
		Robot rb = null;
		try {
			rb = new Robot();
		} catch (AWTException e) {
			e.printStackTrace();
		}
		rb.mouseMove(0, 0);// move mouse to top left corner, for eliminating some effects of hover in pages

		String dirName = null;
		if (Settings.screenShotType == 1) {
			dirName = Settings.sampleImagePath + folderName;
		} else if (Settings.screenShotType == 2) {
			dirName = Settings.contrastImagePath + folderName;
		} else {
			System.err.println("Wrong type！Please check type.properties！");
		}
		if (Settings.browserCoreType == 1 || Settings.browserCoreType == 3) {
			augmentedDriver.manage().window().setPosition(new Point(0, 0));
			augmentedDriver.manage().window().setSize(new Dimension(9999, 9999));
		} else if (Settings.browserCoreType == 2) {
			augmentedDriver = new Augmenter().augment(augmentedDriver);
		} else {
			System.err.println("Wrong type！Please check imagecheck.properties！");
		}		
		try {
			File sourceFile = ((TakesScreenshot) augmentedDriver).getScreenshotAs(OutputType.FILE);
			FileUtils.copyFile(sourceFile, new File(dirName + File.separator + imageName + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return dirName + File.separator + imageName + ".png";
	}

	/**
	 * Clear images
	 * 
	 * @param dir
	 */
	public static void deleteFiles(String dir) {
		try {
			File file = new File(dir);
			if (!file.exists()) {
				return;
			}
			if (!file.isDirectory()) {
				return;
			}
			String[] tempList = file.list();
			File temp = null;
			for (int i = 0; i < tempList.length; i++) {
				if (dir.endsWith(File.separator)) {
					temp = new File(dir + tempList[i]);
				} else {
					temp = new File(dir + File.separator + tempList[i]);
				}
				if (temp.isFile()) {
					temp.delete();
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Image process
	 * 
	 * @param driver
	 * @param folderName
	 * @param checkPoint
	 * @param xpath
	 */
	public static void process(WebDriver driver, String folderName, String checkPoint, String xpath) throws Exception {
		WebDriverWait wait = new WebDriverWait(driver, 10);
		WebElement we = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(xpath)));
		we = findElement(driver, By.xpath(xpath));
		String sampleImage = checkPoint + "_sample";
		String differenceImage = checkPoint + "_difference";
		String actualImage = null;
		if (Settings.screenShotType == 1) {
			actualImage = ImageProcess.screenShot(driver, folderName, sampleImage);
		} else if (Settings.screenShotType == 2) {
			actualImage = ImageProcess.screenShot(driver, folderName, checkPoint);
			ImageContrast.contrastImages(Settings.sampleImagePath + folderName + File.separator + sampleImage, actualImage, folderName + File.separator + differenceImage, we);
		} else {
			System.err.println("Wrong type！Please check type.properties！");
		}
	}

	private static WebElement findElement(WebDriver driver, By by) {
		WebElement webElement = null;
		try {
			webElement = driver.findElement(by);
		} catch (Exception e) {
			System.err.println(by.toString() + " can not be found！");
		}
		return webElement;
	}
}
