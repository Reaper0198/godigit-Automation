package pages;

import java.util.List;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.*;

import com.aventstack.extentreports.ExtentTest;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HealthInsurancePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private JavascriptExecutor js;
    private ExtentTest test;
    private static final Logger logger = LogManager.getLogger(HealthInsurancePage.class);

    public HealthInsurancePage(WebDriver driver, WebDriverWait wait, JavascriptExecutor js, ExtentTest test) {
        this.driver = driver;
        this.wait = wait;
        this.js = js;
        this.test = test;
    }

    public void searchHealthInsurance() throws InterruptedException {
        try {
            WebElement healthTab = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='qf-switcher'])[2]")));
            healthTab.click();
            logger.info("Switched to Health Insurance tab.");
            test.pass("Successfully switched to Health Insurance tab");
        } catch (Exception e) {
            logger.error("Failed to switch to Health Insurance tab", e);
            test.fail("Failed to switch to Health Insurance tab ");
        }

        try {
            WebElement pincodeInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@type='text' and @id='resident-pincode-input'])[2]")));
            pincodeInput.sendKeys("603103");
            logger.info("Entered pin code.");
            test.pass("Successfully entered pin code");
        } catch (Exception e) {
            logger.error("Failed to enter pin code", e);
            test.fail("Failed to enter pin code ");
        }

        try {
            WebElement mobileInput = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("(//input[@placeholder='Enter Mobile No.'])[2]")));
            mobileInput.sendKeys("9090090103");
            logger.info("Entered mobile number.");
            test.pass("Successfully entered mobile number");
        } catch (Exception e) {
            logger.error("Failed to enter mobile number", e);
            test.fail("Failed to enter mobile number");
        }

        try {
            WebElement viewPricesBtn = wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//button[@ng-class=\"{ 'btn-loading' : abs.pincodePending }\"]")));
            js.executeScript("arguments[0].click();", viewPricesBtn);
            logger.info("Clicked View Price button.");
            test.pass("Successfully clicked View Price button");
        } catch (Exception e) {
            logger.error("Failed to click View Price button", e);
            test.fail("Failed to click View Price button");
        }

        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//*[contains(@class, 'text-sm') and contains(@class, 'font-bold')]")));
            List<WebElement> menuItems = driver.findElements(By.xpath("//*[contains(@class, 'text-sm') and contains(@class, 'font-bold')]"));
            Thread.sleep(2000);

            if (menuItems.isEmpty()) {
                logger.warn("No menu items found.");
                System.out.println("No menu items found.");
            } else {
                logger.info("Found " + menuItems.size() + " menu items.");
                System.out.println("Found " + menuItems.size() + " menu items:");
                for (WebElement item : menuItems) {
                    System.out.println(item.getText());
                }
            }

            test.pass("Successfully fetched and printed menu options");
        } catch (Exception e) {
            logger.error("Failed to fetch and print menu options", e);
            test.fail("Failed to fetch and print menu options");
        }
    }
}