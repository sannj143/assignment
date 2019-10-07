package com.hiver.assignement;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.hiver.assignement.locators.HomePageLocators;

public class Assignement2 implements HomePageLocators {

	private static final Logger LOG = Logger.getLogger(Assignement2.class.getName());
	AmazonHomePage amazonHomePage;
	AmazonMobileListingPage amznListingPage;
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

	@BeforeMethod
	public void beforeMethod() {
		LOG.info("++++++++++++++++++++++++++++++++++++++++++");
		LOG.info("************   TEST CASE STARTED **********");
		LOG.info("++++++++++++++++++++++++++++++++++++++++++");
	}

	@Test(priority = 0)

	public void comparePriceBetweenAmazonAndFlipkart() throws InterruptedException {

		driver.get("https://www.amazon.in/");

		// HomePage
		amazonHomePage = new AmazonHomePage(driver, wait);
		amazonHomePage.searchText("iPhone 7 32 gb(black)");
		amazonHomePage.clickOnSubmit();

		// Listing Page Get the mobile text and price
		amznListingPage = new AmazonMobileListingPage(driver, wait);
		String amznMobileName = amznListingPage.getMobileTextOnListingPage();
		String amznMobilePrice = amznListingPage.getMobilePriceOnListingPage().replaceAll(",", "");
		LOG.info("Mobile Name : " + amznMobileName + " :::::::: Mobile Price : " + amznMobilePrice);
		Integer amazonAmount = Integer.parseInt(amznMobilePrice);

		driver.get("https://www.flipkart.com/");

		// Cancellin the login pop up
		driver.findElement(By.xpath(CANCEL)).click();
		// HomePage
		homePage = new HomePage(driver, wait);
		homePage.searchText("iPhone 7 32 gb(black)");
		homePage.clickOnSubmit();

		// Listing Page Get the mobile text and price
		mblListingPage = new MobileListingPage(driver, wait);
		String flipkartMobileName = mblListingPage.getMobileTextOnListingPage();
		String flipkartMobilePrice = mblListingPage.getMobilePriceOnListingPage().substring(1).replaceAll(",", "");
		LOG.info("Mobile Name : " + flipkartMobileName + " :::::::: Mobile Price : " + flipkartMobilePrice);
		Integer flipkartAmount = Integer.parseInt(flipkartMobilePrice);

		if (amazonAmount < flipkartAmount) {
			LOG.info("AMAZON HAS LESSER VALUE FOR " + amznMobileName);
		} else {
			LOG.info("FLIPKART HAS LESSER VALUE FOR " + flipkartMobileName);
		}

	}

	@AfterMethod
	public void afterMethod(ITestResult result) {
		LOG.info("#### TEST METHOD EXECUTED " + result.getName() + " " + result.getTestClass() + " ####");
		if (result.getStatus() == ITestResult.SUCCESS) {
			LOG.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			LOG.info("************   TEST CASE PASSED **********");
			LOG.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		} else if (result.getStatus() == ITestResult.FAILURE) {
			LOG.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			LOG.info("************   TEST CASE FAILED **********");
			LOG.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		} else if (result.getStatus() == ITestResult.SKIP) {
			LOG.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
			LOG.info("************   TEST CASE SKIPPED **********");
			LOG.info("$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$$");
		}
	}

	@AfterTest
	public void tearDown() throws Exception {
		// Close the Chrome browser
		driver.quit();
	}
}
