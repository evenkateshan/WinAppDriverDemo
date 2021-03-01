package com.win.stepdefs;

import org.openqa.selenium.WebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.win.taf.core.driver.DriverManager;

import io.appium.java_client.windows.WindowsDriver;

public class BaseStepDef {
    protected final Logger logger = LoggerFactory.getLogger(this.getClass());

    public WebDriver getDriver() {
        return DriverManager.getDriver();
    }
    public WindowsDriver getWinDriver() {
        return DriverManager.getWinDriver();
    }

}
