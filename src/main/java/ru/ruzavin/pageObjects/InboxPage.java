package ru.ruzavin.pageObjects;

import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

@Getter
public class InboxPage extends PageObject{
	public InboxPage(WebDriver driver) {
		super(driver);
	}

	@FindBy(className = "gb_uc")
	private WebElement mainMenu;

	@FindBy(xpath = "//*[@class='zA yO']")
	private List<WebElement> inboxEmails;

	@FindBy(xpath = "//div[@class='T-I T-I-KE L3']")
	private WebElement writeButton;

	@FindBy(xpath = "//textarea[@aria-label='Кому']")
	private WebElement recipientInput;

	@FindBy(xpath = "//input[@name='subjectbox']")
	private WebElement subjectInput;

	@FindBy(xpath = "//div[@class='Am Al editable LW-avf tS-tW']")
	private WebElement messageInput;

	@FindBy(xpath = "//div[@class='dC']//*[contains(text(),'Отправить')]")
	private WebElement submitMessageButton;

	@SneakyThrows
	public String getAmountOfSubjectsEmailsAndSenderAddress(){
		int counter = 0;
		String emailToSend = "null";
		for (WebElement email : inboxEmails){
			WebElement subject = email.findElement(By.className("bog"));
			if (subject.getText().equals("Simbirsoft Тестовое задание")){
				WebElement senderEmail = driver.findElement(By.className("yP"));
				emailToSend = senderEmail.getAttribute("email");
				counter++;
			}
		}
		return counter + "," + emailToSend;
	}


	@SneakyThrows
	public void writeMessage(String recipient, String subject, String text){
		WebDriverWait wait = new WebDriverWait(driver, 2);
		writeButton.click();
		wait.until(ExpectedConditions.elementToBeClickable(submitMessageButton));
		recipientInput.sendKeys(recipient);
		subjectInput.sendKeys(subject);
		messageInput.sendKeys(text);
		submitMessageButton.click();
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.xpath("//*[text()='Письмо отправлено.']"))));
	}
}
