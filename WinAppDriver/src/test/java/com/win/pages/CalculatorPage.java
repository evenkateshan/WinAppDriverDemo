package com.win.pages;

import java.net.URL;
import org.openqa.selenium.remote.DesiredCapabilities;

import com.win.taf.core.driver.DriverManager;
import com.win.taf.ui.AbstractPageWindows;

import io.appium.java_client.windows.WindowsDriver;


public class CalculatorPage extends AbstractPageWindows {
	
	WindowsDriver winDriver=DriverManager.getWinDriver();
	//four
	//five
	//result
	//plus
	//minus
	//equals
	
	
	
 public CalculatorPage()
	{
	 super(DriverManager.getWinDriver());
	}
	
	
	public void enterValuesAdd()
	{
		System.out.println("Four");
		//click four
		//click plus
		//click five
		//click equals
//		winDriver.findElementByName(a).click();
//		
//		
//		if(c.equals("Add"))
//		{
//			winDriver.findElementByName("Plus").click();
//		}
//		else
//		if(c.equals("Subtract"))
//		{
//			winDriver.findElementByName("Minus").click();
//		}
//		else
//		if(c.equals("Multiply"))
//		{
//			winDriver.findElementByName("Multiply by").click();
//		}
//		else
//		if(c.equals("Divide"))
//		{
//			winDriver.findElementByName("Divide by").click();
//		}
//		winDriver.findElementByName(b).click();
//		
//		 winDriver.findElementByName("Equals").click();
				
	}
	
	public void enterValuesSubtract()
	{
		System.out.println("Four5");
		//Nine
		//minus
		//five
		//equals
	}
	
	public String _GetCalculatorResultText()
    {
        return winDriver.findElementByAccessibilityId("CalculatorResults").getText().replace("Display is", "").trim();
    }

}
