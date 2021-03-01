package com.win.taf.core.utils;

import com.win.taf.core.driver.DriverManager;
import com.win.taf.ui.SyncUtils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriverException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TAFUtils {
    private static Logger logger = LoggerFactory.getLogger(TAFUtils.class);

    private TAFUtils() {
    }

    public static byte[] captureScreenshotasByte() {
        byte[] screenshotAsByte = null;
        SyncUtils.sleep(2000);
        try {
            screenshotAsByte = ((TakesScreenshot) DriverManager.getDriver()).getScreenshotAs(OutputType.BYTES);
        } catch (WebDriverException e) {
            logger.error(e.getMessage());
        }
        return screenshotAsByte;
    }
}
