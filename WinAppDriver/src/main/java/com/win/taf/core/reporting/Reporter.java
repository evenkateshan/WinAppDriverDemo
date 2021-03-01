package com.win.taf.core.reporting;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.win.taf.core.logging.MessageType;

/**
 *
 */

public class Reporter {

    private static List<HTMLReportObserver> observerList = new ArrayList<>();

    private Reporter() {
    }

    public static void register(HTMLReportObserver htmlReportObserver) {
        observerList.add(htmlReportObserver);
    }

    public static void unRegister(HTMLReportObserver htmlReportObserver) {
        observerList.remove(htmlReportObserver);
    }

    public static void setUp() {
        observerList.forEach(HTMLReportObserver::setUp);
    }

    public static void tearDown() {
        observerList.forEach(HTMLReportObserver::tearDown);
    }

    public static void createTest(String testName) {
        observerList.forEach(e -> e.createTest(testName));
    }

    public static void createTest(String testName, Map<String, Object> additionalParams) {
        observerList.forEach(e -> e.createTest(testName, additionalParams));
    }

    public static void log(MessageType messageType, String message) {
        observerList.forEach(e -> e.log(messageType, message));
    }

    public static void log(MessageType MessageType, String message, Throwable throwable) {
        observerList.forEach(e -> e.log(MessageType, message, throwable));
    }

    public static void info(String message) {
        observerList.forEach(e -> e.info(message));
    }

    public static void info(String message, Throwable throwable) {
        observerList.forEach(e -> e.info(message, throwable));
    }

    public static void pass(String message) {
        observerList.forEach(e -> e.pass(message));
    }

    public static void fail(String message) {
        observerList.forEach(e -> e.fail(message));
    }

    public static void fail(String message, Throwable throwable) {
        observerList.forEach(e -> e.fail(message, throwable));
    }

    public static void skip(String message) {
        observerList.forEach(e -> e.skip(message));
    }

    public static void skip(String message, Throwable throwable) {
        observerList.forEach(e -> e.skip(message, throwable));
    }

    public static void warning(String message) {
        observerList.forEach(e -> e.warning(message));
    }

    public static void warning(String message, Throwable throwable) {
        observerList.forEach(e -> e.warning(message, throwable));
    }

    public static void error(String message) {
        observerList.forEach(e -> e.error(message));
    }

    public static void error(String message, Throwable throwable) {
        observerList.forEach(e -> e.error(message, throwable));
    }

    public static void addScreenshot(String screenshotName, String description) {
        observerList.forEach(e -> e.addScreenshot(screenshotName, description));
    }
}
