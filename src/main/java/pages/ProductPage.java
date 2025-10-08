package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.elementActions.ElementActions;

public class ProductPage {
    private final WebDriver driver;
    private final ElementActions actions;

    private final By addToCartForm = By.cssSelector("form[id='add-to-cart-or-refresh']");

    public ProductPage(WebDriver driver, ElementActions actions) {
        this.driver = driver;
        this.actions = actions;
    }

    public ProductPage addToCart() {
        actions.submit(addToCartForm);
        return this;
    }


}
