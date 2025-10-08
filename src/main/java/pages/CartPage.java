package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import pages.elementActions.ElementActions;

public class CartPage {
    private final WebDriver driver;
    private final ElementActions actions;
    private final By successMsg = By.cssSelector("#myModalLabel");

    public CartPage(WebDriver driver, ElementActions actions) {
        this.driver = driver;
        this.actions = actions;
    }

    public String getSuccessMessage(){
        return actions.getTextOf(successMsg);
    }
}
