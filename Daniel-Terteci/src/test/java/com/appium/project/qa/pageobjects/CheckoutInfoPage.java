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
public class CheckoutInfoPage extends BasePage {
    @FindBy(id = "checkout_info_container")
    private WebElement checkoutInfoPage;
    @FindBy(id = "first-name")
    private WebElement firstNameInput;
    @FindBy(id = "last-name")
    private WebElement lastNameInput;
    @FindBy(id = "postal-code")
    private WebElement postalCodeInput;
    @FindBy(id = "continue")
    private WebElement continueButton;

    public void enterFirstName(String checkoutFirstName) {
        firstNameInput.sendKeys(checkoutFirstName);
    }

    public void enterLastName(String checkoutLastName) {
        lastNameInput.sendKeys(checkoutLastName);
    }

    public void enterPostalCode(String postalCode) {
        postalCodeInput.sendKeys(postalCode);
    }

    public boolean isCheckoutPageDisplayed() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        try {
            return wait.until(ExpectedConditions.visibilityOf(checkoutInfoPage)).isDisplayed();
        } catch (TimeoutException e) {
            System.out.println("TimeoutException occurred while checking if cart page is displayed: " + e.getMessage());
            return false;
        }
    }

    public CheckoutOverview clickContinue() {
        try {
            FluentWait<WebDriver> wait = new FluentWait<>(driver)
                    .withTimeout(Duration.ofMillis(8000))
                    .pollingEvery(Duration.ofMillis(250))
                    .ignoring(WebDriverException.class);
            wait.until((ExpectedConditions.elementToBeClickable(continueButton))).click();
        } catch (TimeoutException e) {
            System.out.println("TimeoutException occurred while checking if cart page is displayed: " + e.getMessage());
        }
        return new CheckoutOverview();
    }
}
