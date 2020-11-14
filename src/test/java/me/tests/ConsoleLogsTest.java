package me.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Console;
import org.openqa.selenium.devtools.DevTools;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConsoleLogsTest {
	private static ChromeDriver chromeDriver;
	private static DevTools chromeDevTools;

	@BeforeClass
	public static void initDriverAndDevTools() {

		chromeDriver = new ChromeDriver();

		chromeDevTools = chromeDriver.getDevTools();
		chromeDevTools.createSession();

	}

	@Test
	public void verifyConsoleMessageAdded() {

		String consoleMessage = "Hello Selenium 4";

		// enable Console
		chromeDevTools.send(Console.enable());

		// add listener to verify the console message
		chromeDevTools.addListener(Console.messageAdded(), consoleMessageFromDevTools -> Assert.assertEquals(true,
				consoleMessageFromDevTools.getText().equals(consoleMessage)));

		chromeDriver.get("https://apache.org");

		// execute JS - write console message
		chromeDriver.executeScript("console.log('" + consoleMessage + "');");

	}

	@AfterClass
	public void tearDown() {
		if (chromeDriver != null) {
			chromeDriver.close();
			chromeDriver.quit();
		}
	}
}
