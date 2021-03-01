package com.win.taf.core.reporting.extent;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.ExtentTest;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.reporter.ExtentHtmlReporter;
import com.aventstack.extentreports.reporter.configuration.Theme;
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

/**
 *
 */

public class ExtentHTMLReportObserver implements HTMLReportObserver {

    private static Logger logger = LoggerFactory.getLogger(ExtentHTMLReportObserver.class);
    public static Properties prop = loadConfigFile("system.properties");
    private static ExtentReports extentReports = new ExtentReports();
    private static ThreadLocal<ExtentTest> extentTestThreadLocal = new ThreadLocal<>();
    private static final String OUTPUT_DIRECTORY = prop.getProperty("html.report.out.directory", "target/html-report/");
    private static final String HTML_REPORT_NAME = prop.getProperty("html.report.name", "report.html");
    private static final String XML_CONFIG_FILENAME = prop.getProperty("html.report.xmlconfig.name", "extent-config.xml");

    @Override
    public synchronized void setUp() {
        ExtentHtmlReporter htmlReporter = new ExtentHtmlReporter(OUTPUT_DIRECTORY + HTML_REPORT_NAME);
        configureExtentReportFromXml(htmlReporter);
        extentReports.attachReporter(htmlReporter);
    }

    @Override
    public synchronized void tearDown() {
        extentReports.flush();
    }

    @Override
    public synchronized void createTest(String testName) {
        ExtentTest methodTest = extentReports.createTest(testName);
        extentTestThreadLocal.set(methodTest);
    }

    @Override
    public void createTest(String testName, Map<String, Object> additionalParams) {
        ExtentTest methodTest = extentReports.createTest(testName);
        extentTestThreadLocal.set(methodTest);
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
    public synchronized void info(String message) {
        getTest().info(message);
    }

    @Override
    public synchronized void info(String message, Throwable throwable) {
        getTest().info(message);
        getTest().info(throwable);
    }

    @Override
    public synchronized void pass(String message) {
        getTest().pass(message);
    }

    @Override
    public synchronized void fail(String message) {
        getTest().fail(message);
    }

    @Override
    public synchronized void fail(String message, Throwable throwable) {
        getTest().fail(message);
        getTest().fail(throwable);
    }

    @Override
    public synchronized void skip(String message) {
        getTest().skip(message);
    }

    @Override
    public synchronized void skip(String message, Throwable throwable) {
        getTest().skip(message);
        getTest().skip(throwable);
    }

    @Override
    public synchronized void warning(String message) {
        getTest().warning(message);
    }

    @Override
    public synchronized void warning(String message, Throwable throwable) {
        getTest().warning(message);
        getTest().warning(throwable);
    }

    @Override
    public synchronized void error(String message) {
        getTest().error(message);
    }

    @Override
    public synchronized void error(String message, Throwable throwable) {
        getTest().error(message);
        getTest().error(throwable);
    }


    @Override
    public synchronized void addScreenshot(String screenshotName, String description) {
        WebDriver driver = DriverManager.getDriver();
        if (driver != null) {
            String newFileName = screenshotName + new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
            TakesScreenshot takesScreenshot = (TakesScreenshot) driver;
            File destFile = new File(OUTPUT_DIRECTORY + "/screenshots/" + newFileName + ".png");
            File src = takesScreenshot.getScreenshotAs(OutputType.FILE);
            try {
                FileUtils.copyFile(src, destFile);
                String imageRelativePath = "./screenshots/" + destFile.getName();
                getTest().info(description, MediaEntityBuilder.createScreenCaptureFromPath(imageRelativePath).build());
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

    private synchronized ExtentTest getTest() {
        return extentTestThreadLocal.get();
    }

    private synchronized void configureExtentReportFromXml(ExtentHtmlReporter htmlReporter) {

        try {
            String configXmlFile = getClass().getClassLoader().getResource(XML_CONFIG_FILENAME).getPath();
            logger.info("Loading Extent Report configuration parameters from XML File{}", configXmlFile);
            htmlReporter.loadXMLConfig(configXmlFile);
        } catch (Exception ex) {
            //setting the Dark mode for default. Rest Settings keeping default
            logger.info("Got the exception while reading Extent report parameters from xml file. " +
                    "So setting Dark Mode and keeping rest as default");
            htmlReporter.config().setTheme(Theme.DARK);
        }

    }
}
