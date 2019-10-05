package org.qbit.challenge.challenge.dev.expections;

import java.util.List;

public class MessengersException extends RuntimeException {

    private static final long serialVersionUID = -1;

    public MessengersException() {
    }

    public MessengersException(String message) {
        super(message);
    }

    public MessengersException(String message, Throwable cause) {
        super(message, cause);
    }

    public MessengersException(Throwable cause) {
        super(cause);
    }

    public MessengersException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}