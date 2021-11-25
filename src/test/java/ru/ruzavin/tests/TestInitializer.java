package ru.ruzavin.tests;

import lombok.SneakyThrows;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.io.File;
import java.util.concurrent.TimeUnit;

public class TestInitializer {
	private static ChromeDriverService service;
	protected WebDriver driver;

	@SneakyThrows
	@BeforeClass
	public static void setUp(){
		service = new ChromeDriverService.Builder()
				.usingDriverExecutable(new File("src/main/resources/chromedriver/chromedriver.exe"))
				.usingAnyFreePort()
				.build();
		service.start();
	}

	@AfterClass
	public static void stopService(){
		service.stop();
	}

	@Before
	public void createDriver(){
		driver = new RemoteWebDriver(service.getUrl(), new ChromeOptions());
		driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
	}

	@After
	public void quitDriver(){
		driver.quit();
	}
}
