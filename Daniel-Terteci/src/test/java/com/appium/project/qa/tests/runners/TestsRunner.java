package com.appium.project.qa.tests.runners;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        plugin = {
                "pretty",
                "json:target/cucumber-report/cucumber.json"
        },
        features = "src/test/resources/features",
        glue = {"com.appium.project.qa.tests.steps"},
        stepNotifications = true
)
public class TestsRunner {
}
