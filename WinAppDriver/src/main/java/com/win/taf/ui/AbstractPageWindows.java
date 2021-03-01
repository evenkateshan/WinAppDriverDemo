package com.win.taf.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.win.taf.core.driver.DriverManager;

import io.appium.java_client.windows.WindowsDriver;

import java.util.List;

public class AbstractPageWindows {
    protected final Logger Logger = LoggerFactory.getLogger(this.getClass());
    protected static WindowsDriver winDriver;

    public AbstractPageWindows(WindowsDriver winDriver) {
        this.winDriver = winDriver;
    }

    public static WindowsDriver getWinDriver() {
        return winDriver;
    }

    public void click(Locator locator) {
        ActionsExecutor.click(this.winDriver, locator);
    }

    public void jsClick(Locator locator) {
        JavaScriptUtils.click(this.winDriver, locator);
    }

    public void click(Locator locator, String elementName) {
        ActionsExecutor.click(this.winDriver, locator.format(elementName));
    }

    public void clickByAction(Locator locator) {
        ActionsExecutor.clickByAction(this.winDriver, locator);
    }

    public boolean isElementPresent(Locator locator) {
        try {
            SyncUtils.waitForElementVisibility(winDriver, locator);
            return winDriver.findElement(locator.getBy()).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public void clearText(Locator locator) {
        ActionsExecutor.clearText(this.winDriver, locator);
    }

    public void sendKeys(Locator locator, String value) {
        ActionsExecutor.sendKeys(this.winDriver, locator, value);
    }

    public String getText(Locator locator) {
        return ActionsExecutor.getText(this.winDriver, locator);
    }

    public List<String> getWindowsElementsText(Locator locator) {
        return ActionsExecutor.getWebElementsText(winDriver, locator);
    }

    public void refreshPage() {
        this.winDriver.navigate().refresh();
    }

    public void setTextAndPressEnter(Locator locator, String value) {
        ActionsExecutor.setTextAndPressEnter(this.winDriver, locator, value);
    }

    public void click(WebElement element) {
        ActionsExecutor.click(this.winDriver, element);
    }

    public List<WebElement> findElements(Locator locator) {
        return ActionsExecutor.getWebElements(winDriver, locator);
    }

    public WebElement findElement(Locator locator) {
        return ActionsExecutor.getWebElement(winDriver, locator);
    }

    public boolean isElementDisplayed(Locator locator) {
        return ActionsExecutor.isElementDisplayed(this.winDriver, locator);
    }

    public boolean isElementEnabled(Locator locator) {
        return ActionsExecutor.isElementEnabled(this.winDriver, locator);
    }

    public void scrollToElementUsingActions(Locator locator) {
        ActionsExecutor.scrollToElementUsingActions(this.winDriver, locator);
    }

    public void scrollDownToElementUsingJS(Locator locator) {
        JavaScriptUtils.scrollDownTillElement(winDriver, locator);
    }

    public void selectByVisibleText(Locator locator, String option) {
        ActionsExecutor.selectByVisibleText(winDriver, locator, option);
    }

    public void switchToFrameUsingLocator(Locator locator) {
        ActionsExecutor.switchToFrameUsingLocator(winDriver, locator);
    }

    public void switchToDefaultContent() {
        ActionsExecutor.switchToDefaultContent(winDriver);
    }

    public void selectByIndex(Locator locator, int index) {
        ActionsExecutor.selectByIndex(winDriver,locator,index);
    }
}
