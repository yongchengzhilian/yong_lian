package com.aidou.api.service;

public interface JsPayService {

    /**
     * 获取用户信息
     * @param code
     */
    Long getUserOAuth(String code);
}
