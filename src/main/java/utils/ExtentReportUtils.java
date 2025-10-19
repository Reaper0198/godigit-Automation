package utils;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentReportUtils {
	
    private static ExtentReports extent;

    public static ExtentReports getInstance() {
        if (extent == null) {
            ExtentSparkReporter spark = new ExtentSparkReporter("C:/Users/2407391/eclipse-workspace/modularFramework/ExtentReport/ExtentReport.html");
            extent = new ExtentReports();
            extent.attachReporter(spark);
        }
        return extent;
    }
}



