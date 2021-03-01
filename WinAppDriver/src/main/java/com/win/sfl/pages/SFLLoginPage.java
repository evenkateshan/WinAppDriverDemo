package com.win.sfl.pages;

import com.win.taf.core.reporting.Reporter;
import com.win.taf.ui.AbstractPage;
import com.win.taf.ui.Locator;

import org.openqa.selenium.WebDriver;

public class SFLLoginPage extends AbstractPage {

    //TextBoxes
    protected static Locator txtUserName = Locator.byXpath("//input[@id='username']");
    protected static Locator txtPassword = Locator.byXpath("//input[@id='password']");

    //Buttons
    protected static Locator btnLogin = Locator.byXpath("//input[@id='Login']");
    protected static Locator btnMyABC = Locator.byXpath("//input[text()='%s']");


    public SFLLoginPage(WebDriver driver) {
        super(driver);
    }

    public SFLLoginPage setUserName(String userName) {
        sendKeys(txtUserName, userName);
        Reporter.info(String.format("Entered username '%s'", userName));
        return this;
    }

    public SFLLoginPage setPassword(String password) {
        sendKeys(txtPassword, password);
        Reporter.info(String.format("Entered Password '%s'", password));
        return this;
    }

    public void clickOnLoginButton() {
        click(btnLogin);
        Reporter.info("Clicked on 'Login' button");
    }

    public void loginToWebApplication(String username, String password) {
        Reporter.addScreenshot("LoginScreenShot","My Description");
        setUserName(username)
                .setPassword(password)
                .clickOnLoginButton();
    }
}
