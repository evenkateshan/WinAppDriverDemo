package com.win.cucumber;

import com.epam.reportportal.testng.ReportPortalTestNGListener;
import com.win.taf.core.reporting.Reporter;
import com.win.taf.core.reporting.reportportal.ReportPortalObserver;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;

@Listeners({ReportPortalTestNGListener.class})
@CucumberOptions(
        strict = false,
        features = {"src\\test\\resources\\features"},
        glue = {"com.win", "com.win.taf"},
        tags= {"@Calculator"}
        )
public class CucumberRunner extends AbstractTestNGCucumberTests {
    @Override
    @DataProvider(parallel = false)
    public Object[][] scenarios() {
        return super.scenarios();
    }

    @BeforeSuite
    public void beforeSuite(){
        Reporter.register(new ReportPortalObserver());
    }
}
