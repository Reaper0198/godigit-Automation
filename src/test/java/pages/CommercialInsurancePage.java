package pages;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CommercialInsurancePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private ExtentTest test;
    private static final Logger logger = LogManager.getLogger(CommercialInsurancePage.class);

    public CommercialInsurancePage(WebDriver driver, WebDriverWait wait, Actions actions, ExtentTest test) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
        this.test = test;
    }

    public void searchCarInsurance() throws InterruptedException {
        Thread.sleep(2000);
        logger.info("Starting car insurance search.");

        try {
            WebElement regField = wait.until(ExpectedConditions.elementToBeClickable(By.id("registration-search")));
            actions.moveToElement(regField).click().perform();
            logger.info("Clicked on registration field.");

            WebElement phoneField = wait.until(ExpectedConditions.elementToBeClickable(By.id("car-mobile-number")));
            actions.moveToElement(phoneField).click().sendKeys("9999999999")
                .keyDown(Keys.ENTER).keyUp(Keys.ENTER).build().perform();
            logger.info("Entered mobile number and submitted.");

            Thread.sleep(2000);
            test.pass("Successfully added mobile number");
        } catch (Exception e) {
            logger.error("Failed to add mobile number", e);
            test.fail("Failed to add mobile number");
        }
    }
}