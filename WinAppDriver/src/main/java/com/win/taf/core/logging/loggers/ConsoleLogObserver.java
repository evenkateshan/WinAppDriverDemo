package com.win.taf.core.logging.loggers;

import com.win.taf.core.logging.MessageType;

public class ConsoleLogObserver implements LogObserver {

    @Override
    public void log(MessageType messageType, String message) {
        switch (messageType) {
            case INFO: default:
                info(message);
                break;
            case DEBUG:
                debug(message);
                break;
            case ERROR:
                error(message);
                break;
            case WARNING:
                warning(message);
                break;
        }
    }

    @Override
    public void log(MessageType messageType, String message, Throwable throwable) {
        switch (messageType) {
            case INFO: default:
                info(message, throwable);
                break;
            case DEBUG:
                debug(message, throwable);
                break;
            case ERROR:
                error(message, throwable);
                break;
            case WARNING:
                warning(message, throwable);
                break;
        }
    }

    @Override
    public void info(String message) {
        System.out.printf("INFO : %s", message);
    }

    @Override
    public void info(String message, Throwable throwable) {
        System.out.printf("INFO : %s", message);
        throwable.printStackTrace();
    }

    @Override
    public void debug(String message) {
        System.out.printf("DEBUG: %s", message);
    }

    @Override
    public void debug(String message, Throwable throwable) {
        System.out.printf("DEBUG: %s", message);
        throwable.printStackTrace();
    }

    @Override
    public void error(String message) {
        System.out.printf("ERROR: %s", message);

    }

    @Override
    public void error(String message, Throwable throwable) {
        System.out.printf("ERROR: %s", message);
        throwable.printStackTrace();
    }

    @Override
    public void warning(String message) {
        System.out.printf("WARNING: %s", message);
    }

    @Override
    public void warning(String message, Throwable throwable) {
        System.out.printf("WARNING: %s", message);
        throwable.printStackTrace();
    }
}
