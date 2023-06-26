package com.automation.pages;

import com.automation.utils.DriverUtils;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.Select;
import org.junit.Assert;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class HomePage extends BasePage {

    @FindBy(xpath = "//button[contains(@id, 'add-to-cart')]")
    WebElement item1;

    @FindBy(xpath = "//a[@class = 'shopping_cart_link']")
    WebElement cart;

    @FindBy(xpath = "//select[@class='product_sort_container']")
    WebElement filter;

    @FindBy(id = "react-burger-menu-btn")
    WebElement burgerIcon;

    @FindBy(id = "logout_sidebar_link")
    WebElement logoutLink;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    List<WebElement> priceList;

    public void clickOnAddToCartBtnOfFirstProduct() {
        item1.click();
    }

    public void clickOnCartIcon() {
        cart.click();
    }

    public void verifyHomePage() {
        Assert.assertTrue(cart.isDisplayed());
        Assert.assertTrue(filter.isDisplayed());
    }

    public void clickOnHamburgerIcon() {
        burgerIcon.click();
    }

    public void clickOnLogoutLink() {
        logoutLink.click();
    }

    public void clickOnFilterDropDown() {
        filter.click();
    }

    public void selectSortOptionHighToLow() {
        Select selectDropdown = new Select(filter);
        selectDropdown.selectByValue("hilo");  // Select the "Price (high to low)" option

        // Wait for the page to refresh and sort the items
        DriverUtils.waitForPageToLoad();
    }

    public void verifyProductsSortedByPriceHighToLow() {
        List<Double> prices = new ArrayList<>();

        // Get the prices of all items and store them in a list
        for (WebElement priceElement : priceList) {
            double price = Double.parseDouble(priceElement.getText().replace("$", ""));
            prices.add(price);
        }

        // Sort the prices list in descending order
        Collections.sort(prices, Collections.reverseOrder());

        // Display the prices in the console from high to low
        System.out.println("Prices of all items (high to low):");
        for (double price : prices) {
            System.out.println(price);
        }

        // Create a copy of the prices list for comparison
        List<Double> sortedPrices = new ArrayList<>(prices);

        // Assert that the prices list is sorted from high to low
        Assert.assertEquals("Products are not sorted by price from high to low.", sortedPrices, prices);
    }
}
