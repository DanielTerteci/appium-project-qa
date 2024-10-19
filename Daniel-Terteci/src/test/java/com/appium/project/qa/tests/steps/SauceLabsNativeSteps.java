package com.appium.project.qa.tests.steps;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.appmanagement.ApplicationState;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.springframework.beans.factory.annotation.Autowired;

import java.time.Duration;

public class SauceLabsNativeSteps extends BaseSteps{

    @Autowired
    private AndroidDriver driver;

    @Given("app state is running in foreground")
    public void appStateIsRunningInForeground() {
        driver.queryAppState("com.saucelabs.mydemoapp.rn").equals(ApplicationState.RUNNING_IN_FOREGROUND);
        driver.getPageSource();
        driver.getContextHandles();
    }

    @When("User opens native menu")
    public void iOpenNativeMenu() {
        getFluentWait()
                .until(ExpectedConditions.elementToBeClickable(AppiumBy.ByAccessibilityId.accessibilityId("open menu")))
                .click();
    }

    @Then("User verifies native menu is opened")
    public void userVerifiesNativeMenuIsOpened() {
        boolean isMenuDisplayed = getFluentWait().until(ExpectedConditions.presenceOfElementLocated(AppiumBy.ByAccessibilityId.accessibilityId("menu item log in"))).isDisplayed();
        Assert.assertTrue("Native menu was not opened", isMenuDisplayed);
    }

    FluentWait<AndroidDriver> getFluentWait() {
        FluentWait<AndroidDriver> fluentWait = new FluentWait<>(driver)
                .withTimeout(Duration.ofMillis(8000))
                .pollingEvery(Duration.ofMillis(250))
                .ignoring(WebDriverException.class);

        return fluentWait;
    }

    @When("User selects login from native menu")
    public void userSelectsLoginFromNativeMenu() {
        driver.findElement(AppiumBy.ByAccessibilityId.accessibilityId("menu item log in")).click();
    }

    @Then("Login native screen is displayed")
    public void loginNativeScreenIsDisplayed() {
        boolean isLoginScreenDisplayed = getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(By.ByXPath.xpath("//*[@content-desc='login screen']"))).isDisplayed();
//        boolean isLoginScreenDisplayed = driver.findElement(By.xpath("//*[@content-desc='login screen']")).isDisplayed();
        Assert.assertTrue("Login screen is not displayed", isLoginScreenDisplayed);
    }

    @When("Context is changed to webview")
    public void iChangeContextToWebview() {
        System.out.println("All possible available contexts :" + driver.getContextHandles());
        System.out.println("Current context is:" + driver.getContext());
        System.out.println("Trying to change context to webview");
        driver.getContextHandles()
                .stream()
                .filter(context -> context.equals("WEBVIEW_com.saucelabs.mydemoapp.rn"))
                .findFirst()
                .ifPresent(webViewContext -> driver.context(webViewContext));
        System.out.println("Current context is:" + driver.getContext());
    }

    @When("User logs in with username {string} and password {string} on the native login screen")
    public void userLogsInWithUsernameAndPasswordOnTheNativeLoginScreen(String username, String password) {
        driver.findElement(AppiumBy.ByAccessibilityId.accessibilityId("Username input field")).sendKeys(username);
        driver.findElement(AppiumBy.ByAccessibilityId.accessibilityId("Password input field")).sendKeys(password);
    }

    @Then("User will not see login screen")
    public void userWillNotSeeLoginScreen() {
        boolean isLoginScreenDisplayed = getFluentWait().until(ExpectedConditions.visibilityOfElementLocated(By.ByXPath.xpath("//*[@content-desc='login screen']"))).isDisplayed();
        Assert.assertFalse("Login Screen is displayed", !isLoginScreenDisplayed);
    }

    @When("User selects Webview from native menu")
    public void userSelectsWebviewFromNativeMenu() {
        driver.findElement(AppiumBy.ByAccessibilityId.accessibilityId("menu item webview")).click();
    }

    @Then("Webview selection is displayed")
    public void webviewSelectionIsDisplayed() {
        boolean isWebviewDisplayed = getFluentWait().until(ExpectedConditions.presenceOfElementLocated(AppiumBy.ByAccessibilityId.accessibilityId("webview selection screen"))).isDisplayed();
        Assert.assertTrue("Webview page was not opened", isWebviewDisplayed);
    }

    @When("User enters {string} in webview selection and clicks enter")
    public void userEntersInWebviewSelectionAndClicksEnter(String saucedemo) {
        driver.findElement(AppiumBy.ByAccessibilityId.accessibilityId("URL input field")).sendKeys(saucedemo);
        driver.findElement(AppiumBy.ByAccessibilityId.accessibilityId("Go To Site button")).click();
    }

    @And("User logs in with username {string} and password {string} on the webview login screen")
    public void userLogsInWithUsernameAndPasswordOnTheWebviewLoginScreen(String username, String password) {
        loginPage.enterUsername(username);
        loginPage.enterPassword(password);
        loginPage.clickLoginButton();
    }

    //EXTRA TESTS

    @When("User adds to cart product {string}")
    public void userAddsToCartProduct(String productName) {
        productsPage.addProductsToCart(productName);
    }

    @Then("Product {string} is added to cart")
    public void productIsAddedToCart(String backpack) {
        productsPage.checkElementOnPage(backpack);
    }

    @When("User goes to shopping cart")
    public void userGoesToShoppingCart() {
        productsPage.clickShoppingCart();
    }
    @Then("Shopping carts page is displayed")
    public void shoppingCartsPageIsDisplayed() {
        Assert.assertTrue("Cart page is displayed", cartPage.isCartPageDisplayed());
    }

    @When("User checks out")
    public void userChecksOut() {
        cartPage.clickCheckout();
    }

    @Then("Checkout Info page is displayed")
    public void checkoutInfoPageIsDisplayed() {
        Assert.assertTrue("Checkout Page is displayed", checkoutInfoPage.isCheckoutPageDisplayed());
    }
    @When("User enters checkout info {string} {string} {string}")
    public void userEntersCheckoutInfo(String checkoutFirstName, String checkoutLastName, String postalCode) {
        checkoutInfoPage.enterFirstName(checkoutFirstName);
        checkoutInfoPage.enterLastName(checkoutLastName);
        checkoutInfoPage.enterPostalCode(postalCode);
    }

    @And("User continues on Checkout Info page")
    public void userContinuesOnCheckoutInfoPage() {
        checkoutInfoPage.clickContinue();
    }

    @Then("Checkout Overview is displayed")
    public void checkoutOverviewIsDisplayed() {
        Assert.assertTrue("CheckoutOverview is displayed", checkoutOverview.isCheckoutOverviewPageDisplayed());
    }

    @When("User finishes on Checkout Overview page")
    public void userFinishesOnCheckoutOverviewPage() {
        checkoutOverview.clickFinish();
    }

    @Then("Checkout Complete page is displayed")
    public void checkoutCompletePageIsDisplayed() {
        Assert.assertTrue("Checkout Complete Page is displayed", checkoutCompletePage.isCheckoutCompletePageDisplayed());
    }
    @When("User goes back to  Products page")
    public void userGoesBackToProductsPage() {
        checkoutCompletePage.clickBackHomeButton();
    }
    @Then("Products page is displayed")
    public void productsPageIsDisplayed() {
        Assert.assertTrue("Products are not displayed", productsPage.isProductsContainerDisplayed());
    }

}
