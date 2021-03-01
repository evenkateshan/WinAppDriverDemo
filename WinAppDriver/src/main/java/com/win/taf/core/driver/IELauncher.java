package com.win.taf.core.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.ie.InternetExplorerOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.win.taf.ui.SystemConstants;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * this is the IElauncher launcher.
 *
 *
 */
public class IELauncher implements DriverLauncher {
    private String browser;
    private String version;
    private String platform;
    private String environment;
    private Map<String, Object> additionalCapabilities;
    private Logger logger = LoggerFactory.getLogger(IELauncher.class);

    public IELauncher(String browser, String version, String platform, String environment, Map<String, Object> additionalCapabilities) {
        this.browser = browser;
        this.version = version;
        this.platform = platform;
        this.environment = environment;
        this.additionalCapabilities = additionalCapabilities;
    }

    @Override
    public DriverDTO launch() {
        switch (environment.toLowerCase()) {
            case "sauce":
                return initializeIEDriverForSauceLab();
            case "local":
            default:
                return initializeIELocalDriver();
        }
    }

    private DriverDTO initializeIELocalDriver() {
        logger.info("launching the '{}' browser", browser);
        System.setProperty("webdriver.ie.driver", SystemConstants.IE_DRIVER_PATH);
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        if ("true".equalsIgnoreCase(SystemConstants.HEADLESS)) {
            logger.warn("Headless is 'true'. However, its not supported by '{}' browser", browser);
        }
        internetExplorerOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        internetExplorerOptions.disableNativeEvents();
        internetExplorerOptions.ignoreZoomSettings();
        internetExplorerOptions.setCapability("cssSelectorsEnabled", true);
        WebDriver webDriver = new InternetExplorerDriver(internetExplorerOptions);
        return new DriverDTO(webDriver);
    }

    private DriverDTO initializeIEDriverForSauceLab() {
        logger.info("launching the '{}' browser with version '{}'", browser, version);
        MutableCapabilities ieCapabilities = getIECapabilities();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(SystemConstants.SAUCE_HUB_URL), ieCapabilities);
        } catch (MalformedURLException e) {
            logger.error("Error while browser initialization ", e);
        }
        return new DriverDTO(driver);
    }

    private MutableCapabilities getIECapabilities() {
        InternetExplorerOptions internetExplorerOptions = new InternetExplorerOptions();
        internetExplorerOptions.setCapability("browserVersion", version);
        internetExplorerOptions.setCapability("platformName", platform);
        internetExplorerOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        internetExplorerOptions.disableNativeEvents();
        internetExplorerOptions.ignoreZoomSettings();
        String scenarioName = (String) additionalCapabilities.get("name");
        internetExplorerOptions.setCapability("sauce:options", getSauceCapabilities(scenarioName));
        return internetExplorerOptions;
    }

    private MutableCapabilities getSauceCapabilities(String scenarioName) {
        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("username", SystemConstants.USER_NAME);
        sauceCapabilities.setCapability("accessKey", SystemConstants.ACCESS_KEY);
        sauceCapabilities.setCapability("seleniumVersion", "3.141.59");
        sauceCapabilities.setCapability("name", scenarioName);
        sauceCapabilities.setCapability("build", SystemConstants.BUILD_NAME);
        return sauceCapabilities;
    }
}
