package com.hyeon.jpaboard.exception;

public class TagDuplicateException extends RuntimeException {
    private Long postId;
    public Long getPostId()
    {
        return postId;
    }

    public TagDuplicateException() {
        super();
    }

    public TagDuplicateException(String message) {
        super(message);
    }
    public TagDuplicateException(String message,Long postId) {
        super(message);
        this.postId=postId;
    }
    public TagDuplicateException(String message, Throwable cause) {
        super(message, cause);
    }

    public TagDuplicateException(Throwable cause) {
        super(cause);
    }
}
