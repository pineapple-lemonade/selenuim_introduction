package ru.ruzavin.tests;

import lombok.SneakyThrows;
import org.junit.Assert;
import org.junit.Test;
import org.openqa.selenium.WebElement;
import ru.ruzavin.pageObjects.InboxPage;
import ru.ruzavin.pageObjects.SignInEmailPage;
import ru.ruzavin.pageObjects.SignInPasswordPage;

import java.util.List;

public class MainTest extends TestInitializer{

	@SneakyThrows
	@Test
	public void mainTest(){
		driver.get("https://accounts.google.com/ServiceLogin/identifier?service=mail&passive=true&rm=false&continue=https%3A%2F%2Fmail.google.com%2Fmail%2F&ss=1&scc=1&ltmpl=default&ltmplcache=2&emr=1&osid=1&flowName=GlifWebSignIn&flowEntry=ServiceLogin");

		SignInEmailPage signInEmailPage = new SignInEmailPage(driver);
		Assert.assertTrue(signInEmailPage.isInit());
		signInEmailPage.enterEmail("gruzavin25");

		SignInPasswordPage signInPasswordPage = signInEmailPage.submitEmail();
		Thread.sleep(1000);
		Assert.assertTrue(signInPasswordPage.isInit());
		//input your password here
		signInPasswordPage.enterPassword("");
		InboxPage inboxPage = signInPasswordPage.submitPassword();
		Thread.sleep(1000);
		Assert.assertTrue(inboxPage.isInit());

		List<WebElement> inboxEmails = inboxPage.getInboxEmails();
		Assert.assertEquals(3, inboxEmails.size());

		int amountOfSubjectsEmails = inboxPage.getAmountOfSubjectsEmails();
		Assert.assertEquals(2, amountOfSubjectsEmails);

		String emailOfPerson = inboxPage.getEmailOfPerson();
		Assert.assertEquals("ruzavin02@gmail.com", emailOfPerson);

		String subject = "Simbirsoft Тестовое задание.Рузавин";
		inboxPage.writeMessage(emailOfPerson, subject, amountOfSubjectsEmails + " писем с нужной темой");
		Thread.sleep(3000);
	}




}
