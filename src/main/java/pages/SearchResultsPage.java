package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class SearchResultsPage {

    @FindBy(xpath = "//h2[text()='Searched Products']")
    private WebElement searchedProductsHeader;

    @FindBy(xpath = "//div[@class='features_items']/div")
    private List<WebElement> searchedProducts;

    public SearchResultsPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isSearchHeaderVisible() {
        return searchedProductsHeader.isDisplayed();
    }

    public boolean areSearchResultsVisible() {
        return searchedProducts.size() > 0;
    }
}

