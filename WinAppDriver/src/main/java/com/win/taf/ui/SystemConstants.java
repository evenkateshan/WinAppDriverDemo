package com.win.taf.ui;

import com.saucelabs.saucerest.DataCenter;
import com.win.taf.core.utils.PropertyReader;

public class SystemConstants {
    private static PropertyReader prop = new PropertyReader("system.properties");
    public static final String BROWSER = getBrowser();
    public static final String APPID = getAppId();
    public static final String PLATFORM = getPlatform();
    public static final String BROWSER_VERSION = getBrowserVersion();
    //SauceLab Constants
    public static final String USER_NAME = getUserName();
    public static final String ACCESS_KEY = getAccessKey();
    public static final String SAUCE_HUB_URL = getSauceHubUrl();
    public static final DataCenter SAUCE_DATA_CENTER = getSauceDataCenter();
    public static final String BUILD_NAME = getBuildName();
    //Configuration Constants
    public static final String EXEC_ENV = getExecEnv();
    public static final String CHROME_DRIVER_PATH = getChromeDriverPath();
    public static final String EDGE_DRIVER_PATH = getEdgeDriverPath();
    public static final String IE_DRIVER_PATH = getIeDriverPath();
    public static final String FIREFOX_DRIVER_PATH = getFirefoxDriverPath();
    public static final String SAFARI_DRIVER_PATH = getSafariDriverPath();
    public static final String HEADLESS = getHeadless();
    public static final int DEFAULT_EXPLICITE_WAIT = getDefaultExplicitWait();
    public static final String BASEURL = getBaseUrl();
    
    
    private SystemConstants() {
    }

    private static String getBrowser() {
        return getPropertyAsPerThePriority("browser", "chrome");
    }
    private static String getAppId() {
        return getPropertyAsPerThePriority("appId", "Microsoft.WindowsCalculator_8wekyb3d8bbwe!App");
    }
    private static String getBaseUrl() {
        return getPropertyAsPerThePriority("BaseURL", "http://127.0.0.1:4723");
    }

    private static String getPlatform() {
        return getPropertyAsPerThePriority("platform", "Windows 10");
    }

    private static String getBrowserVersion() {
        return getPropertyAsPerThePriority("browserVersion", "latest");
    }

    private static String getUserName() {
        return getPropertyAsPerThePriority("userName", "defaultUser");
    }

    private static String getAccessKey() {
        return getPropertyAsPerThePriority("accessKey", "defaultAccessKey");
    }

    private static String getSauceHubUrl() {
        return getPropertyAsPerThePriority("sauceHubUrl", "https://ondemand.saucelabs.com/wd/hub");
    }

    private static DataCenter getSauceDataCenter() {
        String sauceHUbUrl = getSauceHubUrl();
        if (sauceHUbUrl.contains(".eu-central-1")) {
            return DataCenter.EU;
        } else if (sauceHUbUrl.contains(".us-east-1")) {
            return DataCenter.US_EAST;
        } else
            return DataCenter.US;
    }

    private static String getExecEnv() {
        return getPropertyAsPerThePriority("exec_env", "local");
    }

    private static String getBuildName() {
        return getPropertyAsPerThePriority("buildName", "DentsplySironaTest");
    }

    private static String getChromeDriverPath() {
        return getPropertyAsPerThePriority("chrome.driver.path", "drivers/chromedriver.exe");
    }

    private static String getEdgeDriverPath() {
        return getPropertyAsPerThePriority("edge.driver.path", "drivers/msedgedriver.exe");
    }

    private static String getIeDriverPath() {
        return getPropertyAsPerThePriority("ie.driver.path", "drivers/IEDriverServer.exe");
    }

    private static String getFirefoxDriverPath() {
        return getPropertyAsPerThePriority("firefox.driver.path", "drivers/geckodriver.exe");
    }

    private static String getSafariDriverPath() {
        return getPropertyAsPerThePriority("safari.driver.path", "drivers/safaridriver.exe");
    }

    private static int getDefaultExplicitWait() {
        String defaultWait = getPropertyAsPerThePriority("default.explicit.wait.seconds", "5");
        return Integer.parseInt(defaultWait.trim());
    }

    private static String getHeadless() {
        return getPropertyAsPerThePriority("headless", "false");
    }

    private static String getPropertyAsPerThePriority(String property, String defaultValue) {
        if (prop != null) {
            return System.getProperty(property, prop.getProperty(property, defaultValue));
        }
        return System.getProperty(property, defaultValue);
    }
}