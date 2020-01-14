package com.aidou.api.exception;

/**
 * Created by guoyi on 2017/4/25.
 */
public class UserNotPresenceException extends RuntimeException {
    public UserNotPresenceException(String message) {
        super(message);
    }
}
