package com.aidou.api.exception;

/**
 * Created by guoyi on 2017/4/25.
 */
public class UserLockException extends RuntimeException {
    public UserLockException(String message) {
        super(message);
    }
}
