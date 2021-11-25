package ru.ruzavin.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInPasswordPage extends PageObject{
	public SignInPasswordPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(xpath = "//div[@id='password']//input[@name='password']")
	private WebElement passwordInput;

	@FindBy(id = "passwordNext")
	private WebElement nextButton;

	public void enterPassword(String password){
		this.passwordInput.clear();
		this.passwordInput.sendKeys(password);
	}

	public InboxPage submitPassword(){
		this.nextButton.click();
		return new InboxPage(driver);
	}

}
