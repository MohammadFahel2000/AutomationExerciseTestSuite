package tests;

import base.BaseTest;
import io.qameta.allure.*;
import listeners.TestListener;
import org.testng.Assert;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import pages.HomePage;
import pages.ProductsPage;
import pages.SearchResultsPage;

@Listeners(TestListener.class)
public class TestCase9SearchProduct extends BaseTest {

    // Annotations:
    @Epic("Automation Exercise")
    @Feature("Product Search")
    @Severity(SeverityLevel.CRITICAL)
    @Description("Verify that searched products are visible after entering product name in search input.")
    @Test(description = "Test Case 9: Search Product")
    public void searchProductTest() {
        driver.get("https://automationexercise.com/");

        HomePage homePage = new HomePage(driver);
        ProductsPage productsPage = new ProductsPage(driver);
        SearchResultsPage resultsPage = new SearchResultsPage(driver);

        Assert.assertTrue(homePage.isHomeBtnOrange(), "Home page is not visible");

        homePage.clickProducts();
        Assert.assertTrue(productsPage.isProductsPageVisible(), "Products page is not visible");

        productsPage.searchForProduct("Tshirt");

        // to make the case failed for testing purpose
        Assert.assertTrue(false, "Intentionally failing this test");

        // If the above line uncommented, the below lines will be skipped
        Assert.assertTrue(resultsPage.isSearchHeaderVisible(), "Search header not visible");
        Assert.assertTrue(resultsPage.areSearchResultsVisible(), "No search results displayed");
    }
}
