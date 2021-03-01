package com.win.taf.core.logging.loggers;

import com.win.taf.core.logging.MessageType;

public interface LogObserver {
    void log(MessageType messageType, String message);

    void log(MessageType messageType, String message, Throwable throwable);

    void info(String message);

    void info(String message, Throwable throwable);

    void debug(String message);

    void debug(String message, Throwable throwable);

    void error(String message);

    void error(String message, Throwable throwable);

    void warning(String message);

    void warning(String message, Throwable throwable);
}
