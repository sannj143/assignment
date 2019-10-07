package com.hiver.assignement;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.hiver.assignement.locators.HomePageLocators;
import com.hiver.utils.WebDriverUtil;

public class HomePage implements HomePageLocators {
	WebDriver driver;
	WebDriverWait wait;
	@FindBy(how = How.XPATH, using = ELECTRONICS)
	private WebElement electronics;

	@FindBy(how = How.XPATH, using = ELECTRONICS_PIXEL3)
	private WebElement pixel3;
	
	@FindBy(how = How.XPATH, using = SEARCHBOX)
	private WebElement searchBox;
	
	@FindBy(how = How.XPATH, using = SUBMIT)
	private WebElement submit;

	public HomePage(WebDriver driver,WebDriverWait wait) {

		this.driver = driver;
        this.wait = wait;
		// This initElements method will create all WebElements

		PageFactory.initElements(driver, this);

	}

	public void HoverOnElectronics() {
	if(electronics.isDisplayed())
	{
		electronics.click();
	}
	}

	public void selectPixel3() {
		pixel3.click();
	}
	
	public void searchText(String text) {
		if (searchBox.isEnabled()) {
			searchBox.click();
			WebDriverUtil.typeWhenReady(driver, wait, searchBox, text);
		}

	}

	public void clickOnSubmit() throws InterruptedException {
		Thread.sleep(5000);
		searchBox.sendKeys(Keys.ENTER);
	}

}
