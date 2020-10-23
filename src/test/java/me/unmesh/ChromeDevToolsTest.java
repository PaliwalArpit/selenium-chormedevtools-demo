package me.unmesh;
import java.util.HashMap;
import java.util.Map;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.ConnectionType;
import org.openqa.selenium.devtools.performance.Performance;
import org.openqa.selenium.devtools.performance.model.Metric;
import org.openqa.selenium.devtools.performance.model.TimeDomain;

import java.util.List;

import static java.util.Optional.empty;
import static org.openqa.selenium.devtools.Log.disable;
import static org.openqa.selenium.devtools.Log.enable;
import static org.openqa.selenium.devtools.network.Network.emulateNetworkConditions;
import static org.openqa.selenium.devtools.performance.Performance.getMetrics;

public class ChromeDevToolsTest {

    WebDriver driver;

    // @Before
    // public void setUp(DevTools devtools) {

    //     System.setProperty("webdriver.chrome.driver","/home/apaliwal/QECamp/Selenium4/selenium4/chromedriver");
    //     WebDriver driver= new ChromeDriver();
    //     driver = new ChromeDriver();
    //     // devtools = driver.getDevTools();
    //     devtools.createSession();

    //     //WebDriverManager.chromedriver().setup();
    //     //driver = new ChromeDriver();
    //     driver.manage().window().fullscreen();
    //     //driver.get("https://www.google.com/");
    // }

   @SuppressWarnings("unchecked")
   @Test
   public void geoLocationTest(){
       ChromeDriver driver = new ChromeDriver();
       Map coordinates = new HashMap()
       {{
           put("latitude", 50.2334);
           put("longitude", 0.2334);
           put("accuracy", 1);
       }};
       driver.executeCdpCommand("Emulation.setGeolocationOverride", coordinates);
       driver.get("https://flipkart.com");
       System.out.println(" ==== Coordinates are ===="+coordinates);
   }

    // @Test
    // public void getPerfMetrics(){
    //     devTools.send(Performance.setTimeDomain(TimeDomain.ThreadTicks));
    //     devTools.send(enable());
    //     driver.get("<Ur browser url>");
    //     List<Metric> metrics = devTools.send(getMetrics());
    //     devTools.send(disable());
    // }


    // private static void enableNetworkOffline() {
    //     devTools.send(Network.enable(of(100000000), empty(), empty()));
    //     devTools.send(emulateNetworkConditions(true, 100, 1000, 2000, of(ConnectionType.cellular3g));
    //     driver.get("https://access.redhat.com");
    // }

    @After
    public void tearDown() {
        if(driver != null) {
            driver.close();
            driver.quit();
        }
    }
}
