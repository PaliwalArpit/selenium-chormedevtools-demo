package me.tests;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

public class CustomHeadersTest {
	private static ChromeDriver chromeDriver;
	private static DevTools chromeDevTools;

	@BeforeClass
	public static void initDriverAndDevTools() {

		chromeDriver = new ChromeDriver();

		chromeDevTools = chromeDriver.getDevTools();
		chromeDevTools.createSession();

	}

	@Test
	public void addCustomHeaders() {

		// enable Network
		chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

		// set custom header
		chromeDevTools.send(Network.setExtraHTTPHeaders(ImmutableMap.of("customHeaderName", "text/html")));

		// add event listener to verify that requests are sending with the custom header
		chromeDevTools.addListener(Network.requestWillBeSent(), requestWillBeSent -> Assert
				.assertEquals(requestWillBeSent.getRequest().getHeaders().get("customHeaderName"), "text/html"));

		chromeDriver.get("https://apache.org");

	}

	@AfterClass
	public void tearDown() {
		if (chromeDriver != null) {
			chromeDriver.close();
			chromeDriver.quit();
		}
	}
}
