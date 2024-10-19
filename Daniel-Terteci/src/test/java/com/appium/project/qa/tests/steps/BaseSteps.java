package com.appium.project.qa.tests.steps;

import com.appium.project.qa.configurations.FrameworkConfiguration;
import com.appium.project.qa.pageobjects.*;
import io.cucumber.spring.CucumberContextConfiguration;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

@CucumberContextConfiguration
@ContextConfiguration(classes = FrameworkConfiguration.class)
public class BaseSteps {
    @Autowired
    LoginPage loginPage;
    @Autowired
    protected ProductsPage productsPage;
    @Autowired
    protected CartPage cartPage;
    @Autowired
    protected CheckoutInfoPage checkoutInfoPage;
    @Autowired
    protected CheckoutOverview checkoutOverview;
    @Autowired
    protected CheckoutCompletePage checkoutCompletePage;
    }
