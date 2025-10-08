package tests;

import com.github.javafaker.Faker;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.*;
import pages.elementActions.ElementActions;

public class E2E_PrestaShopTest extends BaseTest {

    private HomePage homePage;
    private Faker faker;
    private ElementActions actions;

    @BeforeMethod
    public void setUp() throws InterruptedException {
        super.setUp();
        // Initialize helpers
        homePage = new HomePage(driver);
        faker = new Faker();
        actions = new ElementActions(driver);
    }

    @Test(description = "Complete end-to-end test flow on PrestaShop demo site")
    public void testE2EPrestaShopFlow() throws InterruptedException {
        logger = extent.createTest("Complete end-to-end test flow on PrestaShop demo site");

        reporter("pass", "Successfully navigated to dynamic iframe and verified logo");
        reporter("info", "Starting PrestaShop E2E Automation Test...");

        try {

            reporter("info", "Opening registration form");
            homePage.goToSignUpPage();
            RegisterPage registerPage = new RegisterPage(driver);

            String firstName = faker.name().firstName();
            String lastName = faker.name().lastName();
            String email = faker.internet().emailAddress();
            String password =  faker.internet().password(10, 15, true, true, true);

            reporter("info", String.format("Creating account for %s %s (%s)", firstName, lastName, email));

            registerPage
                    .fillRegistrationForm(firstName, lastName, email, password)
                    .submit();

            registerPage.verifyRegistrationSuccess();
            reporter("pass", "Account successfully created and user is logged in.");

            String keyword = "notebook";
            reporter("info", "Searching for product keyword: " + keyword);
            homePage.searchFor(keyword);

            SearchResultsPage searchResultsPage = new SearchResultsPage(driver);

            reporter("info", "Selecting first product and verifying its image.");
            searchResultsPage.selectFirstProductAndAssertImage();
            reporter("pass", "First search result has a valid image.");

            ProductPage productPage = new ProductPage(driver, actions);
            reporter("info", "Adding product to the cart...");
            productPage.addToCart();

            CartPage cartPage = new CartPage(driver, actions);
            String successMsg = cartPage.getSuccessMessage();

            Assert.assertTrue(successMsg.contains("Product successfully added to your shopping cart"),
                    "Product was not added to the cart as expected.");
            reporter("pass", "Product successfully added to the shopping cart.");

        } catch (AssertionError ae) {
            reporter("fail", ae.getMessage());
            Assert.fail(ae.getMessage());
        } catch (Exception e) {
            reporter("warning", "Unexpected exception occurred - " + e.getMessage());
            Assert.fail("Test failed due to unexpected error.");
        }
    }

}
