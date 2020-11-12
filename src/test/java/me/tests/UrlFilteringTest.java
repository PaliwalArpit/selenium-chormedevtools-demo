package me.tests;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.BlockedReason;
import org.openqa.selenium.devtools.network.model.ResourceType;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

public class UrlFilteringTest {

    private static ChromeDriver chromeDriver;
    private static DevTools chromeDevTools;

    @BeforeClass
    public static void initDriverAndDevTools() {

        chromeDriver = new ChromeDriver();

        chromeDevTools = chromeDriver.getDevTools();
        chromeDevTools.createSession();

    }
    
    @Test
    public void filterUrls() {

        //enable Network
        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        //set blocked URL patterns
        chromeDevTools.send(Network.setBlockedURLs(ImmutableList.of("*.css", "*.png")));

        //add event listener to verify that css and png are blocked
        chromeDevTools.addListener(Network.loadingFailed(), loadingFailed -> {

            if (loadingFailed.getResourceType().equals(ResourceType.Stylesheet)) {
                Assert.assertEquals(loadingFailed.getBlockedReason(), BlockedReason.inspector);
            }

            else if (loadingFailed.getResourceType().equals(ResourceType.Image)) {
                Assert.assertEquals(loadingFailed.getBlockedReason(), BlockedReason.inspector);
            }

        });

        chromeDriver.get("https://apache.org");

    }
}
