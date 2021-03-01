package com.win.taf.ui;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.ArrayList;
import java.util.List;

public class ActionsExecutor {

    private ActionsExecutor() {
    }

    public static void click(WebDriver driver, Locator locator) {
        try {
            SyncUtils.waitForClickable(driver, locator);
            driver.findElement(locator.getBy()).click();
        } catch (Exception ex) {
            SyncUtils.waitForClickable(driver, locator, 30);
            JavaScriptUtils.click(driver, locator);
        }
    }

    public static void click(WebDriver driver, WebElement element) {
        try {
            element.click();
        } catch (Exception ex) {
            JavaScriptUtils.click(driver, element);
        }
    }

    public static void sendKeys(WebDriver driver, Locator locator, String value) {
        try {
            driver.findElement(locator.getBy()).sendKeys(value);
        } catch (Exception ex) {
            SyncUtils.waitForClickable(driver, locator);
            driver.findElement(locator.getBy()).sendKeys(value);
        }
    }

    public static void sendKeys(WebDriver driver, Locator locator, Keys keyToPress) {
        try {
            driver.findElement(locator.getBy()).sendKeys(keyToPress);
        } catch (Exception ex) {
            SyncUtils.waitForElementVisibility(driver, locator);
            driver.findElement(locator.getBy()).sendKeys(keyToPress);

        }
    }

    public static String getText(WebDriver driver, Locator locator) {
        try {
            SyncUtils.waitForElementVisibility(driver, locator);
            return driver.findElement(locator.getBy()).getText();
        } catch (Exception ex) {
            return JavaScriptUtils.getText(driver, locator);
        }
    }


    public static void clickByAction(WebDriver driver, Locator locator) {
        Actions action = new Actions(driver);
        try {
            JavaScriptUtils.highlightElementWithWait(driver, driver.findElement(locator.getBy()));
            action.moveToElement(driver.findElement(locator.getBy())).click().perform();
        } catch (Exception ex) {
            SyncUtils.waitForClickable(driver, locator);
            action.moveToElement(driver.findElement(locator.getBy())).click().perform();
            JavaScriptUtils.unhighlightElement(driver, driver.findElement(locator.getBy()));
        }
    }

    public static void clearText(WebDriver driver, Locator locator) {
        try {
            driver.findElement(locator.getBy()).clear();
        } catch (Exception ex) {
            SyncUtils.waitForElementVisibility(driver, locator);
            driver.findElement(locator.getBy()).clear();
        }
    }

    public static void setTextAndPressEnter(WebDriver driver, Locator locator, String value) {
        SyncUtils.waitForClickable(driver, locator);
        sendKeys(driver, locator, value);
        sendKeys(driver, locator, Keys.ENTER);
    }

    public static List<WebElement> getWebElements(WebDriver driver, Locator locator) {
        return driver.findElements(locator.getBy());
    }

    public static List<String> getWebElementsText(WebDriver driver, Locator locator) {
        List<String> texts = new ArrayList<>();
        List<WebElement> webElementList = getWebElements(driver, locator);
        for (WebElement element : webElementList) {
            texts.add(element.getText());
        }
        return texts;
    }

    public static WebElement getWebElement(WebDriver driver, Locator locator) {
        try {
            return driver.findElement(locator.getBy());
        } catch (Exception e) {
            SyncUtils.waitForElementVisibility(driver, locator);
            return driver.findElement(locator.getBy());
        }
    }

    public static boolean isElementDisplayed(WebDriver driver, Locator locator) {
        try {
            return driver.findElement(locator.getBy()).isDisplayed();
        } catch (Exception e) {
            SyncUtils.waitForElementVisibility(driver, locator);
            return driver.findElement(locator.getBy()).isDisplayed();
        }
    }

    public static boolean isElementEnabled(WebDriver driver, Locator locator) {
        try {
            return driver.findElement(locator.getBy()).isEnabled();
        } catch (Exception e) {
            SyncUtils.waitForElementVisibility(driver, locator);
            return driver.findElement(locator.getBy()).isDisplayed();
        }
    }

    public static void scrollToElementUsingActions(WebDriver driver, Locator locator) {
        Actions action = new Actions(driver);
        action.moveToElement(driver.findElement(locator.getBy()));
    }

    public static void scrollTillElementUsingJavaScriptExecutor(WebDriver driver, Locator locator) {
        JavaScriptUtils.scrollDownTillElement(driver, locator);
    }

    public static void selectByVisibleText(WebDriver driver, Locator locator, String option) {
        Select select = new Select(driver.findElement(locator.getBy()));
        select.selectByVisibleText(option);
    }

    public static void selectByIndex(WebDriver driver, Locator locator, int index) {
        Select select = new Select(driver.findElement(locator.getBy()));
        select.selectByIndex(index);
    }

    public static void switchToFrameUsingLocator(WebDriver driver, Locator locator) {
        driver.switchTo().frame(driver.findElement(locator.getBy()));
    }

    public static void switchToDefaultContent(WebDriver driver) {
        driver.switchTo().defaultContent();
    }
}

