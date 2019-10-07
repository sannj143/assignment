package com.hiver.assignement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hiver.assignement.locators.AmazonListingPageLocators;
import com.hiver.assignement.locators.MobileListingPageLocators;
import com.hiver.utils.WebDriverUtil;

public class AmazonMobileListingPage implements AmazonListingPageLocators {
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(how = How.XPATH, using = MOBILE_TEXT)
	private WebElement mobileText;

	@FindBy(how = How.XPATH, using = MOBILE_PRICE)
	private WebElement mobilePrice;

	public AmazonMobileListingPage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;
		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	public String getMobileTextOnListingPage() {
		return WebDriverUtil.getTextWhenReady(driver, wait, mobileText);
	}

	public String getMobilePriceOnListingPage() {
		return WebDriverUtil.getTextWhenReady(driver, wait, mobilePrice);
	}

}
