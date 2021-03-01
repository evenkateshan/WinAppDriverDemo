package com.win.taf.core.utils;

import com.win.taf.core.exceptions.TARuntimeException;
import com.win.taf.ui.SystemConstants;
import com.saucelabs.saucerest.SauceREST;

import java.util.HashMap;
import java.util.Map;

public class SauceUtils {
    /**
     * Login singleton instance of the Sauce REST client
     */
    private static SauceREST sauceClient;

    private SauceUtils() {
    }

    private static SauceREST getSauceRestClient() {
        if (sauceClient == null) {
            sauceClient = new SauceREST(SystemConstants.USER_NAME, SystemConstants.ACCESS_KEY, SystemConstants.SAUCE_DATA_CENTER);
        }
        return sauceClient;
    }

    public static synchronized void updateResults(boolean testResults, String sessionId) {
        Map<String, Object> updates = new HashMap<>();
        updates.put("passed", testResults);
        try {
            getSauceRestClient().updateJobInfo(sessionId, updates);
        } catch (Exception ex) {
            throw new TARuntimeException("Getting exception while updating the test result status...!!!", ex);
        }
    }
}
