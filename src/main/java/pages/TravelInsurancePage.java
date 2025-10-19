package pages;

import java.util.List;

import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.aventstack.extentreports.ExtentTest;

import org.openqa.selenium.interactions.Actions;

public class TravelInsurancePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private Actions actions;
    private JavascriptExecutor js;
    private ExtentTest test;

    private static final Logger logger = LogManager.getLogger(TravelInsurancePage.class);

    public TravelInsurancePage(WebDriver driver, WebDriverWait wait, Actions actions, JavascriptExecutor js, ExtentTest test) {
        this.driver = driver;
        this.wait = wait;
        this.actions = actions;
        this.js = js;
        this.test = test;
    }

    public void searchTravelInsurance() throws InterruptedException {
        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("(//a[@class='qf-switcher'])[4]"))).click();
            Thread.sleep(2000);
            logger.info("Switched to Travel Insurance tab.");
            test.pass("Successfully switched to Travel Insurance tab");
        } catch (Exception e) {
            logger.error("Failed to switch to Travel Insurance tab", e);
            test.fail("Failed to switch to Travel Insurance tab");
        }

        try {
            WebElement input = wait.until(ExpectedConditions.elementToBeClickable(By.id("country-option")));
            input.sendKeys(Keys.DOWN);
            input.sendKeys("Germany");
            Thread.sleep(2000);
            WebElement locationElement = wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector("div[data-search='Germany']")));
            actions.moveToElement(locationElement).click().build().perform();
            Thread.sleep(1000);
            input.sendKeys(Keys.UP);
            logger.info("Entered country: Germany");
            test.pass("Successfully entered the Country");
        } catch (Exception e) {
            logger.error("Failed to enter the Country", e);
            test.fail("Failed to enter the Country");
        }

        try {
            WebElement departureField = wait.until(ExpectedConditions.elementToBeClickable(By.id("departure-date")));
            departureField.click();
            wait.until(ExpectedConditions.elementToBeClickable(By.className("pika-next"))).click();
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='4']"))).click();
            Thread.sleep(2000);
            logger.info("Selected departure date.");
            test.pass("Successfully selected the departure date");
        } catch (Exception e) {
            logger.error("Failed to select departure date", e);
            test.fail("Failed to select departure date");
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[contains(@class, 'calendar__end-wrapper')]//td[@data-day='8']"))).click();
            Thread.sleep(2000);
            logger.info("Selected return date.");
            test.pass("Successfully selected the return date");
        } catch (Exception e) {
            logger.error("Failed to select return date", e);
            test.fail("Failed to select return date");
        }

        try {
            WebElement viewBtn = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[text()='View Prices']")));
            actions.moveToElement(viewBtn).click().build().perform();
            logger.info("Clicked the view price button.");
            test.pass("Successfully clicked the view price button");
        } catch (Exception e) {
            logger.error("Failed to click the view price button", e);
            test.fail("Failed to click the view price button");
        }

        try {
            wait.until(ExpectedConditions.elementToBeClickable(By.id("add-traveller"))).click();
            logger.info("Clicked to add traveller.");

            List<WebElement> dobFields = driver.findElements(By.cssSelector("input[placeholder='DD/MM/YYYY']"));
            dobFields.get(0).sendKeys("01/01/2000");
            dobFields.get(1).sendKeys("01/01/2002");
            Thread.sleep(2000);
            logger.info("Entered DOBs for travellers.");

            WebElement viewPlansBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("continue-btn")));
            js.executeScript("arguments[0].click()", viewPlansBtn);
            Thread.sleep(2000);
            logger.info("Clicked continue to view plans.");
            test.pass("Successfully added 1 traveller");
        } catch (Exception e) {
            logger.error("Failed to add 1 traveller", e);
            test.fail("Failed to add 1 traveller");
        }
    }

    public String getPlanName(int index) {
        try {
            String planName = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='plan-" + index + "']/descendant::h2"))).getText();
            logger.info("Fetched plan name for index " + index + ": " + planName);
            return planName;
        } catch (Exception e) {
            logger.error("Failed to fetch plan name for index " + index, e);
            return null;
        }
    }

    public String getPlanPrice(int index) {
        try {
            String planPrice = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@id='plan-" + index + "']/descendant::p[3]"))).getText();
            logger.info("Fetched plan price for index " + index + ": " + planPrice);
            return planPrice;
        } catch (Exception e) {
            logger.error("Failed to fetch plan price for index " + index, e);
            return null;
        }
    }
}