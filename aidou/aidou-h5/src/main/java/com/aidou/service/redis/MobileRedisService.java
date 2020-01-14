package com.aidou.service.redis;


import com.aidou.enums.SmsCodeType;

/**
 * Created by yingjiafeng on 2019/4/24.
 */
public interface MobileRedisService {

    /**
     * 判断当前IP在短信个类型中是否达到最大数量
     * @param ipAddress
     * @param bizType
     * @return
     */
    boolean   isIpTypeMaxSendCount(String ipAddress, SmsCodeType bizType);

    /**
     * 获取手机验证码
     * @return
     */
    String   getSmsCodeByMobile(String key);

    /**
     * 保存手机验证码
     * @param code  手机验证码
     * @return
     */
    void  saveSmsCode(String key, String code);




}
