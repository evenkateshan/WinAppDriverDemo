package com.win.taf.core.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.safari.SafariDriver;
import org.openqa.selenium.safari.SafariOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.win.taf.ui.SystemConstants;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class SafariLauncher implements DriverLauncher {


    private String browser;
    private String version;
    private String platform;
    private String environment;
    private Map<String, Object> additionalCapabilities;
    private Logger logger = LoggerFactory.getLogger(SafariLauncher.class);

    public SafariLauncher(String browser, String version, String platform, String environment, Map<String, Object> additionalCapabilities) {
        this.browser = browser;
        this.version = version;
        this.platform = platform;
        this.environment = environment;
        this.additionalCapabilities = additionalCapabilities;
    }

    @Override
    public DriverDTO launch() {
        logger.info("launching the '{}' browser with version '{}' on platform {}", browser, version, platform);
        switch (environment.toLowerCase()) {
            case "sauce":
                return initializeTheSafariDriverForSauceLab();
            case "local":
            default:
                return initializeTheSafariLocalDriver();
        }
    }

    private DriverDTO initializeTheSafariLocalDriver() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        WebDriver webDriver = new SafariDriver();
        return new DriverDTO(webDriver);
    }

    private DriverDTO initializeTheSafariDriverForSauceLab() {
        MutableCapabilities safariCapabilities = getSafariCapabilities();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL(SystemConstants.SAUCE_HUB_URL), safariCapabilities);
        } catch (MalformedURLException e) {
            logger.error("Error while browser initialization ", e);
        }
        return new DriverDTO(driver);
    }

    private MutableCapabilities getSafariCapabilities() {
        SafariOptions safariOptions = new SafariOptions();
        safariOptions.setCapability("safariOptions", true);
        safariOptions.setCapability(CapabilityType.PLATFORM_NAME, SystemConstants.PLATFORM);
        safariOptions.setCapability(CapabilityType.BROWSER_NAME, SystemConstants.BROWSER);
        safariOptions.setCapability(CapabilityType.VERSION, SystemConstants.BROWSER_VERSION);
        safariOptions.setCapability(CapabilityType.SUPPORTS_JAVASCRIPT, true);
        safariOptions.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        safariOptions.setCapability(CapabilityType.SUPPORTS_FINDING_BY_CSS, true);
        String scenarioName = (String) additionalCapabilities.get("name");
        safariOptions.setCapability("sauce:options", getSauceCapabilities(scenarioName));
        return safariOptions;
    }

    private MutableCapabilities getSauceCapabilities(String scenarioName) {
        MutableCapabilities sauceCapabilities = new MutableCapabilities();
        sauceCapabilities.setCapability("username", SystemConstants.USER_NAME);
        sauceCapabilities.setCapability("accessKey", SystemConstants.ACCESS_KEY);
        sauceCapabilities.setCapability("autoAcceptAlerts", true);
        sauceCapabilities.setCapability(CapabilityType.UNEXPECTED_ALERT_BEHAVIOUR, UnexpectedAlertBehaviour.ACCEPT);
        sauceCapabilities.setCapability("seleniumVersion", "3.141.59");
        sauceCapabilities.setCapability("name", scenarioName);
        sauceCapabilities.setCapability("build", SystemConstants.BUILD_NAME);
        return sauceCapabilities;
    }
}
