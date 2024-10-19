package com.appium.project.qa.pageobjects;

import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.stereotype.Component;

import java.time.Duration;

@Component
public class CheckoutOverview extends BasePage{
    @FindBy(id = "checkout_summary_container")
    private WebElement checkoutOverview;
    @FindBy(id = "finish")
    private WebElement finishButton;

    public boolean isCheckoutOverviewPageDisplayed() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        try {
            return wait.until(ExpectedConditions.visibilityOf(checkoutOverview)).isDisplayed();
        } catch (TimeoutException e2) {
            System.out.println("TimeoutException occurred while checking if cart page is displayed: " + e2.getMessage());
            return false;
        }
    }

    public CheckoutCompletePage clickFinish() {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofMillis(8000))
                    .pollingEvery(Duration.ofMillis(250))
                    .ignoring(WebDriverException.class);
            wait.until(ExpectedConditions.elementToBeClickable(finishButton)).click();
        } catch (TimeoutException e2) {
            System.out.println("TimeoutException occurred while checking if cart page is displayed: " + e2.getMessage());
        }
        return new CheckoutCompletePage();
    }

}
