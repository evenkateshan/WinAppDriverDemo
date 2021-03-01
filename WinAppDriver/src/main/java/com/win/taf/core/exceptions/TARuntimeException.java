package com.win.taf.core.exceptions;

public class TARuntimeException extends TABaseException {

    public TARuntimeException() {
        super();
    }

    public TARuntimeException(String message) {
        super(message);
    }

    public TARuntimeException(String message, Throwable cause) {
        super(message, cause);
    }

    public TARuntimeException(Throwable cause) {
        super(cause);
    }

    protected TARuntimeException(String message, Throwable cause,
                                 boolean enableSuppression,
                                 boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
