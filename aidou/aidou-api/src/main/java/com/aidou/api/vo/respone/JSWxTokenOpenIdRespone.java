package com.aidou.api.vo.respone;

import lombok.Data;

@Data
public class JSWxTokenOpenIdRespone {

    private String access_token;
    private int expires_in;
    private String refresh_token;
    private String openid;
    private String scope;
    private String unionid;


}
