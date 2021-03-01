package com.win.taf.core;

import com.win.taf.core.driver.DriverManager;
import com.win.taf.core.reporting.Reporter;
import com.win.taf.core.reporting.TestNGHTMLReportAdapter;
import com.win.taf.core.reporting.extent.ExtentHTMLReportObserver;
import com.win.taf.core.utils.SauceUtils;
import com.win.taf.ui.SystemConstants;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.ITestResult;
import org.testng.annotations.*;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

@Listeners({TestNGHTMLReportAdapter.class})
public class TAFBaseTestClass {

    private static Logger logger = LoggerFactory.getLogger(TAFBaseTestClass.class);

    @BeforeSuite
    public void beforeSuiteBase(){
        System.out.printf("<=============================On Before Suite======================================>");
        Reporter.register(new ExtentHTMLReportObserver());
    }

    @BeforeClass(alwaysRun = true)
    public void beforeBaseClassMethod(){
    }

    @BeforeMethod(alwaysRun = true)
    public void beforeBaseMethod(Method testCaseMethod){
        String testCaseName = testCaseMethod.getName();
        logger.info("TestCase '{}' is started...!!!", testCaseName);
        Map<String, Object> additionalCapabilities = new HashMap<>();
        additionalCapabilities.put("testCaseName", testCaseName);
      // DriverManager.initializeDriver(SystemConstants.BROWSER, SystemConstants.BROWSER_VERSION, SystemConstants.PLATFORM, SystemConstants.EXEC_ENV, additionalCapabilities);
    }

    @AfterMethod(alwaysRun = true)
    public void afterBaseMethod(ITestResult result) {
        if(SystemConstants.EXEC_ENV.equalsIgnoreCase("sauce")) {
            SauceUtils.updateResults(getTestResultStatus(result), DriverManager.getSessionIds());
        }
        //DriverManager.quitDriver();
    }

    @AfterClass(alwaysRun = true)
    public void afterBaseClass(){
    }

    public WebDriver driver(){
        return DriverManager.getDriver();
    }

    private boolean getTestResultStatus(ITestResult result){
        if (ITestResult.SUCCESS == result.getStatus())
            return true;
        else
            return false;
    }
}
