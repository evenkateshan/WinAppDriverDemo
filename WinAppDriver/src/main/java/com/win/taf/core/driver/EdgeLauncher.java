package com.win.taf.core.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.win.taf.ui.SystemConstants;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * this is the edgelauncher launcher.
 *
 *
 */
public class EdgeLauncher implements DriverLauncher {
    private String browser;
    private String version;
    private String platform;
    private String environment;
    private Map<String, Object> additionalCapabilities;
    private Logger logger = LoggerFactory.getLogger(EdgeLauncher.class);

    public EdgeLauncher(String browser, String version, String platform, String environment, Map<String, Object> additionalCapabilities) {
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
                return initializeEdgeDriverForSauceLab();
            case "local":
            default:
                return initializeEdgeLocalDriver();
        }
    }

    private DriverDTO initializeEdgeLocalDriver() {
        logger.info("launching the '{}' browser", browser);
        EdgeOptions edgeOptions = new EdgeOptions();
        if ("true".equalsIgnoreCase(SystemConstants.HEADLESS)) {
            logger.warn("Headless is 'true'. However, its not supported by '{}' browser", browser);
        }
        edgeOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        WebDriver webDriver = new EdgeDriver(edgeOptions);
        return new DriverDTO(webDriver);
    }

    private DriverDTO initializeEdgeDriverForSauceLab() {
        logger.info("launching the '{}' browser with version '{}' on platform '{}'", browser, version, platform);
        MutableCapabilities edgeCapabilities = getEdgeCapabilities();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(SystemConstants.SAUCE_HUB_URL), edgeCapabilities);
        } catch (MalformedURLException e) {
            logger.error("Error while browser initialization ", e);
        }
        return new DriverDTO(driver);
    }

    private MutableCapabilities getEdgeCapabilities() {
        EdgeOptions edgeOptions = new EdgeOptions();
        edgeOptions.setCapability(CapabilityType.PLATFORM_NAME, SystemConstants.PLATFORM);
        edgeOptions.setCapability(CapabilityType.VERSION, SystemConstants.BROWSER_VERSION);
        edgeOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        edgeOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        String scenarioName = (String) additionalCapabilities.get("name");
        edgeOptions.setCapability("sauce:options", getSauceCapabilities(scenarioName));
        return edgeOptions;
    }

    private MutableCapabilities getSauceCapabilities(String scenarioName) {
        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("username", SystemConstants.USER_NAME);
        sauceCapabilities.setCapability("accessKey", SystemConstants.ACCESS_KEY);
        sauceCapabilities.setCapability("name", scenarioName);
        sauceCapabilities.setCapability("build", SystemConstants.BUILD_NAME);
        return sauceCapabilities;
    }
}
