package com.win.taf.core.reporting;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.util.HashMap;
import java.util.Map;

/**
 *
 */

public class TestNGHTMLReportAdapter implements ITestListener {

    public synchronized void onStart(ITestContext context) {
        System.out.printf("<=============================On Start======================================>");
        Reporter.setUp();
    }

    public synchronized void onFinish(ITestContext context) {
        Reporter.tearDown();
    }

    public synchronized void onTestStart(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Map<String, Object> additionalParams = new HashMap<>();
        additionalParams.put("ITestResult", result);
        Reporter.createTest(methodName, additionalParams);
        Reporter.info("TEST EXECUTION STARTED - "+result.getName());
    }

    public synchronized void onTestSuccess(ITestResult result) {
        Reporter.pass("PASSED - "+result.getName());
    }

    public synchronized void onTestFailure(ITestResult result) {
        String methodName = result.getMethod().getMethodName();
        Reporter.fail("FAILED - " + result.getName(), result.getThrowable());
        Reporter.addScreenshot(methodName + "-Failed", "Screenshot After Test Failure..!!!");
    }

    public synchronized void onTestSkipped(ITestResult result) {
        Reporter.skip("SKIPPED - "+result.getName(), result.getThrowable());
    }

    public synchronized void onTestFailedButWithinSuccessPercentage(ITestResult result) {
    }

}

