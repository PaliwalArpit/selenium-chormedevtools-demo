package me.tests;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.Command;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableMap;

public class CrashChromeTest {
	private static ChromeDriver chromeDriver;
    private static DevTools chromeDevTools;
    
	@BeforeClass
	public static void initDriverAndDevTools() {

		chromeDriver = new ChromeDriver();

		chromeDevTools = chromeDriver.getDevTools();
		chromeDevTools.createSession();

	}

	@Test
	public void genericCrashBrowser() {
		chromeDevTools.send(new Command<>("Browser.crash", ImmutableMap.of()));
	}
}
