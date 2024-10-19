package com.appium.project.qa.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;


@Component
public class BasePage {

    @Value("${base.url:}")
    private String baseUrl;

   @Autowired
    protected WebDriver driver;


    @PostConstruct
    public void init() {
        System.out.println("Initializing page for class" + this.getClass());
        PageFactory.initElements(driver, this);
    }

    public void goToStartPage() {
        driver.get(baseUrl);
    }

}
