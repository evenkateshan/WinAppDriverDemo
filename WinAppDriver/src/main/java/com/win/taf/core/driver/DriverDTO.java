package com.win.taf.core.driver;

import org.openqa.selenium.WebDriver;

import io.appium.java_client.windows.WindowsDriver;

import java.util.Objects;


public class DriverDTO {

    private WebDriver driver;
    private WindowsDriver winDriver;
    
    public DriverDTO() {
    }

    public DriverDTO(WebDriver driver) {
        this.driver = driver;
    }
    public DriverDTO(WindowsDriver winDriver) {
        this.winDriver = winDriver;
    }

    public WebDriver getDriver() {
        return driver;
    }
    
    public WindowsDriver getWinDriver() {
        return winDriver;
    }

    public void setDriver(WebDriver driver) {
        this.driver = driver;
    }
    public void setWinDriver(WindowsDriver winDriver) {
        this.winDriver = winDriver;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof DriverDTO)) return false;
        DriverDTO driver1 = (DriverDTO) o;
        return Objects.equals(driver, driver1.driver);
    }

    @Override
    public int hashCode() {
        return Objects.hash(driver);
    }
}
