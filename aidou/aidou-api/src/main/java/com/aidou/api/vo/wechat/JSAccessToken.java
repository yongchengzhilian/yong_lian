package com.aidou.api.vo.wechat;

import lombok.Data;

@Data
public class JSAccessToken {
    private String access_token;
    private int expires_in;
}
