package com.win.taf.ui;

import com.win.taf.core.TestContext;
import com.win.taf.core.exceptions.TARuntimeException;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.stream.IntStream;

public class JavaScriptUtils {

    private static Logger logger = LoggerFactory.getLogger(JavaScriptUtils.class);
    private static final String HIGH_LIGHT_IGNORE_MESSAGE = "Ignoring the exception captured while highlighting the element";

    private JavaScriptUtils() {
    }

    public static void click(WebDriver driver, Locator locator) {
        performAction(() -> {
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            WebElement element = driver.findElement(locator.getBy());
            javascriptExecutor.executeScript("arguments[0].click();", element);
            SyncUtils.sleep(1000);
        });
    }

    public static String getText(WebDriver driver, Locator locator) {
        JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
        SyncUtils.sleep(1000);
        WebElement element = driver.findElement(locator.getBy());
        return (String) javascriptExecutor.executeScript("return arguments[0].innerText;", element);
    }

    public static void click(WebDriver driver, WebElement element) {
        performAction(() -> {
                    JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
                    javascriptExecutor.executeScript("arguments[0].click()", element);
                }
        );
    }

    public static void setValue(WebDriver driver, Locator locator, String value) {
        performAction(() -> {
            WebElement element = driver.findElement(locator.getBy());
            JavascriptExecutor javascriptExecutor = (JavascriptExecutor) driver;
            javascriptExecutor.executeScript("arguments[0].value='" + value + "';", element);
        });
    }

    public static void performAction(Runnable runnable) {
        try {
            runnable.run();
        } catch (Exception ex) {
            logger.info("Getting exception while performing the Action using java script executor..!!!");
            logger.error("Failed to perform action", ex);
            throw new TARuntimeException(ex);
        }
    }

    public static void highlightElement(WebDriver driver, WebElement element) {
        try {
            TestContext.put("backgroundColor", element.getCssValue("background-color"));
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellowgreen; border: 3px solid red;');", element);
        } catch (Exception e) {
            logger.info(HIGH_LIGHT_IGNORE_MESSAGE);
        }
    }

    public static void highlightElementWithWait(WebDriver driver, WebElement element) {
        try {
            TestContext.put("backgroundColor", element.getCssValue("background-color"));
            IntStream.rangeClosed(1, 10).forEach(counter -> ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'background: yellowgreen; border: 2px solid red;');", element));
        } catch (Exception e) {
            logger.info(HIGH_LIGHT_IGNORE_MESSAGE);
        }
    }

    public static void unhighlightElement(WebDriver driver, WebElement element) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].setAttribute('style', 'border: 0px solid red;');", element);
        } catch (Exception e) {
            logger.info(HIGH_LIGHT_IGNORE_MESSAGE);
        }
    }

    public static void scrollDown(WebDriver driver, int scrollPixel) {
        ((JavascriptExecutor) driver)
                .executeScript("scroll(0," + scrollPixel + ")");
    }

    public static void scrollDownTillElement(WebDriver driver, Locator locator) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", driver.findElement(locator.getBy()));
    }

}
