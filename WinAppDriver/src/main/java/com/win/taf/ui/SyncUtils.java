package com.win.taf.ui;

import com.google.common.collect.ImmutableMap;
import com.win.taf.core.driver.DriverManager;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.Command;
import org.openqa.selenium.remote.CommandExecutor;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.Response;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class SyncUtils {

    private static final int WAIT_IN_SECOND = SystemConstants.DEFAULT_EXPLICITE_WAIT;
    private static Logger logger = LoggerFactory.getLogger(SyncUtils.class);

    private SyncUtils() {
    }

    public static void waitForPageLoad(WebDriver driver, int timeOutInSeconds) {
        long startTime = System.currentTimeMillis();
        try {
            WebDriverWait wait = new WebDriverWait(driver, timeOutInSeconds);
            wait.until(driver1 -> ((RemoteWebDriver) driver1).executeScript("return document.readyState").toString().equals("complete"));
            wait.until((driver1 -> (Boolean) ((JavascriptExecutor) driver).executeScript("return (window.jQuery != null) && (jQuery.active === 0);")));
            logger.info("Actual wait: '{}' milliseconds, Expected was '{}'", (System.currentTimeMillis() - startTime), timeOutInSeconds * 1000);
        } catch (Exception ex) {
            logger.info("Timeout during waitForPageLoad");
        }
    }

    public static void waitForDottedSpinnerToDisappear(int timeOutInSeconds) {
        long startTime = System.currentTimeMillis();
        WebDriverWait wait = new WebDriverWait(DriverManager.getDriver(), timeOutInSeconds);
        try {
            String spnDotted = "//div[contains(@class,'active laf')]//div[@class='slds-spinner_container slds-grid']";
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath(spnDotted)));
            logger.info("waited '{}' milliseconds for spinner to disappear", System.currentTimeMillis() - startTime);
        } catch (Exception e) {
            logger.info("Timeout during waitForDottedSpinnerToDisappear, waited for '{}' milliseconds", System.currentTimeMillis() - startTime);
        }

    }

    public static void waitForClickable(WebDriver driver, Locator locator) {
        waitForClickable(driver, locator, WAIT_IN_SECOND);
    }

    public static void waitForClickable(WebDriver driver, Locator locator, int waitInSeconds) {
        try {
            WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);

            wait.ignoring(NoSuchElementException.class)
                    .until(ExpectedConditions.elementToBeClickable(driver.findElement(locator.getBy())));
        } catch (Exception e) {
            logger.info("Timeout during waitForClickable");
        }
    }

    public static void waitForElementVisibility(WebDriver driver, Locator locator) {
        waitForElementVisibility(driver, locator, WAIT_IN_SECOND);
    }

    public static void waitForElementVisibility(WebDriver driver, Locator locator, int waitInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
        wait.ignoring(NoSuchElementException.class)
                .until(ExpectedConditions.visibilityOf(driver.findElement(locator.getBy())));
    }

    public static void waitForElementInVisibility(WebDriver driver, Locator locator) {
        waitForElementInVisibility(driver, locator, WAIT_IN_SECOND);
    }

    public static void waitForElementInVisibility(WebDriver driver, Locator locator, int waitInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(locator.getBy())));
    }

    public static void waitPresenceOfElement(WebDriver driver, Locator locator) {
        waitPresenceOfElement(driver, locator, WAIT_IN_SECOND);
    }

    public static void waitPresenceOfElement(WebDriver driver, Locator locator, int waitInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, waitInSeconds);
        wait.until(ExpectedConditions.presenceOfElementLocated(locator.getBy()));
    }

    public static void sleep(int timeInMilli) {
        try {
            Thread.sleep(timeInMilli);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            logger.warn("Exception", e);
        }
    }

    public static void enableNetworkThrottling(WebDriver driver) {
        Map map = new HashMap();
        map.put("offline", false);
        map.put("latency", 4);
        map.put("download_throughput", 2000);
        map.put("upload_throughput", 2000);


        CommandExecutor executor = ((ChromeDriver) driver).getCommandExecutor();
        Response response = null;
        try {
            response = executor.execute(
                    new Command(((ChromeDriver) driver).getSessionId(), "setNetworkConditions", ImmutableMap.of("network_conditions", ImmutableMap.copyOf(map)))
            );
        } catch (IOException e) {
            logger.info("Error while throttling browser speed :", e);
        }
        logger.warn("Throttling response '{}'", response);
    }
}
