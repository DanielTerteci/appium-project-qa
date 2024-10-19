
package com.appium.project.qa.configurations;


import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import javax.annotation.PreDestroy;
import java.net.URL;

@Configuration
@ComponentScan(basePackages = "com.appium.project.qa")
@PropertySource("classpath:framework.properties")
public class FrameworkConfiguration {

    private AndroidDriver driver;

    @Bean
    public AndroidDriver getAndroidDriver() {
        System.setProperty("webdriver.chrome.driver", "E:\\chromedrivers\\chromedriver103.exe");
        try {
            //for appium 1.x (appium server gui) the path is http://127.0.0.1:4445/wd/hub
            //for appium 2.0 beta (installed from node) the path is like this, without /wd/hub
            driver = new AndroidDriver(new URL("http://127.0.0.1:4445/"), getDesiredCapabilities());
        } catch (Exception exception) {
            throw new RuntimeException("Android driver could not be started", exception);
        }

        return driver;
    }

    private DesiredCapabilities getDesiredCapabilities() {
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "13");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, "UiAutomator2");
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, "AndroidDevice");
        desiredCapabilities.setCapability(MobileCapabilityType.NEW_COMMAND_TIMEOUT, 2000);
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, true);
        //Update to your apk path
        desiredCapabilities.setCapability(MobileCapabilityType.APP, "C:\\Program Files (x86)\\apks\\SauceLabDemo.apk");
        //Update to your chromedriver path. This must match the browser version(android webview version) on mobile device. It will fail to change context if not properly set
        desiredCapabilities.setCapability(UiAutomator2Options.CHROMEDRIVER_EXECUTABLE_OPTION, "E:\\chromedrivers\\chromedriver103.exe");

        return desiredCapabilities;
    }

    @PreDestroy
    public void destroy() {
        if (driver != null) {
            driver.terminateApp("com.saucelabs.mydemoapp.rn");
            driver.quit();
            System.out.println("Driver quit was called");
        } else System.out.println("Cannot quit driver because it's already null");
    }
}
