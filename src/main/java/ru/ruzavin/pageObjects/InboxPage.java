package ru.ruzavin.pageObjects;

import lombok.Getter;
import lombok.SneakyThrows;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

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

	@FindBy(xpath = "//div[@class='dC']")
	private WebElement submitMessageButton;

	public boolean isInit(){
		return mainMenu.isDisplayed();
	}

	@SneakyThrows
	public int getAmountOfSubjectsEmails(){
		int counter = 0;
		for (WebElement email : inboxEmails){
			email.click();
			Thread.sleep(1000);
			WebElement subject = driver.findElement(By.xpath("//div[@class='ha']"));
			if (subject.getText().substring(0,27).equals("Simbirsoft Тестовое задание")){
				counter++;
			}
			driver.get("https://mail.google.com/mail/u/0/#inbox");
		}
		return counter;
	}

	@SneakyThrows
	public String getEmailOfPerson(){
		String personEmail = "";
		for (WebElement email : inboxEmails){
			email.click();
			Thread.sleep(1000);
			WebElement person = driver.findElement(By.xpath("//span[@class='go']"));
			WebElement subject = driver.findElement(By.xpath("//div[@class='ha']"));
			if (subject.getText().substring(0,27).equals("Simbirsoft Тестовое задание")){
				personEmail = person.getText();
				break;
			}
		}
		return personEmail.substring(1, personEmail.length() - 1);
	}

	@SneakyThrows
	public void writeMessage(String recipient, String subject, String text){
		writeButton.click();
		Thread.sleep(1000);
		recipientInput.click();
		recipientInput.sendKeys(recipient);
		Thread.sleep(1000);
		//subjectInput.click();
		subjectInput.sendKeys(subject);
		Thread.sleep(1000);
		messageInput.click();
		messageInput.sendKeys(text);
		Thread.sleep(1000);
		submitMessageButton.click();
	}
}
