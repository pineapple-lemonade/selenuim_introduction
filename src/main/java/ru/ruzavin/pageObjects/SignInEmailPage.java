package ru.ruzavin.pageObjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class SignInEmailPage extends PageObject{

	public SignInEmailPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(id = "identifierId")
	private WebElement emailInput;

	@FindBy(id = "identifierNext")
	private WebElement nextButton;

	public void enterEmail(String email){
		this.emailInput.clear();
		this.emailInput.sendKeys(email);
	}

	public SignInPasswordPage submitEmail(){
		nextButton.click();
		return new SignInPasswordPage(driver);
	}

}
