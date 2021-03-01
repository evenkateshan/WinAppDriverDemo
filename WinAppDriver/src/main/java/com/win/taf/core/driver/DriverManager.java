package com.win.taf.core.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.remote.SessionId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.win.taf.ui.SystemConstants;

import io.appium.java_client.windows.WindowsDriver;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * This class is responsible for managing the driver initialization maintaining the driver instances per thread for parallel execution.
 * This class provides the global way to access the driver once it is initialized.
 *
 * 
 */
public class DriverManager {

    private static DriverManager instance = null;
    private static Logger logger = LoggerFactory.getLogger(DriverManager.class);
    private static ThreadLocal<WebDriver> webDrivers = new ThreadLocal<>();
    private static ThreadLocal<WindowsDriver> winDrivers = new ThreadLocal<>();
    private static ThreadLocal<String> sessionIds = new ThreadLocal<>();
    private static int i=0;

    private DriverManager() {
    }

    public static DriverManager getInstance() {
        if (instance == null) {
            instance = new DriverManager();
        }
        return instance;
    }

    /**
     * This is the global static method to provide the current webdriver
     *
     * @return Webdriver
     */
    public static WindowsDriver getWinDriver() {
        return winDrivers.get();
    }
    /**
     * This is the global static method to provide the current webdriver
     *
     * @return Webdriver
     */
    public static WebDriver getDriver() {
        return webDrivers.get();
    }


    public static String getSessionIds() {
        return sessionIds.get();
    }


//    /**
//     * this method is responsible for launching the respective webdriver.
//     *
//     * @param browser
//     * @param version
//     * @param platform
//     * @param environment
//     * @param addCapabilities
//     */
//    public static void initializeDriver(String browser, String version, String platform, String environment, Map<String, Object> addCapabilities) {
//        DriverLauncher driverLauncher = DriverLauncherFactory.getDriverLauncher(browser, version, platform, environment, addCapabilities);
//        DriverDTO driverDTO = driverLauncher.launch();
//        webDrivers.set(driverDTO.getDriver());
//        SessionId sessionId = ((RemoteWebDriver) webDrivers.get()).getSessionId();
//        sessionIds.set(sessionId.toString());
//        getDriver().manage().window().maximize();
//        getDriver().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        logger.info("Initialized browser:'{}' sessionID:'{}'  threadID:'{}'", SystemConstants.BROWSER, sessionId, Thread.currentThread().getName());
//    }
    /**
     * this method is responsible for launching the respective webdriver.
     *
     * @param browser
     * @param version
     * @param platform
     * @param environment
     * @param addCapabilities
     */
    public static void initializeWindowsDriver(String browser, String version, String platform, String environment, Map<String, Object> addCapabilities) {
    	if(i==0)
    	{
    	DriverLauncher driverLauncher = DriverLauncherFactory.getDriverLauncher(browser, version, platform, environment, addCapabilities);
    	DriverDTO driverDTO = driverLauncher.launch();
        winDrivers.set(driverDTO.getWinDriver());
        SessionId sessionId = ((RemoteWebDriver) winDrivers.get()).getSessionId();
        sessionIds.set(sessionId.toString());
        logger.info("Initialized Windows Driver");
        i++;
    }
    }

    public static void quitWinDriver() {
       
    }
}
