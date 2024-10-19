Feature: SauceLab Demo Native

  @loginTestNative
  Scenario Outline: Successful login native
    Given app state is running in foreground
    When User opens native menu
    Then User verifies native menu is opened

    When User selects login from native menu
    Then Login native screen is displayed

    When User logs in with username "<username>" and password "<password>" on the native login screen
    Then User will not see login screen


    Examples:
      | username        | password |
      | bob@example.com | 10203040 |
      | bob@example.com | 10203040 |

  @changeContextToWebview
  Scenario Outline: Change context to webview
    Given app state is running in foreground
    When User opens native menu
    Then User verifies native menu is opened

    When User selects Webview from native menu
    Then Webview selection is displayed

      When User enters "https://www.saucedemo.com" in webview selection and clicks enter

    And Context is changed to webview

    And User logs in with username "<username>" and password "<password>" on the webview login screen

#    EXTRA EXERCISE TO ADD AN ITEM IN CART AND FINISH THE FLOW for https://www.saucedemo.com

    When User adds to cart product "Sauce Labs Backpack"
    Then Product "Sauce Labs Backpack" is added to cart

    When User goes to shopping cart
    Then Shopping carts page is displayed

    When User checks out
    Then Checkout Info page is displayed

    When User enters checkout info "<checkoutFirstName>" "<checkoutLastName>" "<postalCode>"
    And User continues on Checkout Info page
    Then Checkout Overview is displayed

    When User finishes on Checkout Overview page
    Then Checkout Complete page is displayed

    When User goes back to  Products page
    Then Products page is displayed

    Examples:
      | username      | password     | checkoutFirstName | checkoutLastName | postalCode |
      | standard_user | secret_sauce | Standard          | user             | 12346      |


