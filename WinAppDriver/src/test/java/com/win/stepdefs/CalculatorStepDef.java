package com.win.stepdefs;

import org.testng.Assert;

import com.win.pages.CalculatorPage;
import com.win.taf.core.reporting.Reporter;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class CalculatorStepDef {
	   CalculatorPage calc;
	//Launch Calculator App
	//User enters '4' and '5' to add
	//Then Validate the Result for addition
	
	@Given("Launch Calculator App")
    public void launchCalc()
	    {
	       calc=new CalculatorPage();
	    }

	 @When("User enters values to add")
	 public void enterValuesInCalc(){
		 calc.enterValuesAdd();
	    }

	  @Then("Validate the Result for addition")
	  public void validateResultAdd(){
	    	 Assert.assertEquals("9", calc._GetCalculatorResultText());
	    }
	  @When("User enters values to subtract")
	    public void enterValuesInCalcSubtract(){
		 calc.enterValuesSubtract();
	    }

	  @Then("Validate the Result for subtraction")
	  public void validateResultSubtract(){
	   	 Assert.assertEquals("9", calc._GetCalculatorResultText());
	   }

}
