package com.aidou.api.vo.request;

import lombok.Data;

/**
 * Created by yingjiafeng on 2019/6/2.
 */
@Data
public class WxPhoneRequest {
    private  String encrypdata;
    private String ivdata;
    private String code;
}
