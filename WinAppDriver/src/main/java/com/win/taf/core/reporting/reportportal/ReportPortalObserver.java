package com.win.taf.core.reporting.reportportal;

import com.win.taf.core.driver.DriverManager;
import com.win.taf.core.logging.MessageType;
import com.win.taf.core.reporting.HTMLReportObserver;
import com.win.taf.ui.SystemConstants;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;
import java.util.Properties;

public class ReportPortalObserver implements HTMLReportObserver {

    private static final Logger logger = LoggerFactory.getLogger("ReportPortalLogger");
    public static Properties prop = loadConfigFile("system.properties");
    private static final String OUTPUT_DIRECTORY = prop.getProperty("html.report.out.directory", "target/html-report/");


    @Override
    public void setUp() {

    }

    @Override
    public void tearDown() {

    }

    @Override
    public void createTest(String testName) {


    }

    @Override
    public void createTest(String testName, Map<String, Object> additionalParams) {

    }

    @Override
    public synchronized void log(MessageType messageType, String message) {
        switch (messageType) {
            case WARNING:
                warning(message);
                break;
            case ERROR:
                error(message);
                break;
            case DEBUG:
                error("DEBUG: " + message);
                break;
            case INFO:
            default:
                info(message);
                break;
        }

    }

    @Override
    public void log(MessageType messageType, String message, Throwable throwable) {
        switch (messageType) {
            case WARNING:
                warning(message, throwable);
                break;
            case ERROR:
                error(message, throwable);
                break;
            case DEBUG:
                error("DEBUG: " + message, throwable);
                break;
            case INFO:
            default:
                info(message, throwable);
                break;
        }
    }

    @Override
    public void info(String message) {
        logger.info(message);
    }

    @Override
    public void info(String message, Throwable throwable) {
        logger.info(message, throwable);
    }

    @Override
    public void pass(String message) {
        logger.info("[PASS] {}", message );
        addScreenshot("Pass",message);
    }

    @Override
    public void fail(String message) {
        logger.error(message);
        addScreenshot("Failure",message);
    }

    @Override
    public void fail(String message, Throwable throwable) {

        logger.error(message, throwable);
        addScreenshot("Failure",message);
    }

    @Override
    public void skip(String message) {
        logger.info("[SKIP] {}", message );
    }

    @Override
    public void skip(String message, Throwable throwable) {
        logger.info(message, throwable);
    }

    @Override
    public void warning(String message) {
        logger.warn(message);
    }

    @Override
    public void warning(String message, Throwable throwable) {
        logger.warn(message, throwable);
    }

    @Override
    public void error(String message) {
        logger.error(message);
    }

    @Override
    public void error(String message, Throwable throwable) {
        logger.error(message, throwable);
    }

    @Override
    public void addScreenshot(String screenshotName, String description) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            String newFileName = screenshotName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File destFile = new File(OUTPUT_DIRECTORY + "/rp_screenshots/" + newFileName + ".png");
            File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(src, destFile);
                logger.info("RP_MESSAGE#FILE#{}#{}", destFile.getAbsolutePath() , description);
            } catch (IOException e) {
                logger.info("Getting exception While taking the Screenshots", e);
            }
        }
    }

    private static synchronized Properties loadConfigFile(String propFile) {
        Properties prop = new Properties();
        InputStream resourceAsStream = SystemConstants.class.getClassLoader().getResourceAsStream(propFile);
        try {
            prop.load(resourceAsStream);
        } catch (IOException e) {
            logger.info("Getting Exception while reading the property file.", e);
            return null;
        }
        return prop;
    }
}
