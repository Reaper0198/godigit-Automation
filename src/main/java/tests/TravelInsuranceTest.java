package tests;

import base.BaseTest;
import pages.TravelInsurancePage;
import utils.ExcelUtils;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class TravelInsuranceTest extends BaseTest {

    protected ExtentTest test;
    private static final Logger logger = LogManager.getLogger(TravelInsuranceTest.class);

    @BeforeTest
    public void testSetup() {
        test = extent.createTest("Travel Insurance Search Test");
        
    }

    @Test(priority = 1)
    public void testTravelInsurancePlans() throws Exception {
        TravelInsurancePage travelPage = new TravelInsurancePage(driver, wait, actions, js, test);
        travelPage.searchTravelInsurance();
        logger.info("Executed travel insurance search.");

        try {
            ExcelUtils.writeHeader();
            test.pass("Successfully created and added headings to excel file");
            logger.info("Excel header written successfully.");
        } catch (Exception e) {
            test.fail("Failed to create excel file");
            logger.error("Failed to write Excel header", e);
        }

        try {
            for (int i = 0; i < 3; i++) {
                String planName = travelPage.getPlanName(i);
                String planPrice = travelPage.getPlanPrice(i);
                ExcelUtils.writePlan(planName, planPrice, i + 1);
                logger.info("Written plan {}: {} - {}", i + 1, planName, planPrice);
            }

            ExcelUtils.saveToFile();
            test.pass("Successfully added travel insurance data to excel file");
            logger.info("Excel file saved with travel insurance data.");
        } catch (Exception e) {
            test.fail("Failed to add travel insurance data to excel file");
            logger.error("Failed to write travel insurance data to Excel", e);
        }
    }
}