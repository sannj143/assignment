package com.hiver.assignement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hiver.assignement.locators.CartPageLocators;
import com.hiver.utils.WebDriverUtil;

public class CartPage implements CartPageLocators {
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(how = How.XPATH, using = PLUS_ICON)
	private WebElement clickPlusIcon;

	@FindBy(how = How.XPATH, using = TOTAL_PAYABLE_AMOUNT_AT_LEFT)
	private WebElement pixel3MobilePriceAtLeft;

	@FindBy(how = How.XPATH, using = TOTAL_PAYABLE_AMOUNT_AT_RIGHT)
	private WebElement pixel3MobilePriceAtRight;

	@FindBy(how = How.XPATH, using = QUANTITY)
	private WebElement itemCount;

	public CartPage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;
		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	public void clickOnPlusIcon() throws InterruptedException {
		Thread.sleep(4000);
		if(clickPlusIcon.isDisplayed())
		{
			clickPlusIcon.click();
		}
	}

	public String getMobilePriceOnCartPageLeft() {
		return WebDriverUtil.getTextWhenReady(driver, wait, pixel3MobilePriceAtLeft);
	}

	public String getMobilePriceOnCartPageRight() {
		return WebDriverUtil.getTextWhenReady(driver, wait, pixel3MobilePriceAtRight);
	}

	public String getMobileItemCount() throws InterruptedException {
		Thread.sleep(5000);
		return WebDriverUtil.getAttributeValueWhenReady(driver, wait, itemCount, "value");
	}
}
