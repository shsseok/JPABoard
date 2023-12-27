package com.hyeon.jpaboard.exception;

public class PostNotFoundException extends RuntimeException {
    public PostNotFoundException() {
        super();
    }

    public PostNotFoundException(String s) {
    }

    public PostNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }

    public PostNotFoundException(Throwable cause) {
        super(cause);
    }

}
