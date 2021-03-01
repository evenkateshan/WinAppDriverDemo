package com.win.taf.core.exceptions;

public class TATestDataReadException extends TABaseException {
    public TATestDataReadException() {
    }

    public TATestDataReadException(String message) {
        super(message);
    }

    public TATestDataReadException(String message, Throwable cause) {
        super(message, cause);
    }

    public TATestDataReadException(Throwable cause) {
        super(cause);
    }

    public TATestDataReadException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
