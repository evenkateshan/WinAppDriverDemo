package com.win.taf.core.driver;

import org.openqa.selenium.MutableCapabilities;
import org.openqa.selenium.UnexpectedAlertBehaviour;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.CapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.win.taf.ui.SystemConstants;

import io.appium.java_client.windows.WindowsDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

/**
 * this is the windowsdriver launcher.
 *
 *
 */
public class WinAppDriverLauncher implements DriverLauncher {
    private String browser;
    private Logger logger = LoggerFactory.getLogger(WinAppDriverLauncher.class);
    private static String appId=SystemConstants.APPID;
    private WindowsDriver winDriver;
    private String baseUrl=SystemConstants.BASEURL;
    int i=0;

    public WinAppDriverLauncher(String browser) {
        this.browser = browser;
    }

    @Override
    public DriverDTO launch() {
           try {
			return initializeTheWindowsLocalDriver();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		return null;
    }

    private DriverDTO initializeTheWindowsLocalDriver() throws MalformedURLException {
        logger.info("launching the windows driver ");
        DesiredCapabilities cap=new DesiredCapabilities();
    	cap.setCapability("app", appId);
    	cap.setCapability("ms:waitForAppLaunch", "25");	
    	
    	winDriver=new WindowsDriver(new URL(baseUrl), cap);
    	
        return new DriverDTO(winDriver);
    }

}

