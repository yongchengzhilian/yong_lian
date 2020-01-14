package com.aidou.vo;


import com.aidou.enums.SmsCodeType;
import lombok.Data;

/**
 * 用户请求获取验证码，参数对象
 */

@Data
public class SmsCodeRequest {


    /**
     * 手机号
     */
    private String mobile;

    /**
     * 业务码
     */
    private SmsCodeType type;


}
