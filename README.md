# Selenium 4 and Chome DEv Tools Integration
This repo hosts examples for new features and changes introduced in Selenium 4

One of the features added in the new version of Selenium (4.0.0-alpha-2) is a very nice Interface for Chrome DevTools API in Java.

In this post, we will review the benefits we can gain from using the DevTools API and will go over some basic examples for some of the capabilities.

DevTools API offers great capabilities for controlling the Browser and the Web Traffic. The complete API can be found here: [DevTools] https://chromedevtools.github.io/devtools-protocol/)  

Here is a list of some of the things we can achieve by using DevTools API with Selenium:
- URL filtering
- Adding custom headers for requests
- Intercepting requests/responses and acting like a “Proxy”
- Get performance and Metrics of our Browser/Network
- Leverage Console capabilities
- Emulate network conditions
- Perform security operations

First, make sure you have the latest Selenium version from Maven:
```
<dependency>
    <groupId>org.seleniumhq.selenium</groupId>
    <artifactId>selenium-java</artifactId>
    <version>4.0.0-alpha-2</version>
</dependency
```
