package com.win.pages;

import java.net.URL;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.win.taf.core.driver.DriverManager;
import com.win.taf.ui.AbstractPageWindows;
import com.win.taf.ui.ActionsExecutor;

import io.appium.java_client.windows.WindowsDriver;


public class CalculatorPage extends AbstractPageWindows {
	
	WindowsDriver winDriver=DriverManager.getWinDriver();
	WebElement fourBtn=winDriver.findElementByName("Four");
	WebElement fiveBtn=winDriver.findElementByName("Five");
	WebElement nineBtn=winDriver.findElementByName("Nine");
	WebElement plusBtn=winDriver.findElementByName("Plus");
	WebElement minusBtn=winDriver.findElementByName("Minus");
	WebElement equalsBtn=winDriver.findElementByName("Equals");
	WebElement resultBox=winDriver.findElementByAccessibilityId("CalculatorResults");	
	
	
 public CalculatorPage()
	{
	 super(DriverManager.getWinDriver());
	}
	
	
	public void enterValuesAdd()
	{
		ActionsExecutor.click(winDriver, fourBtn);
		ActionsExecutor.click(winDriver, plusBtn);
		ActionsExecutor.click(winDriver, fiveBtn);
		ActionsExecutor.click(winDriver, equalsBtn);
	}
	
	public void enterValuesSubtract()
	{
		ActionsExecutor.click(winDriver, nineBtn);
		ActionsExecutor.click(winDriver, minusBtn);
		ActionsExecutor.click(winDriver, fiveBtn);
		ActionsExecutor.click(winDriver, equalsBtn);
	}
	
	public String _GetCalculatorResultText()
    {
        return ActionsExecutor.getText(winDriver, resultBox).replace("Display is", "").trim();
    }

}
