package com.win.taf.core;

import com.win.taf.core.exceptions.TARuntimeException;

public class TestContext {

    private static ThreadLocal<Context> scenarioContext = new ThreadLocal<>();

    private TestContext() {
    }

    public static synchronized <T> T get(String key) {
        if (scenarioContext.get() != null) {
            return scenarioContext.get().get(key);
        } else {
            throw new TARuntimeException("Test Context is not initialized..!!");
        }
    }

    public static synchronized void put(String key, Object value) {
        scenarioContext.get().put(key, value);
    }

    public static void registerContext(Context context) {
        scenarioContext.set(context);
    }

    public static void removeContext() {
        scenarioContext.remove();
    }
}
