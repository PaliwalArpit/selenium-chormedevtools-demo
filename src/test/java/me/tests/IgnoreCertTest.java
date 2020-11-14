package me.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.security.Security;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class IgnoreCertTest {
	private static ChromeDriver chromeDriver;
	private static DevTools chromeDevTools;

	@BeforeClass
	public static void initDriverAndDevTools() {

		chromeDriver = new ChromeDriver();

		chromeDevTools = chromeDriver.getDevTools();
		chromeDevTools.createSession();

	}

	@Test
	public void loadInsecureWebsite() {

		// enable Security
		chromeDevTools.send(Security.enable());

		// set ignore certificate errors
		chromeDevTools.send(Security.setIgnoreCertificateErrors(true));

		// load insecure website
		chromeDriver.get("https://expired.badssl.com/");

		// verify that the page was loaded
		Assert.assertEquals(true, chromeDriver.getPageSource().contains("expired"));

	}
}
