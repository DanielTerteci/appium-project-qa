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
public class CartPage extends BasePage {
    @FindBy(id = "cart_contents_container")
    private WebElement isCartDisplayed;
    @FindBy(id = "checkout")
    private WebElement checkoutButton;

    public boolean isCartPageDisplayed() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        try {
            return wait.until(ExpectedConditions.visibilityOf(isCartDisplayed)).isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("TimeoutException occurred while checking if cart page is displayed: " + e.getMessage());
            return false;
        }
    }

    public CheckoutInfoPage clickCheckout() {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofMillis(8000))
                    .pollingEvery(Duration.ofMillis(250))
                    .ignoring(WebDriverException.class);
            wait.until(ExpectedConditions.elementToBeClickable(checkoutButton)).click();
        } catch (TimeoutException e) {
            System.out.println("TimeoutException occurred while checking if cart page is displayed: " + e.getMessage());
        }
        return new CheckoutInfoPage();
    }

}
