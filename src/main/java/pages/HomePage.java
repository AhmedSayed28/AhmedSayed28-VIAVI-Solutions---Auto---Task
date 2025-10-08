package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.elementActions.ElementActions;

public class HomePage {

    private final WebDriver driver;
    private final ElementActions actions;

    // ===== Locators =====
    private final By iframeLocator = By.tagName("iframe");
    private final By loaderLocator = By.xpath("//div[@id='loadingMessage']//img");
    private final By logoLocator = By.xpath("//img[@alt='PrestaShop']");
    private final By signInButton = By.cssSelector("div.user-info a");
    private final By signUpButton = By.cssSelector("a[data-link-action='display-register-form']");
    private final By searchBox = By.cssSelector("input[name='s']");
    private final By searchForm = By.cssSelector("#search_widget form");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ElementActions(driver);
    }

    public HomePage waitForLoaderToDisappear() {
        actions.waitForInvisibility(loaderLocator);
        return this;
    }

    private String getIframeSrc() {
        return actions.getAttribute(iframeLocator, "src");
    }

    public HomePage navigateToIframeAndVerify() {
        waitForLoaderToDisappear();
        String iframeSrc = getIframeSrc();
        driver.get(iframeSrc);
        Assert.assertTrue(actions.isElementVisible(logoLocator), "❌ Logo is not displayed!");
        System.out.println("✅ Navigated to PrestaShop iframe successfully.");
        return this;
    }

    public RegisterPage goToSignUpPage() {
        actions.click(signInButton)
                .click(signUpButton);
        return new RegisterPage(driver);
    }

    public void searchFor(String keyword) throws InterruptedException {
        actions.fillField(searchBox, keyword);
        actions.submit(searchForm);
        System.out.println("🔍 Searching for: " + keyword);
    }
    
}
