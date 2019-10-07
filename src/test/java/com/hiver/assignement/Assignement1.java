package com.hiver.assignement;

import java.math.BigDecimal;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hiver.assignement.locators.HomePageLocators;

public class Assignement1 implements HomePageLocators {

	private static final Logger LOG = Logger.getLogger(Assignement1.class.getName());
	HomePage homePage;
	MobileListingPage mblListingPage;
	MobilePage mblPage;
	CartPage cartPage;
	WebDriver driver;
	WebDriverWait wait;
	public static int DRIVER_DEFAULT_WAIT_TIMEOUT = 50;
	public static final long SLEEP_IN_BETWEEN_POLLS = 500L;

	@BeforeTest
	public void setup() {

		System.setProperty("webdriver.chrome.driver", "chromedriver_mac");
		driver = new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		wait = new WebDriverWait(driver, DRIVER_DEFAULT_WAIT_TIMEOUT, SLEEP_IN_BETWEEN_POLLS);
		LOG.info("Chrome Driver Instance loaded successfully.");

	}

	@Test(priority = 0)

	public void verifyTotalPayablePrice() throws InterruptedException {

		driver.get("https://www.flipkart.com/");

		// Cancellin the login pop up
		driver.findElement(By.xpath(CANCEL)).click();
		// HomePage
		homePage = new HomePage(driver, wait);
		homePage.HoverOnElectronics();
		homePage.selectPixel3();

		// Listing Page
		String parentWindowHandle = driver.getWindowHandle();
		mblListingPage = new MobileListingPage(driver, wait);
		mblListingPage.clickOnFirstPhone();
		String listPagePrice = mblListingPage.getMobilePriceOnListingPage();
		LOG.info("Price on Listing Page : " + listPagePrice);

		// MobilePage
		// Switch to new window handle
		Set<String> winHandles = driver.getWindowHandles();
		for (String handle : winHandles) {
			if (!handle.equals(parentWindowHandle)) {
				driver.switchTo().window(handle);
				Thread.sleep(1000);
				LOG.info("Title of the new window: " + driver.getTitle());
			}
		}
		mblPage = new MobilePage(driver, wait);
		String mobilePagePrice = mblPage.getMobilePriceOnMobilePage();
		mblPage.clickOnAddToCart();
		LOG.info("Price on Mobile Page : " + mobilePagePrice);

		// CartPage
		cartPage = new CartPage(driver, wait);
		cartPage.clickOnPlusIcon();
		Thread.sleep(2000);
		String itemCounts = cartPage.getMobileItemCount();
		LOG.info("Number of Items are " + itemCounts);
		
		Assert.assertEquals(listPagePrice, mobilePagePrice);

		String payableAmountString = mobilePagePrice.substring(1).replaceAll(",", "");

		BigDecimal payableAmount = new BigDecimal(payableAmountString).setScale(2);

		BigDecimal itemCount = new BigDecimal(itemCounts).setScale(2);

		BigDecimal expectedTotalPayableAmount = payableAmount.multiply(itemCount).setScale(2);

		LOG.info("Expected Total Amount : " + expectedTotalPayableAmount);

		Assert.assertEquals(cartPage.getMobilePriceOnCartPageLeft(), cartPage.getMobilePriceOnCartPageRight());

		String actualTotalPayableAmountInString = cartPage.getMobilePriceOnCartPageRight().substring(1).replaceAll(",",
				"");

		BigDecimal actualTotalPayableAmount = new BigDecimal(actualTotalPayableAmountInString).setScale(2);

		LOG.info("Expected Total Amount : " + actualTotalPayableAmount);

		Assert.assertEquals(actualTotalPayableAmount, expectedTotalPayableAmount);

	}

	@AfterTest
	public void tearDown() throws Exception {
		// Close the Chrome browser
		driver.quit();
	}
}
