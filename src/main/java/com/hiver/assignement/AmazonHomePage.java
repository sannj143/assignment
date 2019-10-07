package com.hiver.assignement;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hiver.assignement.locators.AmazonHomePageLocators;
import com.hiver.utils.WebDriverUtil;

public class AmazonHomePage implements AmazonHomePageLocators {
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(how = How.ID, using = SEARCHBOX)
	private WebElement searchBox;

	@FindBy(how = How.XPATH, using = SUBMIT_BUTTON)
	private WebElement submit;

	public AmazonHomePage(WebDriver driver, WebDriverWait wait) {

		this.driver = driver;
		this.wait = wait;
		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	public void searchText(String text) {
		if (searchBox.isEnabled()) {
			searchBox.click();
			WebDriverUtil.typeWhenReady(driver, wait, searchBox, text);
		}

	}

	public void clickOnSubmit() {
		submit.click();
	}

}
