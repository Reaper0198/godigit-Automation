package tests;

import base.BaseTest;
import pages.HealthInsurancePage;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HealthInsuranceTest extends BaseTest {

    protected ExtentTest test;
    private static final Logger logger = LogManager.getLogger(HealthInsuranceTest.class);

    @BeforeTest
    public void testSetup() {
        test = extent.createTest("Health Insurance Search Test");
        
    }

    @Test(priority = 1)
    public void testHealthInsuranceSearch() throws InterruptedException {
        HealthInsurancePage healthPage = new HealthInsurancePage(driver, wait, js, test);
        healthPage.searchHealthInsurance();
        logger.info("Executed health insurance search.");
    }
}