package com.hiver.assignement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hiver.assignement.locators.MobileListingPageLocators;
import com.hiver.utils.WebDriverUtil;

public class MobileListingPage implements MobileListingPageLocators {
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(how = How.XPATH, using = MOBILE_PIXEL3)
	private WebElement selectFirstPhone;

	@FindBy(how = How.XPATH, using = MOBILE_PRICE)
	private WebElement mobilePrice;

	@FindBy(how = How.XPATH, using = MOBILE_TEXT)
	private WebElement mobileText;
	
	public MobileListingPage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;
		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	public void clickOnFirstPhone() throws InterruptedException {
		Thread.sleep(2000);
		if(selectFirstPhone.isEnabled())
		{
			selectFirstPhone.click();
		}
	}

	public String getMobileTextOnListingPage() {
		return WebDriverUtil.getTextWhenReady(driver, wait, mobileText);
	}
	
	public String getMobilePriceOnListingPage() {
		return WebDriverUtil.getTextWhenReady(driver, wait, mobilePrice);
	}

}
