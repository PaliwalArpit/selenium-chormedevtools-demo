package me.tests;

import java.util.Optional;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.network.Network;
import org.openqa.selenium.devtools.network.model.InterceptionStage;
import org.openqa.selenium.devtools.network.model.RequestPattern;
import org.openqa.selenium.devtools.network.model.ResourceType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.google.common.collect.ImmutableList;

public class InterceptRequestTest {
	private static ChromeDriver chromeDriver;
    private static DevTools chromeDevTools;
    
    @BeforeClass
    public static void initDriverAndDevTools() {

        chromeDriver = new ChromeDriver();

        chromeDevTools = chromeDriver.getDevTools();
        chromeDevTools.createSession();

    }
    
    @Test
    public void interceptRequestAndContinue() {

        //enable Network
        chromeDevTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));

        //add listener to intercept request and continue
        chromeDevTools.addListener(Network.requestIntercepted(),
                requestIntercepted -> chromeDevTools.send(
                        Network.continueInterceptedRequest(requestIntercepted.getInterceptionId(),
                                Optional.empty(),
                                Optional.empty(),
                                Optional.empty(), Optional.empty(),
                                Optional.empty(),
                                Optional.empty(), Optional.empty())));

        //set request interception only for css requests
        RequestPattern requestPattern = new RequestPattern("*.css", ResourceType.Stylesheet, InterceptionStage.HeadersReceived);
        chromeDevTools.send(Network.setRequestInterception(ImmutableList.of(requestPattern)));

        chromeDriver.get("https://apache.org");

    }
}
