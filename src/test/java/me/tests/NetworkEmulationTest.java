package me.tests;

import static org.junit.Assert.assertEquals;
import static org.openqa.selenium.devtools.network.Network.emulateNetworkConditions;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.Log;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.ConnectionType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class NetworkEmulationTest {
	private static ChromeDriver chromeDriver;
	private static DevTools chromeDevTools;

	@BeforeClass
	public static void initDriverAndDevTools() {

		chromeDriver = new ChromeDriver();
		chromeDevTools = chromeDriver.getDevTools();
		chromeDevTools.createSession();
	}

	@Test
	public static void enableNetworkOffline() {
		chromeDevTools.send(Network.enable(Optional.of(100000000), Optional.empty(), Optional.empty()));
		chromeDevTools.send(emulateNetworkConditions(false, 100, 200000,100000, Optional.of(ConnectionType.cellular2g)));
	    long startTime = System.currentTimeMillis();
		chromeDriver.get("https://flipkart.com");
	    long endTime = System.currentTimeMillis();
	    System.out.println("=============== Loading time is =========== "+ (endTime-startTime) +"ms");
		chromeDriver.quit();
	}
}
