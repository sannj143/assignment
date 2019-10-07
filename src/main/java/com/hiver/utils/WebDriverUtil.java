package com.hiver.utils;

import java.util.logging.Logger;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.google.common.base.Function;

public class WebDriverUtil {
	private static final Logger LOG = Logger.getLogger(WebDriverUtil.class.getName());
	public static int WEBDRIVER_TIMEOUT = 10;
	public static int DRIVER_DEFAULT_WAIT_TIMEOUT = 50;
	public static final long SLEEP_IN_BETWEEN_POLLS = 500L;

	public static void clickWhenReady(WebDriver driver, WebDriverWait wait, WebElement el) {
		findElementWhenReady(driver, wait, el).click();
	}

	public static void clear(WebDriver driver, WebDriverWait wait, WebElement el) {
		findElementWhenReady(driver, wait, el).clear();
	}

	public static boolean isVisible(WebDriver driver, WebDriverWait wait, WebElement el) {
		return findElementWhenReady(driver, wait, el).isDisplayed();
	}

	public static void clickWithKeysWhenReady(WebDriver driver, WebDriverWait wait, WebElement el) {
		findElementWhenReady(driver, wait, el).sendKeys(Keys.ENTER);
	}

	public static void typeWhenReady(WebDriver driver, WebDriverWait wait, WebElement el, String text) {
		findElementWhenReady(driver, wait, el).sendKeys(text);
	}

	public static void selectFromDropDown(WebDriver driver, WebDriverWait wait, WebElement el, String value) {
		Select dropDown = new Select(findElementWhenReady(driver, wait, el));
		dropDown.selectByValue(value);
	}

	public static WebElement findElementWhenReady(final WebDriver driver, WebDriverWait wait, final WebElement el) {
		return wait.until(new Function<WebDriver, WebElement>() {
			public WebElement apply(WebDriver driver) {
				try {
					if (isElementDisplayedOrEnabled(el, driver)) {
						return el;
					}
				} catch (Exception e) {
					LOG.warning(String.format("Locator didn't match any element."));
				}
				return null;
			}
		});
	}

	public static boolean isElementDisplayedOrEnabled(WebElement el, WebDriver driver) {
		if (driver instanceof HtmlUnitDriver) {
			if (el.isEnabled()) {
				LOG.info(String.format("Element is enabled [" + el + "]"));
				return true;
			} else {
				LOG.warning("Element not enabled..");
				return false;
			}
		} else {
			// Take element into view
			((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", el);
			if (el.isDisplayed()) {
				LOG.info(String.format("Element displayed [" + el + "]"));
				return true;
			} else {
				LOG.warning("Element [" + el + "] not enabled or not yet rendered..");
				return false;
			}
		}
	}

	public static boolean isElementEnabled(WebElement el, WebDriver driver) {
		if (el.isEnabled()) {
			return true;
		} else {
			LOG.warning("Element [" + el + "] not enabled..");
			return false;
		}
	}

	public static String getTextWhenReady(WebDriver driver, WebDriverWait wait, WebElement el) {
		return findElementWhenReady(driver, wait, el).getText();
	}

	public static String getAttributeValueWhenReady(WebDriver driver, WebDriverWait wait, WebElement el,
			String attributeName) {
		return findElementWhenReady(driver, wait, el).getAttribute(attributeName);
	}

}
