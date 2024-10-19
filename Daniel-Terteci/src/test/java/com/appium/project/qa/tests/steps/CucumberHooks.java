package com.appium.project.qa.tests.steps;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.cucumber.java.Before;
import org.springframework.beans.factory.annotation.Autowired;

public class CucumberHooks {

    @Autowired
    private AppiumDriver driver;

    @Before
    public void setupBeforeScenario() {
        System.out.println("Perform before scenario cucumber hook");
        System.out.println("Driver instance " +  driver);
        System.out.println("Perform before scenario cucumber hook");
        if (driver instanceof AndroidDriver) ((AndroidDriver) driver).resetApp();
    }

}
