package com.win.taf.ui;

import com.win.taf.core.utils.PropertyReader;

public class EnvironmentConstants {

    private static PropertyReader prop = new PropertyReader("src/test/resources/environment/"+System.getProperty("env")+".properties");
    private static String URL =getURL();

    private EnvironmentConstants(){

    }

    private static String getURL() {
        return getPropertyAsPerThePriority("test.baseURI.homepage", "drivers/safaridriver.exe");
    }

    private static String getPropertyAsPerThePriority(String property, String defaultValue) {
        if (prop != null) {
            return System.getProperty(property, prop.getProperty(property, defaultValue));
        }
        return System.getProperty(property, defaultValue);
    }
}
