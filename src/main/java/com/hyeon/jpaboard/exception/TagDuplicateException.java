package com.hyeon.jpaboard.exception;

public class TagDuplicateException extends RuntimeException {

    public TagDuplicateException() {
        super();
    }

    public TagDuplicateException(String message) {
        super(message);
    }

    public TagDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagDuplicateException(Throwable cause) {
        super(cause);
    }
}
