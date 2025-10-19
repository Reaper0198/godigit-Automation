package tests;

import base.BaseTest;
import pages.CommercialInsurancePage;
import utils.ScreenshotUtils;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommercialInsuranceTest extends BaseTest {

    protected ExtentTest test;
    private static final Logger logger = LogManager.getLogger(CommercialInsuranceTest.class);

    @BeforeTest
    public void testSetup() {
        test = extent.createTest("Car Insurance Search Test");
       
    }

    @Test(priority = 1)
    public void testCarInsuranceSearch() throws Exception {
        CommercialInsurancePage carPage = new CommercialInsurancePage(driver, wait, actions, test);
        carPage.searchCarInsurance();

        try {
            ScreenshotUtils.takeScreenshot(driver);
            test.pass("Screenshot taken successfully");
            logger.info("Screenshot taken successfully.");
        } catch (Exception e) {
            test.fail("Failed to take screenshot");
            logger.error("Failed to take screenshot", e);
        }
    }
}