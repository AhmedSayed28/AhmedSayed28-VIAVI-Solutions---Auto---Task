package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import pages.elementActions.ElementActions;

public class RegisterPage {

    private final WebDriver driver;
    private final ElementActions actions;

    private final By title = By.cssSelector("label[for='field-id_gender-1'] span[class='custom-radio']");
    private final By firstName = By.id("field-firstname");
    private final By lastName = By.id("field-lastname");
    private final By email = By.id("field-email");
    private final By password = By.id("field-password");
    private final By allCheckBoxes = By.cssSelector("div[class=\"col-md-6 js-input-column\"] label");
    private final By saveBtn = By.cssSelector("button[type='submit']");
    private final By registrationForm = By.cssSelector("form[id='customer-form']");
    private final By signOut = By.cssSelector("a.logout");

    public RegisterPage(WebDriver driver) {
        this.driver = driver;
        this.actions = new ElementActions(driver);
    }

    public RegisterPage fillRegistrationForm(String fName, String lName, String mail, String pass) {
                 actions.click(title)
                .fillField(firstName, fName)
                .fillField(lastName, lName)
                .fillField(email, mail)
                .fillField(password, pass)
                .clickAll(allCheckBoxes);
        return this;
    }

    public RegisterPage submit(){
        actions.submit(registrationForm);
        System.out.println("✅ Registration form submitted.");
        return this;
    }

    public RegisterPage verifyRegistrationSuccess() {
        Assert.assertTrue(actions.isElementVisible(signOut), "❌ Registration failed!");
        System.out.println("✅ User registered successfully.");
        return this;
    }
}
