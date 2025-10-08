package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import pages.elementActions.ElementActions;
import java.util.List;

public class SearchResultsPage {

    private final WebDriver driver;
    private final ElementActions actions;
    private final By productsList = By.cssSelector("div.js-product.product");
    private final By productImage = By.cssSelector("img");
    private final By PageMainHeader = By.cssSelector("h1[id='js-product-list-header']");

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ElementActions(driver);
    }

    public void waitForPageToLoad() {
        actions.isElementVisible(PageMainHeader);
    }

    public void selectFirstProductAndAssertImage() {
        List<WebElement> products = actions.waitForVisibilityOfAll(productsList);
        WebElement firstProduct = products.get(0);
        WebElement image = firstProduct.findElement(productImage);

        Assert.assertTrue(image.isDisplayed(), "❌ Product image is not displayed!");
        String imgSrc = image.getAttribute("src");
        Assert.assertTrue(imgSrc != null && !imgSrc.isEmpty(), "❌ Image src is empty!");

        System.out.println("✅ Product image is valid: " + imgSrc);
        firstProduct.click();
    }
}
