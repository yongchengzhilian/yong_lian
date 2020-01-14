package com.aidou.api.exception;

/**
 * Created by guoyi on 2017/4/25.
 */
public class UserExistSendException extends RuntimeException {
    public UserExistSendException(String message) {
        super(message);
    }
}
