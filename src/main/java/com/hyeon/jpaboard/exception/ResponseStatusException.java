package com.hyeon.jpaboard.exception;

public class ResponseStatusException extends RuntimeException{
    public ResponseStatusException() {
        super();
    }

    public ResponseStatusException(String message) {
        super(message);
    }

    public ResponseStatusException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResponseStatusException(Throwable cause) {
        super(cause);
    }
}
