package com.aidou.api.service;

import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.wechat.WechatAuthInfo;
import com.aidou.util.exception.WechatAesDesryptException;
import com.aidou.util.exception.WechatTokenNotFoundException;

public interface WechatAuthService {



    /**
     * 登陆
     * @return
     */
    AidouResult login(WechatAuthInfo wechatAuthInfo) throws WechatAesDesryptException, WechatTokenNotFoundException;

    /**
     * 微信获取用户手机号解密
     * @param encrypdata
     * @param ivdata
     * @param code
     * @return
     */
    String deciphering(String encrypdata, String ivdata, String code,String source) throws WechatAesDesryptException;

    /**
     *微信消息推送保存
     * @param id
     * @return
     */
    void saveMessageId(String id) throws Exception;


    /**
     * 通过员工ID 获取openId
     * @param uid
     * @return
     */
    String findOpenIdByUid(Long uid);

    /**
     * 获取服务号的accessToken
     * @return
     */
    String jsAccessToken();

}
