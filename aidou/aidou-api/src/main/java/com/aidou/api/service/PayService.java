package com.aidou.api.service;

import com.aidou.api.vo.request.wechat.WechatPayRequest;
import com.aidou.util.exception.BizException;

import java.util.Map;

/**
 * Copyright@www.lanhusoft.com.
 * Author:yingjiafeng
 * Date:2019/11/11
 * Description:
 * @author 1
 */
public interface PayService {

    /**
     * 获取JS支付参数
     * @param wechatPayRequest
     * @return
     */
    Map<String, Object>  getJavaScriptPayInfo(WechatPayRequest wechatPayRequest,String  ip)  throws BizException;

}
