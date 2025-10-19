package base;

import java.io.FileOutputStream;
import java.io.IOException;
import java.time.Duration;
import java.io.File;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeSuite;

import com.aventstack.extentreports.ExtentReports;

import utils.ExcelUtils;
import utils.ExtentReportUtils;

public class BaseTest {
	protected static final Logger logger=LogManager.getLogger(BaseTest.class);
    protected WebDriver driver;
    protected JavascriptExecutor js;
    protected Actions actions;
    protected WebDriverWait wait;
    protected static ExtentReports extent;
    
    @BeforeSuite
    public void reportSetup() {
    	logger.info("Initializing Extent Reports");
    	extent = ExtentReportUtils.getInstance();
    }

    @BeforeClass
    public void browserSetup() throws IOException {
    	logger.info("Launching Chrome browser and navigating to site");
        driver = new ChromeDriver();
        driver.get("https://www.godigit.com/");
        driver.manage().window().maximize();
        js = (JavascriptExecutor) driver;
        actions = new Actions(driver);
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
}

    @AfterClass
    public void teardown() throws IOException {
    	logger.info("Closing browser and cleaning up resources");
        ExcelUtils.workbookClose();
        driver.quit();
    }
    
    @AfterSuite
    public void reportTearDown() {
    	logger.info("Flushing Extent Reports... \n");
    	extent.flush();
    }
}