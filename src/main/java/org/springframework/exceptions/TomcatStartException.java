package org.springframework.exceptions;

public class TomcatStartException extends RuntimeException {

    public TomcatStartException(Throwable cause) {
        super(cause);
    }
}
