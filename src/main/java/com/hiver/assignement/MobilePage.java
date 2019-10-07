package com.hiver.assignement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hiver.assignement.locators.MobilePageLocators;
import com.hiver.utils.WebDriverUtil;

public class MobilePage implements MobilePageLocators {
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(how = How.XPATH, using = ADD_TO_CART)
	private WebElement clickAddToCart;

	@FindBy(how = How.XPATH, using = MOBILE_PRICE)
	private WebElement pixel3MobilePrice;

	public MobilePage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;
		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	public void clickOnAddToCart() {
		WebDriverUtil.clickWhenReady(driver, wait, clickAddToCart);
	}

	public String getMobilePriceOnMobilePage() {
		return pixel3MobilePrice.getText();
	}

}
