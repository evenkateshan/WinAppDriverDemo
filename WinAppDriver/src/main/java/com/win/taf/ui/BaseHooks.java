package com.win.taf.ui;

import com.win.taf.core.Context;
import com.win.taf.core.TestContext;
import com.win.taf.core.driver.DriverManager;
import com.win.taf.core.reporting.Reporter;
import com.win.taf.core.utils.SauceUtils;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

public class BaseHooks {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Before(order = 1)
    public void beforeScenarioBaseHook(Scenario scenario) {
        logger.info("Running Scenario : '{}'", scenario.getName());
        Map<String, Object> additionalCapabilities = new HashMap<>();
        additionalCapabilities.put("name", scenario.getName());
       DriverManager.initializeWindowsDriver(SystemConstants.BROWSER, SystemConstants.BROWSER_VERSION,
                SystemConstants.PLATFORM, SystemConstants.EXEC_ENV, additionalCapabilities);
        TestContext.registerContext(new Context());
        String featureName = getFeatureName(scenario);
        TestContext.put("FeatureName", featureName);

        String methodName = scenario.getName();
        Map<String, Object> additionalParams = new HashMap<>();
        Reporter.createTest(methodName, additionalParams);
        Reporter.info("Running scenario: "+ scenario.getName());
    }

    @After(order = 1)
    public void afterScenarioBaseHook(Scenario scenario) {
        if ("sauce".equalsIgnoreCase(SystemConstants.EXEC_ENV)) {
            SauceUtils.updateResults(!scenario.isFailed(), DriverManager.getSessionIds());
        }
        Reporter.info(String.format("Completed Scenario '%s' with Status:'%s' ", scenario.getName(), scenario.getStatus()));
        if(scenario.isFailed())
            Reporter.addScreenshot("Failed", "Screenshot After Failure");
       // DriverManager.quitDriver();
    }

    private String getFeatureName(Scenario scenario) {
        String[] idSplitArr = scenario.getId().split("/");
        String featureFileWithExt = idSplitArr[idSplitArr.length - 1];
        return featureFileWithExt.split("\\.")[0];
    }

}
