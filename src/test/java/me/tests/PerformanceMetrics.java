package me.tests;

import static org.testng.Assert.assertFalse;

import java.util.List;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.performance.Performance;
import org.openqa.selenium.devtools.performance.model.Metric;
import org.openqa.selenium.devtools.performance.model.TimeDomain;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class PerformanceMetrics {
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
		chromeDevTools.send(Performance.setTimeDomain(TimeDomain.timeTicks));
		chromeDevTools.send(Performance.enable());
		chromeDriver.get("https://apache.org");
		List<Metric> list = chromeDevTools.send(Performance.getMetrics());
		list.forEach(metric -> System.out.println(metric.getName() + " : "+ metric.getValue()));
		assertFalse(list.isEmpty());
		chromeDevTools.send(Performance.disable());		
		
	}
}
