package com.appium.project.qa.pageobjects;

import org.junit.Assert;
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
public class ProductsPage extends BasePage {
    @FindBy(id = "inventory_container")
    private WebElement products;
    @FindBy(id = "add-to-cart-sauce-labs-backpack")
    private WebElement addToCart;
    @FindBy(id="item_4_title_link")
    private WebElement isElementDisplayed;
    @FindBy(id = "shopping_cart_container")
    private WebElement cartIcon;

    public boolean isProductsContainerDisplayed() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        try {
            return wait.until(ExpectedConditions.visibilityOf(products)).isDisplayed();
        } catch (TimeoutException ex) {
            return false;
        }

    }
    public void addProductsToCart (String productName) {
        addToCart.click();
    }
    public void checkElementOnPage(String backpack) {
        Assert.assertTrue(isElementDisplayed.isDisplayed());
        Assert.assertEquals(isElementDisplayed.getText(), backpack);
    }
    public CartPage clickShoppingCart() {
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);
        wait.until((ExpectedConditions.elementToBeClickable(cartIcon))).click();
        return new CartPage();
    }

}
