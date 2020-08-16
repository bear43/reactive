package com.axear.reactive.util;

import com.axear.reactive.model.ExceptionWrapper;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class Exceptions {

    public static final String EMPTY_EXCEPTION_MESSAGE = "UNKNOWN EXCEPTION";
    public static final String KEY_TO_PUT_VALUE = "exception";

    private static void log(Logger logger, Throwable ex, String message) {
        if (ex == null) {
            logger.error(message);
        } else {
            logger.error(message, ex);
        }
    }

    public static ExceptionWrapper createExceptionWrapper(Throwable ex, Logger logger) {
        ExceptionWrapper exceptionWrapper = new ExceptionWrapper();
        if (ex == null) {
            exceptionWrapper.setMessage(Exceptions.EMPTY_EXCEPTION_MESSAGE);
            exceptionWrapper.setStatus(HttpStatus.BAD_REQUEST);
        } else if ((ResponseStatusException.class.isAssignableFrom(ex.getClass()))) {
            exceptionWrapper.setMessage(ex.getMessage() == null ? ((ResponseStatusException) ex).getStatus().getReasonPhrase() : ex.getMessage());
            exceptionWrapper.setStatus(((ResponseStatusException) ex).getStatus());
        } else {
            exceptionWrapper.setMessage(ex.getMessage());
            exceptionWrapper.setStatus(HttpStatus.BAD_REQUEST);
        }
        log(logger, ex, exceptionWrapper.getMessage());
        return exceptionWrapper;
    }
}
