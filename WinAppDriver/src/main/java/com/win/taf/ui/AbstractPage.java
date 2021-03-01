package com.win.taf.ui;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class AbstractPage {
    protected final Logger Logger = LoggerFactory.getLogger(this.getClass());
    protected WebDriver driver;

    public AbstractPage(WebDriver driver) {
        this.driver = driver;
    }

    public WebDriver getDriver() {
        return this.driver;
    }

    public void click(Locator locator) {
        ActionsExecutor.click(this.driver, locator);
    }

    public void jsClick(Locator locator) {
        JavaScriptUtils.click(this.driver, locator);
    }

    public void click(Locator locator, String elementName) {
        ActionsExecutor.click(this.driver, locator.format(elementName));
    }

    public void clickByAction(Locator locator) {
        ActionsExecutor.clickByAction(this.driver, locator);
    }

    public boolean isElementPresent(Locator locator) {
        try {
            SyncUtils.waitForElementVisibility(driver, locator);
            return driver.findElement(locator.getBy()).isDisplayed();
        } catch (Exception ex) {
            return false;
        }
    }

    public void clearText(Locator locator) {
        ActionsExecutor.clearText(this.driver, locator);
    }

    public void sendKeys(Locator locator, String value) {
        ActionsExecutor.sendKeys(this.driver, locator, value);
    }

    public String getText(Locator locator) {
        return ActionsExecutor.getText(this.driver, locator);
    }

    public List<String> getWebElementsText(Locator locator) {
        return ActionsExecutor.getWebElementsText(driver, locator);
    }

    public void refreshPage() {
        this.driver.navigate().refresh();
    }

    public void setTextAndPressEnter(Locator locator, String value) {
        ActionsExecutor.setTextAndPressEnter(this.driver, locator, value);
    }

    public void click(WebElement element) {
        ActionsExecutor.click(this.driver, element);
    }

    public List<WebElement> findElements(Locator locator) {
        return ActionsExecutor.getWebElements(driver, locator);
    }

    public WebElement findElement(Locator locator) {
        return ActionsExecutor.getWebElement(driver, locator);
    }

    public boolean isElementDisplayed(Locator locator) {
        return ActionsExecutor.isElementDisplayed(this.driver, locator);
    }

    public boolean isElementEnabled(Locator locator) {
        return ActionsExecutor.isElementEnabled(this.driver, locator);
    }

    public void scrollToElementUsingActions(Locator locator) {
        ActionsExecutor.scrollToElementUsingActions(this.driver, locator);
    }

    public void scrollDownToElementUsingJS(Locator locator) {
        JavaScriptUtils.scrollDownTillElement(driver, locator);
    }

    public void selectByVisibleText(Locator locator, String option) {
        ActionsExecutor.selectByVisibleText(driver, locator, option);
    }

    public void switchToFrameUsingLocator(Locator locator) {
        ActionsExecutor.switchToFrameUsingLocator(driver, locator);
    }

    public void switchToDefaultContent() {
        ActionsExecutor.switchToDefaultContent(driver);
    }

    public void selectByIndex(Locator locator, int index) {
        ActionsExecutor.selectByIndex(driver,locator,index);
    }
}
