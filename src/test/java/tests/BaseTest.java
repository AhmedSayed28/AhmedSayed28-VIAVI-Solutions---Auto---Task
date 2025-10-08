package tests;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.*;

import java.awt.*;
import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestResult;
import pages.HomePage;

public class BaseTest {

    private HomePage home;
    protected static ExtentReports extent;
    protected static ExtentSparkReporter reporter;
    protected static ExtentTest logger;

    static WebDriver driver;



    public static void reporter(String status, String stepDetail) throws InterruptedException {

        String base64Screenshot;
        try {
            base64Screenshot = HelperClass.capture(driver);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        if (status.equalsIgnoreCase("pass")) {
            logger.pass(stepDetail, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } else if (status.equalsIgnoreCase("fail")) {
            logger.fail(stepDetail, MediaEntityBuilder.createScreenCaptureFromBase64String(base64Screenshot).build());
        } else if (status.equalsIgnoreCase("info")) {
            logger.info(stepDetail);
        } else if (status.equalsIgnoreCase("Warning")) {
            logger.warning(stepDetail);
        }
    }
    @BeforeSuite
    public void setUpSuite() throws IOException {
        extent = new ExtentReports();
        ExtentSparkReporter spark = new ExtentSparkReporter("Reports/extent-report.html");
        spark.config().setTheme(Theme.DARK);
        extent.attachReporter(spark);
    }
    @BeforeMethod
    public void setUp() throws InterruptedException {
        reporter = new ExtentSparkReporter("Reports/extent-report.html");
        extent.attachReporter(reporter);

        // Initialize the WebDriver instance
        driver = new ChromeDriver();

        driver.manage().window().maximize();
        driver.get("https://demo.prestashop.com/");

        home = new HomePage(driver);
        home.navigateToIframeAndVerify();
    }

    @AfterMethod
    public void tearDown(ITestResult result) throws InterruptedException, IOException {
        if (result.getStatus() == ITestResult.FAILURE) {
            File screenshotFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
            String screenshotPath = "Reports/screenshots/" + result.getName() + ".png";
            FileUtils.copyFile(screenshotFile, new File(screenshotPath));
            logger.fail("Test Failed. See screenshot below:");
            logger.fail(result.getThrowable());
            logger.addScreenCaptureFromPath(screenshotPath);
        }
        Thread.sleep(3000);
        driver.quit();
    }


    @AfterSuite
    public void tearDownSuite() throws IOException {
        // Flush the ExtentReports instance to generate the report then Open it
        extent.flush();
        Desktop.getDesktop().open(new File("Reports/extent-report.html"));
    }
    }
