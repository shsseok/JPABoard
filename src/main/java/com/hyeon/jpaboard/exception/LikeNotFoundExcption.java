package com.hyeon.jpaboard.exception;

public class LikeNotFoundExcption extends RuntimeException{
    public LikeNotFoundExcption() {
        super();
    }

    public LikeNotFoundExcption(String message) {
        super(message);
    }

    public LikeNotFoundExcption(String message, Throwable cause) {
        super(message, cause);
    }

    public LikeNotFoundExcption(Throwable cause) {
        super(cause);
    }
}
