package com.hyeon.jpaboard.exception;

public class LikeDuplicateException extends RuntimeException{
    public LikeDuplicateException() {
        super();
    }

    public LikeDuplicateException(String message) {
        super(message);
    }

    public LikeDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public LikeDuplicateException(Throwable cause) {
        super(cause);
    }
}
