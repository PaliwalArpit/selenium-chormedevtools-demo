package me.tests;

import java.util.HashMap;
import java.util.Map;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class SimulateDevice {
	private static ChromeDriver chromeDriver;
	private static DevTools chromeDevTools;

	@BeforeClass
	public static void initDriverAndDevTools() {

		chromeDriver = new ChromeDriver();
		chromeDevTools = chromeDriver.getDevTools();
		chromeDevTools.createSession();
	}

	@Test
	public static void performanceMetrics() {
		Map deviceMetrics = new HashMap()
        {{
            put("width", 300);
            put("height", 1000);
            put("mobile", true);
            put("deviceScaleFactor", 50);
        }};
        chromeDriver.executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        chromeDriver.get("https://www.google.com");
	}
}
