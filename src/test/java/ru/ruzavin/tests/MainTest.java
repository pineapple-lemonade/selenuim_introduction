package ru.ruzavin.tests;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import ru.ruzavin.pageObjects.InboxPage;
import ru.ruzavin.pageObjects.SignInEmailPage;
import ru.ruzavin.pageObjects.SignInPasswordPage;


public class MainTest extends TestInitializer{

	@SneakyThrows
	@Test
	public void mainTest(){
		driver.get("https://mail.google.com");

		SignInEmailPage signInEmailPage = new SignInEmailPage(driver);
		signInEmailPage.enterEmail("gruzavin25");

		SignInPasswordPage signInPasswordPage = signInEmailPage.submitEmail();
		//input your password here
		signInPasswordPage.enterPassword("");
		InboxPage inboxPage = signInPasswordPage.submitPassword();

		String amountOfSubjectsEmailsAndSenderAddress = inboxPage.getAmountOfSubjectsEmailsAndSenderAddress();
		int amountOfSubjectsEmails = Integer.parseInt(amountOfSubjectsEmailsAndSenderAddress.split(",")[0]);
		String emailOfPerson = amountOfSubjectsEmailsAndSenderAddress.split(",")[1];
		Assert.assertEquals(2, amountOfSubjectsEmails);
		Assert.assertEquals("ruzavin02@gmail.com", emailOfPerson);

		String subject = "Simbirsoft Тестовое задание.Рузавин";
		inboxPage.writeMessage(emailOfPerson, subject, amountOfSubjectsEmails + " писем с нужной темой");
	}


}
